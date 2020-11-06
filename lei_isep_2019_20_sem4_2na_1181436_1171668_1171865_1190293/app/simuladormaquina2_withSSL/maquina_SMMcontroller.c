#include "header.h"

struct sockaddr_storage client;
int err, sock_SMM;
unsigned int adl;
unsigned char line[BUFFER_SIZE];
char cliIPtext[BUFFER_SIZE], cliPortText[BUFFER_SIZE];
struct addrinfo req, *list;

struct Mensagem{
	unsigned char version;
    unsigned char code;
    unsigned short id;
    unsigned short data_length;
    char* raw_data;
};

struct Mensagem decifrar_mensagem(unsigned char *msg);
void* responder_pedido();
void* enviar_mensagem_hello();
void* reinicializar_maquina_request();

void abrir_ligacao_SMM(){
	bzero((char *)&req,sizeof(req));
	// request a IPv6 local address will allow both IPv4 and IPv6 clients to use it
	req.ai_family = AF_INET6;
	req.ai_socktype = SOCK_DGRAM;
	req.ai_flags = AI_PASSIVE;	// local address
	err=getaddrinfo(NULL, SERVER_PORT_SMM , &req, &list);

	if(err) {
       	printf("Falha ao obter endereço local, erro: %s\n",gai_strerror(err));
       	exit(EXIT_FAILURE);
	}

	sock_SMM=socket(list->ai_family,list->ai_socktype,list->ai_protocol);
	if(sock_SMM==-1) {
		perror("Falha ao abrir socket\n");
		freeaddrinfo(list);
		exit(EXIT_FAILURE);
	}

	if(bind(sock_SMM,(struct sockaddr *)list->ai_addr, list->ai_addrlen)==-1) {
		perror("Bind failed");
		close(sock_SMM);
		freeaddrinfo(list);
		exit(EXIT_FAILURE);
	}

	freeaddrinfo(list);
}

void pedidos_monitorizacao(){
	
	abrir_ligacao_SMM();

	puts("A aguardar pedidos UDP (em IPv6 ou IPv4). Use CTRL+C para terminar o programa.");

	adl=sizeof(client);
	while(1){
		recvfrom(sock_SMM,line,BUFFER_SIZE,0,(struct sockaddr *)&client,&adl);
		if(!getnameinfo((struct sockaddr *)&client,adl,
		cliIPtext,BUFFER_SIZE,cliPortText,BUFFER_SIZE,NI_NUMERICHOST|NI_NUMERICSERV)){
			printf("Pedido do endereço %s, port number %s\n", cliIPtext, cliPortText);
		}else{
			puts("Pedido obtido mas falha ao obter endereço de origem\n");
		}
		struct Mensagem msg_request = decifrar_mensagem((unsigned char *) line);

		if(msg_request.code == CODE_HELLO){
			printf("Código recebido: %u \n", msg_request.code);
			responder_pedido();
		}
		
		if(msg_request.code == CODE_RESET){
			printf("Código recebido: %u \n", msg_request.code);
			reinicializar_maquina_request();
			printf("Pedido de reinicialização...\n");
			responder_pedido();
			close(sock_SMM);
			sleep(10);
			abrir_ligacao_SMM();
			printf("Reinicialização terminada!\n");
		}		
		
	}
	pthread_exit(NULL);
}

