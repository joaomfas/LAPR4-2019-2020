#include "header.h"

extern shared_data_type *sharedData;
extern pthread_mutex_t mux;
int sock_SCM, err, i;
unsigned char bt, lsize;
unsigned long f;

/**
 * For SSL connection
 **/
char line[BUFFER_SIZE];
SSL *sslConn;


struct Mensagem{
	unsigned char version;
    unsigned char code;
    unsigned short id;
    unsigned short data_length;
    char* raw_data;
};

struct Mensagem receber_mensagem(){
	unsigned char version = 0;
	unsigned char code = 0;
	unsigned short id = 0;
	unsigned short data_length = 0;

	//Leitura da version (1 byte)
	SSL_read(sslConn, &bt, 1);
	version = bt;
	//Leitura do code (1 byte)
	SSL_read(sslConn, &bt, 1);
	code = bt;
	f = 1;
	bt = 0;
	//Leitura do id (2 bytes)
	for(i = 0; i < 2; i++){
		SSL_read(sslConn, &bt, 1);
		id = id + bt * f;
		f = f * 256;
	}
	f = 1;
	bt = 0;
	//Leitura do data_length (2 bytes)
	for(i = 0; i < 2; i++){
		SSL_read(sslConn, &bt, 1);
		data_length = data_length + bt * f;
		f = f * 256;
	}
	//Leitura do raw data
	char *c = malloc(data_length + 1);
	char p = 0;
	for(i = 0; i < data_length; i++){
		SSL_read(sslConn, &p, 1);
		c[i] = p;
	}
	c[data_length]='\0';

	char* raw_data = strdup(c);
	//Atualiza o código da última mensagem recebida para o sistema de monitorização.
	//Aguarda que o recurso partilhado seja libertado.
	pthread_mutex_lock(&mux);
	sharedData->codigo_ultima_mensagem = id;
	sharedData->ultima_mensagem = raw_data;
	pthread_mutex_unlock(&mux);

	struct Mensagem msg = {version, code, id, data_length, raw_data};


	return msg;
}

struct Mensagem enviar_mensagem(unsigned char version, unsigned char code, unsigned short id, unsigned short data_length, char* raw_data){

	bt = version;
	//Escrita do version (1 byte)
	SSL_write(sslConn, &bt, 1);
	bt = code;
	//Escrita do code (1 byte)
	SSL_write(sslConn, &bt, 1);
	//Escrita do id (2 bytes)
	for(i = 0; i < 2; i++){
		bt = id%256;
		SSL_write(sslConn, &bt, 1);
		id = id / 256;
	}
	//Escrita do data length (2 bytes)
	int num = data_length;
	for(i = 0; i < 2; i++){
		bt = num%256;
		SSL_write(sslConn, &bt, 1);
		num = num / 256;
	}
	lsize = data_length;
	if(lsize > 0){
		SSL_write(sslConn, raw_data, lsize);
	}

	struct Mensagem msg = receber_mensagem();

	return msg;
}

struct Mensagem enviar_mensagem_hello(){
	unsigned char version = VERSION;
	unsigned char code = CODE_HELLO;
	unsigned short id = CODIGO_UNICO;
	unsigned short data_length = 0;

	char* raw_data = "";
	const SSL_METHOD *method = SSLv23_client_method();


	SSL_CTX *ctx = SSL_CTX_new(method);

	// Load client's certificate and key
	strcpy(line,CLIENT_SSL_CERT_FILE);
	SSL_CTX_use_certificate_file(ctx, line, SSL_FILETYPE_PEM);

	strcpy(line, CLIENT_SSL_KEY_FILE);
	SSL_CTX_use_PrivateKey_file(ctx, line, SSL_FILETYPE_PEM);

	if (!SSL_CTX_check_private_key(ctx)) {
		puts("Error loading client's certificate/key");
		close(sock_SCM);
		pthread_exit(NULL);
		exit(EXIT_FAILURE);
	}

	SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER,NULL);

	// THE SERVER'S CERTIFICATE IS TRUSTED
	SSL_CTX_load_verify_locations(ctx,SERVER_SSL_CERT_FILE,NULL);

	// Restrict TLS version and cypher suites
	SSL_CTX_set_min_proto_version(ctx,TLS1_2_VERSION);
	SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");
	sslConn = SSL_new(ctx);
	SSL_set_fd(sslConn, sock_SCM);

	if(SSL_connect(sslConn)!=1) {
		puts("TLS handshake error");
		SSL_free(sslConn);
		close(sock_SCM);
		pthread_exit(NULL);
		exit(EXIT_FAILURE);
	}

	printf("TLS version: %s\nCypher suite: %s\n",
	SSL_get_cipher_version(sslConn),SSL_get_cipher(sslConn));

	if(SSL_get_verify_result(sslConn)!=X509_V_OK) {
		puts("Sorry: invalid server certificate");
		SSL_free(sslConn);
		close(sock_SCM);
		pthread_exit(NULL);
		exit(EXIT_FAILURE);
	}

	X509* cert=SSL_get_peer_certificate(sslConn);
	X509_free(cert);

	if(cert==NULL) {
		puts("Sorry: no certificate provided by the server");
		SSL_free(sslConn);
		close(sock_SCM);
		pthread_exit(NULL);
		exit(EXIT_FAILURE);
	}

	struct Mensagem helloResponse = enviar_mensagem(version, code, id, data_length, raw_data);
  return helloResponse;
}

char reinicializar_maquina_response(){

	char maquina_reinicializada = 0;

	pthread_mutex_lock(&mux);
		char reinicializar = sharedData->por_a_dormir;
	pthread_mutex_unlock(&mux);

	if(reinicializar == 1){
		maquina_reinicializada = 1;
		pthread_mutex_lock(&mux);
		sharedData->por_a_dormir = 0;
		pthread_mutex_unlock(&mux);
		return maquina_reinicializada;
	}
	return maquina_reinicializada;
}


struct Mensagem receber_config(struct Mensagem msgConfig){

	unsigned char version = VERSION;
	unsigned char code = CODE_ACK;
	unsigned short id = CODIGO_UNICO;
	unsigned short data_length = 0;
	char* raw_data = "";

	if(msgConfig.id!=CODIGO_UNICO){
		code = CODE_NACK;
		printf("Config recusado\n");
	}else {
		FILE *file;
		char *ficheiroConfig = "./configuracoes/config.txt";

		file = fopen(ficheiroConfig, "w");

		if(file == NULL){
			perror("Erro lendo config.\n");
			code = CODE_NACK;
		}else{
			fprintf(file, "%s", msgConfig.raw_data);
			fclose(file);
			printf("Ficheiro Config salvo com sucesso\n");
		}
	}

	struct Mensagem msg = enviar_mensagem(version, code, id, data_length, raw_data);

	return msg;
}
