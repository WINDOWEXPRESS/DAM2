#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid1, pid2;

    // Primer fork
    pid1 = fork();
    if (pid1 == -1) {
        perror("Error en el primer fork");
        exit(EXIT_FAILURE);
    }

    if (pid1 == 0) {
        // Código del primer hijo
        printf("Soy el primer hijo, PID: %d\n", getpid());
        // ... (acciones del primer hijo)
        exit(EXIT_SUCCESS);
    } else {
        // Código del padre
        // Segundo fork
        pid2 = fork();
        if (pid2 == -1) {
            perror("Error en el segundo fork");
            exit(EXIT_FAILURE);
        }

        if (pid2 == 0) {
            // Código del segundo hijo
            printf("Soy el segundo hijo, PID: %d\n", getpid());
            // ... (acciones del segundo hijo)
            exit(EXIT_SUCCESS);
        } else {
            // Código del padre después de las bifurcaciones
            // ... (acciones del padre)
            // Esperar a que ambos hijos terminen
            wait(NULL);
            wait(NULL);
            printf("Proceso padre terminó\n");
            printf("getpid(): %d,getppid(): %d\n",getpid(),getppid());
        }
    }

    return 0;
}
