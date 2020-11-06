#include "header.h"

shared_data_type *__shmOPEN(char *fileName, int *fd, int oflag) {
    // 1. Cria e abre a zona de memoria partilhada
    *fd = shm_open(fileName, oflag, S_IRUSR | S_IWUSR);
    if (*fd == -1) {
        perror("Error at shm_open()!\n");
        exit(EXIT_FAILURE);
    }

    // 2. Define o tamanho
    ftruncate(*fd, sizeof(shared_data_type));

    // 3. Obtém um apontador para a zona de memoria partilhada
    shared_data_type *sharedData = (shared_data_type *)mmap(NULL, sizeof(shared_data_type), PROT_READ | PROT_WRITE, MAP_SHARED, *fd, 0);

    return sharedData;
}

void __shmCLOSE(shared_data_type *sharedData, int fd) {
    // Desfaz o mapeamento
    if (munmap((void *)sharedData, sizeof(shared_data_type)) < 0) {
        perror("Error at munmap()!\n");
        exit(EXIT_FAILURE);
    }
    // Fecha o ficheiro
    if (close(fd) < 0) {
        perror("Error at close()!\n");
        exit(EXIT_FAILURE);
    }
}

void __shmDELETE(char *fileName) {
    // Remove o ficheiro do sistema
    if (shm_unlink(fileName) < 0) {
        perror("Error at shm_unlink()!\n");
        exit(EXIT_FAILURE);
    }
}