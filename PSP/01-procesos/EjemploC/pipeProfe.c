#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define READ 0
#define WRITE 1

int main() {
    int fd[2];
    pid_t pid;

    // Crear un pipe
    if (pipe(fd) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    // Bifurcar el proceso actual para crear un proceso hijo
    pid = fork();
    if (pid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {  // Código del proceso hijo
        int numero_recibido;
        close(fd[WRITE]);  // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer el número del pipe
        read(fd[READ], &numero_recibido, sizeof(numero_recibido));
        close(fd[READ]);  // Cerrar el descriptor de lectura después de leer

        // Imprimir el número recibido
        printf("Proceso hijo recibió el número: %d\n", numero_recibido);
        exit(EXIT_SUCCESS);

    } else {  // Código del proceso padre
        int numero_a_enviar = 42;  // Este es el número que el padre enviará al hijo
        close(fd[READ]);  // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribir el número en el pipe
        write(fd[WRITE], &numero_a_enviar, sizeof(numero_a_enviar));
        close(fd[WRITE]);  // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL); 
        printf("Proceso padre terminó\n");
    }

    return 0;
}