#ifndef HEADER_H
#define HEADER_H
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <semaphore.h>
#include <errno.h>
#include <limits.h>
#include <pthread.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/ioctl.h>
#include <netdb.h>

#define SHM_NAME "/shm_maquina2"
#define SEM_NAME "/sem_maquina2_"
#define DATA_SIZE sizeof(shared_data_type)

#define NUM_THREADS 2

#define SERVER_PORT_SCM "9030"
#define SERVER_PORT_SMM "9999"
#define BUFFER_SIZE 300

#define CODIGO_UNICO 110

#define VERSION 0

#define CODE_HELLO 0
#define CODE_MSG 1
#define CODE_CONFIG 2
#define CODE_RESET 3
#define CODE_ACK 150
#define CODE_NACK 151

typedef struct {
	char codigo_ultima_mensagem;
	char* ultima_mensagem;
	char por_a_dormir;
} shared_data_type;

extern pthread_mutex_t mux;

/**
 * Para SSL/TLS CLIENT
 **/
#include <openssl/crypto.h>
#include <openssl/ssl.h>
#include <openssl/err.h>
#include <openssl/conf.h>
#include <openssl/x509.h>


#define SERVER_SSL_CERT_FILE "./certificados/server.pem"
#define CLIENT_SSL_CERT_FILE "./certificados/client1.pem"
#define CLIENT_SSL_KEY_FILE "./certificados/client1.key"


#endif
