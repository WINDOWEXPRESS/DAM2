#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// Función de controlador de señal para SIGINT
void sigint_handler(int signo)
{
    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
    if (signo == SIGUSR1)
    {
        printf("\nRecibido y terminará el proceso\n");
    }
    else
    {
        printf("\nSenial no es SIGUSR1\n");
    }
    exit(0);
}

int main(int argc, char *argv[])
{
    // Registrar un manejador de señales para SIGINT usando la función signal
    signal(SIGUSR1, sigint_handler);

    pid_t pid;

    if ((pid = fork()) == 0)
    { // hijo
        while (1)
        {
            // forzar el envio de buffer
            printf(".");
            fflush(stdout);
            sleep(1);
        }
    }
    else
    { // padre

        // ESpera 5s para  ver si funciona
        sleep(5);

        // El nombre del programa a ejecutar
        char *program = "kill";

        char idHijoStr[20]; // Suficientemente grande para contener el número como cadena
        // snprintf se utiliza para convertir idHijo a una cadena de caracteres (idHijoStr),
        // que luego se incluye en el array de argumentos.
        snprintf(idHijoStr, sizeof(idHijoStr), "%d", pid);

        // Argumentos para el programa: el nombre del programa, "SIGINT", fork_id y NULL al final
        char *arguments[] = {"kill", "-SIGUSR1", idHijoStr, NULL};

        // Llamar a execvp para ejecutar el comando kill con argumentos
        execvp(program, arguments);
        // Si execvp falla, imprimirá un error
        perror("execvp");
        return 1;
    }

    return 0;
}