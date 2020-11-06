#include "header.h"

extern const char* server_ip;
extern SSL *sslConn;
int err, sock_SCM;
unsigned char line[BUFFER_SIZE];
struct addrinfo req, *list;

struct Mensagem{
	unsigned char version;
    unsigned char code;
    unsigned short id;
    unsigned short data_length;
    char* raw_data;
};

struct Mensagem receber_mensagem();
struct Mensagem enviar_mensagem(unsigned char version, unsigned char code, unsigned short id, unsigned short data_length, char* raw_data);
struct Mensagem enviar_mensagem_hello();
char reinicializar_maquina_response();
struct Mensagem receber_config(struct Mensagem);



void abrir_ligacao_SCM(){

	bzero((char *)&req,sizeof(req));
		// let getaddrinfo set the family depending on the supplied server address
		req.ai_family = AF_UNSPEC;
		req.ai_socktype = SOCK_STREAM;
		err=getaddrinfo(server_ip, SERVER_PORT_SCM , &req, &list);
		if(err) {
			printf("Falha ao adquirir endereço IP do servidor, erro: %s\n",gai_strerror(err));
			pthread_exit(NULL);
		}

		sock_SCM=socket(list->ai_family,list->ai_socktype,list->ai_protocol);
		if(sock_SCM==-1) {
			perror("Falha ao abrir socket\n"); freeaddrinfo(list);
			pthread_exit(NULL);
		}

		if(connect(sock_SCM,(struct sockaddr *)list->ai_addr, list->ai_addrlen)==-1) {
			perror("Falha ao conectar\n");
			freeaddrinfo(list);
			close(sock_SCM);
			pthread_exit(NULL);
		}

		freeaddrinfo(list);

}

//função utilizada para verificar se a máquina foi reinicializada
void verificar_reset(){
	char maquina_reinicializada = 0;
	maquina_reinicializada = reinicializar_maquina_response();
	if(maquina_reinicializada == 1){
		printf("Máquina a reiniciar...\n");
		sleep(10);
		printf("Ligação reposta!\n");
	}
}

void envio_mensagens(){
	//IP do servidor SCM

	char *line = NULL;
	FILE *fp;
	size_t len = 0;
	char read;

	//Abrir ficheiro que contém o conteúdo das mensagens a enviar
	fp = fopen("Mensagens.T3", "r");
	if(fp == NULL){
		perror("Erro ao abrir ficheiro\n");
		exit(EXIT_FAILURE);
	}

	int keepCommun =0;
	do{

		abrir_ligacao_SCM();

		verificar_reset();
		sleep(2);
		struct Mensagem responseMessage = enviar_mensagem_hello();

		int secondLoop = 0;

		verificar_reset();
		do{
			switch (responseMessage.code){
			case CODE_CONFIG:
					printf("CONFIG solicitada!\n");
					responseMessage = receber_config(responseMessage);
					secondLoop=1;
				break;
			case CODE_ACK:
				if(secondLoop == 1){
					secondLoop=2;
				} else {
					printf("Ligação estabelecida!\n");
					if((read = getline(&line, &len, fp)) != -1){
						unsigned char version = VERSION;
						unsigned char code = CODE_MSG;
						unsigned short id = CODIGO_UNICO;
						unsigned short data_length = strlen(line);
						char* raw_data = line;
						verificar_reset();
						responseMessage = enviar_mensagem(version, code, id, data_length, raw_data);
						secondLoop=1;
					} else {
						keepCommun=1;
						secondLoop=2;
					}
				}
				break;
			case CODE_NACK:
					printf("Erro: %s\n", responseMessage.raw_data);
					secondLoop=2;
				break;
			default:
					secondLoop=2;
				break;
			}
			verificar_reset();
		}while(secondLoop != 2);

		SSL_free(sslConn);
		close(sock_SCM);

	}while(keepCommun != 1);

	pthread_exit(NULL);
}
