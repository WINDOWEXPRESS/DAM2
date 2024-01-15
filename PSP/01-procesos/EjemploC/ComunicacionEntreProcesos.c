#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define READ 0
#define WRITE 1
#define LONGITUD_CADENA 999

int main()
{
    int fd[2];
    pid_t pid;
    char *cadena_a_enviar = (char *)malloc(LONGITUD_CADENA * sizeof(char));

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
        char cadena_recibida[LONGITUD_CADENA];
        close(fd[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer la cadena del pipe
        read(fd[READ], cadena_recibida, sizeof(cadena_recibida));
        close(fd[READ]); // Cerrar el descriptor de lectura después de leer

        // Imprimir la cadena recibida
        printf("Proceso hijo recibió la cadena: %s\n", cadena_recibida);
        exit(EXIT_SUCCESS);
    }
    else
    { // Código del proceso padre

        close(fd[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        printf("Introduce una cadena: ");
        fgets(cadena_a_enviar, LONGITUD_CADENA, stdin);

        // Escribir la cadena en el pipe
        write(fd[WRITE], cadena_a_enviar, LONGITUD_CADENA);
        close(fd[WRITE]); // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL);
        printf("Proceso padre terminó\n");
    }

    free(cadena_a_enviar);
    return 0;
}