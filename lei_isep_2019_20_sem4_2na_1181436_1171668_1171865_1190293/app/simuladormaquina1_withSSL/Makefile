# Makefile
# Variables
INCLUDES = header.h
SOURCES = maquina.c maquina_SCMcontroller.c maquina_SCMservice.c maquina_SMMcontroller.c maquina_SMMservice.c shm_utils.c
OBJFILES = maquina.o maquina_SCMcontroller.o maquina_SCMservice.o maquina_SMMcontroller.o maquina_SMMservice.o shm_utils.o
EXEC = maquina.bin

# Suffix rules
.SUFFIXES : .c .o
.SUFFIXES : .s .o

# Transform .c into .o
.c.o:
	gcc -lrt -lm -Wall -g -c $< -lcrypto -lssl 
	
${EXEC}: ${OBJFILES}
	gcc -lrt -lm -Wall -g -o ${EXEC} ${OBJFILES} -lssl -lcrypto -lpthread -lrt
	
${OBJFILES}: ${SOURCES} ${INCLUDES}

run: ${EXEC}
	./${EXEC}

#	valgrind ./${EXEC}
	
clean:
	rm -f /dev/shm/*
	rm -f /dev/sem/*
	rm -f ${OBJFILES} ${EXEC}

#Git configurations
gitconfig:
	git config --global user.email "1171865@isep.ipp.pt"
	git config --global user.name "Maria João Dias"
	git config --global push.default simple
	git config --global credential.helper store

git:
	git add -A
	git commit -m "$m"
	git pull
	git push
	
#debug	
debug: ${EXEC}
	ddd ${EXEC} &	
