#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

#define READ 0
#define WRITE 1

int main(int argc, char const *argv[])
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
        int numero_recibido;
        int numero1_recibido;
        char operador_recibido;
        close(fd[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer el número del pipe
        read(fd[READ], &numero_recibido, sizeof(numero_recibido));
        read(fd[READ], &numero1_recibido, sizeof(numero1_recibido));
        read(fd[READ], &operador_recibido, sizeof(operador_recibido));
        close(fd[READ]); // Cerrar el descriptor de lectura después de leer

         int resultado;
        if (operador_recibido == '+')
        {
            resultado = numero_recibido + numero1_recibido;
        }
        else if (operador_recibido == '-')
        {
            resultado = numero_recibido - numero1_recibido;
        }
        else
        {
            printf("Operador no válido\n");
            exit(EXIT_FAILURE);
        }

        // Imprimir el número recibido
        printf("El resultado es : %d %c %d = %d\n", numero_recibido, operador_recibido, numero1_recibido, resultado);
        exit(EXIT_SUCCESS);
    }
    else
    { // Código del proceso padre
        int numero_a_enviar;
        int numero1_a_enviar;
        char operador_a_enviar;

        printf("Ingrese el primer numero: ");
        fscanf(stdin, "%d", &numero_a_enviar);
        printf("Ingrese el sugundo numero: ");
        scanf("%d", &numero1_a_enviar);
        printf("Ingrese el operador '+' o '-' : ");
        scanf(" %c", &operador_a_enviar);
        // Nota: El espacio antes del %c se usa para ignorar espacios en blanco
        //(como saltos de línea) que puedan quedar en el búfer de entrada.

        close(fd[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribir en el pipe
        if (write(fd[WRITE], &numero_a_enviar, sizeof(numero_a_enviar)) == -1 ||
            write(fd[WRITE], &numero1_a_enviar, sizeof(numero1_a_enviar)) == -1 ||
            write(fd[WRITE], &operador_a_enviar, sizeof(operador_a_enviar)) == -1)
        {
            perror("write");
            exit(EXIT_FAILURE);
        }
        close(fd[WRITE]); // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL);
        printf("Proceso padre terminó\n");
    }

    return 0;
}
