#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h> // Necesario para la función time()
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>//para uso de open()

#define READ 0
#define WRITE 1

void ordenarNumeros(int *a, int *b, int *c) {
    int temp;
    if (*a < *b) {
        temp = *a;
        *a = *b;
        *b = temp;
    }
    if (*b < *c) {
        temp = *b;
        *b = *c;
        *c = temp;
    }
    if (*a < *b) {
        temp = *a;
        *a = *b;
        *b = temp;
    }
}

 int main(int argc, char const *argv[])
 {
    __pid_t pid;
    int fd[2];

    //Crear un pipe
    if(pipe(fd) == -1){
        perror("pipe Error");
                exit(EXIT_FAILURE);
    }

    // Bifurcar el proceso actual para crear un proceso hijo
    pid = fork();
    if(pid == -1){
        perror("fork Error");
        exit(EXIT_FAILURE);
    }

    // Código del proceso hijo
    if(pid == 0){
        int numeroAleatorio1_recibido  ;
        int numeroAleatorio2_recibido  ;
        int numeroAleatorio3_recibido ;
         // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        close(fd[WRITE]);

        // Leer los numeros del pipe
        read(fd[READ], &numeroAleatorio1_recibido, sizeof(numeroAleatorio1_recibido));
        read(fd[READ], &numeroAleatorio2_recibido, sizeof(numeroAleatorio2_recibido));
        read(fd[READ], &numeroAleatorio3_recibido, sizeof(numeroAleatorio3_recibido));
        close(fd[READ]); // Cerrar el descriptor de lectura después de leer
        ordenarNumeros(&numeroAleatorio1_recibido,&numeroAleatorio2_recibido,&numeroAleatorio3_recibido);
/*
        printf("Número aleatorio: %d\n", numeroAleatorio1_recibido);
        printf("Número aleatorio: %d\n", numeroAleatorio2_recibido);
        printf("Número aleatorio: %d\n", numeroAleatorio3_recibido);
*/
        int file_descriptor = open ("salida.txt", O_WRONLY | O_CREAT | O_TRUNC, 0666); 
        dup2(file_descriptor,STDOUT_FILENO);
        printf("%d < %d < %d\n",numeroAleatorio1_recibido,numeroAleatorio2_recibido,numeroAleatorio3_recibido);
        close(file_descriptor);
        exit(0);
    }else{
            // Inicializar la semilla con el tiempo actual
        srand(time(NULL));
        int numeroAleatorio1 = rand() %100 ;
        int numeroAleatorio2 = rand() %100 ;
        int numeroAleatorio3 = rand() %100 ;
         // El padre no lee en el pipe, así que cerramos el descriptor de lectura
        close(fd[READ]);
        
        printf("Número aleatorio: %d\n", numeroAleatorio1);
        printf("Número aleatorio: %d\n", numeroAleatorio2);
        printf("Número aleatorio: %d\n", numeroAleatorio3);
        
        // escribir los numeros del pipe
        write(fd[WRITE], &numeroAleatorio1, sizeof(numeroAleatorio1));
        write(fd[WRITE], &numeroAleatorio2, sizeof(numeroAleatorio2));
        write(fd[WRITE], &numeroAleatorio3, sizeof(numeroAleatorio3));
        close(fd[WRITE]); // Cerrar el descriptor de lectura después de leer

    }
    wait(0);
    /* code */
    return 0;
 }
 