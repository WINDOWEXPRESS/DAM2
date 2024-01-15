#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <math.h>
#include <fcntl.h>

#define READ 0
#define WRITE 1
#define LONGITUD_CADENA 999

int main()
{
    int fd[2];
    pid_t pid;

    // Crear un pipe
    if (pipe(fd) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    // Bifurcar el proceso actual para crear un proceso hijo
    pid = fork();
    if (pid == -1)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0)
    { // Código del proceso hijo
        int numero_recibida;

        close(fd[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer la cadena del pipe
        read(fd[READ], &numero_recibida, sizeof(numero_recibida));
        close(fd[READ]); // Cerrar el descriptor de lectura después de leer

        int numero_elevado = pow(numero_recibida, 2);

        // Redirigir la salida estándar al archivo "output.txt"
        int file_descriptor = open("salida.txt", O_WRONLY | O_CREAT | O_TRUNC, 0666);
        dup2(file_descriptor, STDOUT_FILENO);
        close(file_descriptor);

        // Imprimir la cadena recibida
        printf("Proceso hijo recibió el numero : %d y su elevado es %d\n", numero_recibida, numero_elevado);
        exit(EXIT_SUCCESS);
    }
    else
    { // Código del proceso padre
        int     numero_a_enviar;
        close(fd[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        printf("Introduce un numero para elevar: ");
        scanf("%d",&numero_a_enviar);

        // Escribir la cadena en el pipe
        write(fd[WRITE], numero_a_enviar, sizeof(numero_a_enviar));
        close(fd[WRITE]); // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL);
        printf("Proceso padre terminó\n");
    }

    return 0;
}