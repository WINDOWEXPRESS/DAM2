#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int main()
{
    pid_t child1, child2;

    child1 = fork();
    child2 = fork();
    printf("Soy el primer hijo, child1 ID es %d y el ID de child2 es %d\t\tMi ID es %d y el ID de mi padre es %d\n"
            , child1, child2, getpid(), getppid());
    /*
        // Crear el primer hijo
        child1 = fork();

        if (child1 < 0) {
            perror("Error al crear el primer hijo");
            exit(EXIT_FAILURE);
        } else if (child1 == 0) {
            // Código del primer hijo
            printf("Soy el primer hijo, mi ID es %d y el ID de mi padre es %d\n", getpid(), getppid());
            exit(EXIT_SUCCESS);
        } else {
            // Estamos en el proceso padre
            // Crear el segundo hijo
            child2 = fork();

            if (child2 < 0) {
                perror("Error al crear el segundo hijo");
                exit(EXIT_FAILURE);
            } else if (child2 == 0) {
                // Código del segundo hijo
                printf("Soy el segundo hijo, mi ID es %d y el ID de mi padre es %d\n", getpid(), getppid());
                exit(EXIT_SUCCESS);
            } else {
                // Estamos nuevamente en el proceso padre
                // Esperar a que ambos hijos terminen
                wait(NULL);
                wait(NULL);
                printf("Proceso padre terminando.\n");
            }
        }
    */
    return 0;
}
