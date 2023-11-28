#include <stdio.h>
#include <signal.h>
#include <sys/types.h>

// Declaramos los prototipos de funciones
void manejador(int signum);

// Variable global
int bandera = 1;

// Función principal
int main(int argc, char **argv)
{
    // Declaramos variables
    int status, pid;

    if ((pid = fork()) == 0)
    {
        printf("Soy hijo y espero una señal de mi padre\n");
        printf("mi pid es: %d\n", getpid());
        signal(SIGUSR1, manejador);
        
        kill(getppid(), SIGUSR2);
    }
    else
    {
        signal(SIGUSR2, manejador);
        printf("Soy Padre, mi pid es: %d\n", getpid());
        sleep(10);
        kill(pid, SIGUSR1);
        wait(&status); // Esperamos que termine el hijo
        printf("Mi hijo termino con un estado: %d\n", status);
    }
}

void manejador(int signum)
{
    if (signum == SIGUSR1)
    {
        printf("Recibi una senal de mi padre %d\n", signum);
    }
    else
    {
        printf("Recibi una senal de mi hijo %d\n", signum);
    }
    bandera = 0;
}
