#include "header.h"

void* envio_mensagens();
void* pedidos_monitorizacao();

shared_data_type *__shmOPEN(char *fileName, int *fd, int oflag);

void __shmCLOSE(shared_data_type *sharedData, int fd);

void __shmDELETE(char *fileName);

pthread_mutex_t mux;
shared_data_type *sharedData;

// read a string from stdin protecting buffer overflow
#define GETS(B,S) {fgets(B,S-2,stdin);B[strlen(B)-1]=0;}

char ip[20];
char* ipc = ip;
const char* server_ip;

int main(){
	int i;
	pthread_t threads_id[NUM_THREADS];

	// Memoria partilhada
	// Cria e abre a zona de memoria partilhada
	int fd;
	sharedData = __shmOPEN(SHM_NAME, &fd, O_RDWR | O_CREAT);

	//Inicia os dados
	sharedData->codigo_ultima_mensagem = 150;
	sharedData->ultima_mensagem = "N/A";
	sharedData->por_a_dormir = 0;
	
	
	server_ip = ipc;

	printf("IP do SCM: ");
	fgets(ipc, 15, stdin);
	printf("\n");

	//Criação do mutex
	pthread_mutex_init(&mux, NULL);

	pthread_create(&threads_id[0], NULL, envio_mensagens, NULL);
	pthread_create(&threads_id[1], NULL, pedidos_monitorizacao, NULL);

	//Espera que as threads terminem
	for (i = 0; i < NUM_THREADS; i++){
		pthread_join(threads_id[i], NULL);
	}

	//Fechar e apagar memória partilhada
	__shmCLOSE(sharedData, fd);
	__shmDELETE(SHM_NAME);

	return 0;
}
