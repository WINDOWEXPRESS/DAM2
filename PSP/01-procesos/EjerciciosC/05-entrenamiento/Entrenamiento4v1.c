#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
/*
 * este sin accion humano
 */

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

    pid_t pid;
    int idHijo;
    // Registrar un manejador de señales para SIGINT usando la función signal
    signal(SIGINT, sigint_handler);
    for (int i = 0; i < n; i++)
    {
        pid = fork();
        idHijo = getpid();
        // hijo
        if (pid == 0)
        {
            printf("Se ha creado hijo%d con id : %d:(Cada 2s muestra un mensaje de sigue vivo o no)\n", i, idHijo);
        }
    }
    // despues de fork el padre espera
    if (pid != 0)
    {
        wait(NULL);
    }

    // El nombre del programa a ejecutar
    char *program = "kill";

    char idHijoStr[20];  // Suficientemente grande para contener el número como cadena
    //snprintf se utiliza para convertir idHijo a una cadena de caracteres (idHijoStr), 
    //que luego se incluye en el array de argumentos. 
    snprintf(idHijoStr, sizeof(idHijoStr), "%d", idHijo);

    // Argumentos para el programa: el nombre del programa, "SIGINT", fork_id y NULL al final
    char *arguments[] = {"kill", "-SIGINT",idHijoStr , NULL};
    // bucle para mostrar mensaje
    while (1)
    {
        sleep(2);
        printf("hijo %d sigo vivo\n", idHijo);
        fflush(0);
        
        // Llamar a execvp para ejecutar el comando kill con argumentos
        execvp(program, arguments);

        // Si execvp falla, imprimirá un error
        perror("execvp");
        return 1;
    }
    return 0;
}