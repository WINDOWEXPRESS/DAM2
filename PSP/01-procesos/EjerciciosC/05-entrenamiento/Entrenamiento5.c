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
    else if (pid > 0)
    { // padre
        // Espera 5s para ver si funciona
        sleep(5);

        // Argumentos para el programa: el nombre del programa, "SIGUSR1", fork_id y NULL al final
        char idHijoStr[20]; // Suficientemente grande para contener el número como cadena
        sprintf(idHijoStr, "%d", pid);

        // El nombre del programa a ejecutar
        char *program = "kill";

        // Argumentos para el programa: el nombre del programa, "SIGUSR1", fork_id y NULL al final
        char *arguments[] = {"kill", "-SIGUSR1", idHijoStr, NULL};

        // Llamar a execvp para ejecutar el comando kill con argumentos
        execvp(program, arguments);
        
        // Si execvp falla, imprimirá un error
        perror("execvp");
        return 1;
    }
    else
    {
        // Manejar error al crear el proceso hijo
        perror("fork");
        return 1;
    }

    return 0;
}
