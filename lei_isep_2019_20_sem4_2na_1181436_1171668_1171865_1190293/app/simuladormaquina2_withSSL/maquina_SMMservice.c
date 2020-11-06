#include "header.h"

extern shared_data_type *sharedData;
extern pthread_mutex_t mux;
struct sockaddr_storage client;
int sock_SMM, i;
unsigned int adl;
unsigned char bt;
unsigned long f;


struct Mensagem{
	unsigned char version;
    unsigned char code;
    unsigned short id;
    unsigned short data_length;
    char* raw_data;
};

struct Mensagem decifrar_mensagem(unsigned char *msg){
	unsigned char version = msg[0];
	unsigned char code = msg[1];
	unsigned short id = 0;
	//Leitura do id (2 bytes)
	for(i = 0; i < 2; i++){
		bt = msg[2+i];
		id = id + bt * f;
		f = f * 256;
	}
	f = 1;
	bt = 0;
	unsigned short data_length = 0;
	//Leitura do data_length (2 bytes)
	for(i = 0; i < 2; i++){
		bt = msg[4+i];
		data_length = data_length + bt * f;
		f = f * 256;
	}
	//Leitura do raw data
	char raw_data[data_length];
	if(data_length > 0){
		for(i = 0; i < data_length; i++){
			raw_data[i] = msg[6+i];
		}
	}

	struct Mensagem mensagem = {version, code, id, data_length, raw_data};

	return mensagem;
}

void responder_pedido(){
	//Aguarda para aceder ao recurso partilhado.
	pthread_mutex_lock(&mux);
	int data_length = strlen(sharedData->ultima_mensagem);
	char cod_ultima_mensagem = sharedData->codigo_ultima_mensagem;
	char* ultima_mensagem = sharedData->ultima_mensagem;
	pthread_mutex_unlock(&mux);

	int num_bytes = 6 + data_length;
	char bytes[num_bytes];

	bytes[0] = VERSION;
	bytes[1] = cod_ultima_mensagem;
	unsigned char bt;
	unsigned short id = CODIGO_UNICO;
	//Escrita do id (2 bytes)
	for(i = 0; i < 2; i++){
		bt = id%256;
		bytes[2+i] = bt;
		id = id / 256;
	}
	//Escrita do data length (2 bytes)
	int num = data_length;
	for(i = 0; i < 2; i++){
		bt = num%256;
		bytes[4+i] = bt;
		num = num / 256;
	}
	//Escrita do raw data
	for(i = 0; i < data_length; i++){
		bt = num%256;
		bytes[6+i] = ultima_mensagem[i];
		num = num / 256;
	}
	sendto(sock_SMM,bytes,num_bytes,0,(struct sockaddr *)&client,adl);
}

void reinicializar_maquina_request(){
	pthread_mutex_lock(&mux);
	sharedData->por_a_dormir = 1;
	pthread_mutex_unlock(&mux);
	
}




