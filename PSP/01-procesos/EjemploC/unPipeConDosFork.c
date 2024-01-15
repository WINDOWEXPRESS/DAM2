#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int fd[2]; // array para los descriptores de archivo del pipe
    char mensaje_padre[] = "Hola, hijos!";
    char mensaje_hijo1[20];
    char mensaje_hijo2[20];

    if (pipe(fd) == -1) {
        perror("Error al crear el pipe");
        exit(EXIT_FAILURE);
    }

    pid_t pid_hijo1 = fork();

    if (pid_hijo1 == -1) {
        perror("Error al crear el proceso hijo 1");
        exit(EXIT_FAILURE);
    }

    if (pid_hijo1 == 0) { // Código del primer hijo
        close(fd[1]); // El hijo cierra el descriptor de escritura

        read(fd[0], mensaje_hijo1, sizeof(mensaje_hijo1));
        printf("Primer Hijo: Mensaje recibido del padre: %s\n", mensaje_hijo1);

        close(fd[0]);
        exit(EXIT_SUCCESS);
    } else { // Código del padre
        pid_t pid_hijo2 = fork();

        if (pid_hijo2 == -1) {
            perror("Error al crear el proceso hijo 2");
            exit(EXIT_FAILURE);
        }

        if (pid_hijo2 == 0) { // Código del segundo hijo
            close(fd[1]); // El hijo cierra el descriptor de escritura

            read(fd[0], mensaje_hijo2, sizeof(mensaje_hijo2));
            printf("Segundo Hijo: Mensaje recibido del padre: %s\n", mensaje_hijo2);

            close(fd[0]);
            exit(EXIT_SUCCESS);
        } else { // Código del padre
            close(fd[0]); // El padre cierra el descriptor de lectura

            write(fd[1], mensaje_padre, sizeof(mensaje_padre));
            printf("Padre: Mensaje enviado a los hijos\n");

            close(fd[1]);
            wait(NULL);
            wait(NULL);
        }
    }

    return 0;
}
