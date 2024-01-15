#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// Función de controlador de señal para SIGINT
void sigint_handler(int signo)
{
    printf("Se recibió la señal SIGINT (Ctrl + C) a hijo :%d\n", getpid());
    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
    exit(0);
}

int main(int argc, char *argv[])
{

    if (argc != 2)
    {
        fprintf(stderr, "Falta parametro por comando : %s <numero_hijos>\n", argv[0]);
        return 1;
    }
    // atoi metodo para pasar un string a int
    int n = atoi(argv[1]);

    if (n <= 0)
    {
        fprintf(stderr, "El número de hijos debe ser un entero positivo.\n");
        return 1;
    }

    // Registrar un manejador de señales para SIGINT usando la función signal
    signal(SIGINT, sigint_handler);
    
    pid_t pid;    
    for (int i = 0; i < n; i++)
    {
        pid = fork();
        // hijo
        if (pid == 0)
        {
            printf("Se ha creado hijo%d con id : %d:(Cada 2s muestra un mensaje de sigue vivo o no)\n", i, getpid());
        }
    }
    //despues de fork el padre espera 
    if (pid != 0)
    {
        wait(NULL);
    }
    //bucle para mostrar mensaje 
    while (1)
    {
        sleep(2);
        printf("hijo %d sigo vivo\n", getpid());
        fflush(0);
    }
    return 0;
}