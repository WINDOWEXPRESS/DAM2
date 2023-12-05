#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#define LONGITUD 1000
#define CANTIDAD_HIJO 3

// Función de controlador de señal para SIGINT
void sigint_handler(int signo)
{
    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
    if (signo == SIGUSR1)
    {
        printf("\nSoy el hijo %d . He sido eliminado\n", getpid());
    }

    if (signo == SIGINT)
    {
        printf("\nSoy el hijo %d . He sido eliminado Por la señal SIGINT\n", getpid());
        exit(0);
    }
}

int main(int argc, char *argv[])
{
    // Registrar un manejador de señales para SIGINT usando la función signal
    signal(SIGUSR1, sigint_handler);
    signal(SIGINT, sigint_handler);
    if (argc != 2)
    {
        fprintf(stderr, "Falta parametro por comando : %s <numero_señales>\n", argv[0]);
        return 1;
    }
    // atoi metodo para pasar un string a int
    int cantidad = atoi(argv[1]);

    pid_t hijos[LONGITUD];

    pid_t pid;
    for (size_t i = 0; i < CANTIDAD_HIJO; i++)
    {
        pid = fork();
        hijos[i] = pid;
        // GUARDAR EL ID EN ARRAY
        if (pid == 0)
        { // hijo
            while (1)
            {
                sleep(1);
            }
        }
    }

    { // padre
        for (int i = 0; i < CANTIDAD_HIJO; i++)
        {
            printf("posicion %d iD hijo : %d\n", i, hijos[i]);
        }

        // ESpera 3s para  los hijos esten preparado
        sleep(3);

        // Inicializar la semilla con el tiempo actual
        srand(time(NULL));
        for (int i = 0; i < cantidad; i++)
        {
            int hijoAleatorio = rand() % CANTIDAD_HIJO;
            printf("hijoAleatorio : %d\n",hijoAleatorio);
            // Usar metodo kill para enviar señales a los hijos
            kill(hijos[hijoAleatorio], SIGUSR1);
        }

        char *program = "killall";
        char *arguments[] = {"killall", "-SIGINT","Ejercicio2", NULL};
        execvp(program, arguments);
        
        // Redirigir la salida estándar al archivo
        // dup2(execvp(program, arguments), STDOUT_FILENO);  // STDOUT_FILENO es una constante que representa la salida estándar

        return 0;
    }
}