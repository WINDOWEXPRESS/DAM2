#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>

#define READ 0
#define WRITE 1
#define TAMANIO_NUMEROS_ALEATORIOS 20

int main() {
    int fd_hijo1[2];
    int fd_hijo2[2];

    // Crear pipes
    if (pipe(fd_hijo1) == -1 || pipe(fd_hijo2) == -1) {
        perror("Error al crear pipes");
        exit(EXIT_FAILURE);
    }

    // Crear un fork
    pid_t pid = fork();

    if (pid == -1) {
        perror("Error al crear el primer hijo");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) { // Primer hijo
        int numerosAleatorios_Recibido;
        close(fd_hijo1[WRITE]);

        for (size_t i = 0; i < TAMANIO_NUMEROS_ALEATORIOS; i++) {
            read(fd_hijo1[READ], &numerosAleatorios_Recibido, sizeof(numerosAleatorios_Recibido));
            printf("Primer hijo recibe par: %d\n", numerosAleatorios_Recibido);
        }

        close(fd_hijo1[READ]);
        close(fd_hijo2[WRITE]);
        close(fd_hijo2[READ]);
        exit(0);
    } else { // Proceso padre crea segundo hijo
        pid_t pid1 = fork();

        if (pid1 == -1) {
            perror("Error al crear el segundo hijo");
            exit(EXIT_FAILURE);
        } else if (pid1 == 0) { // Segundo hijo
            int numerosAleatorios_Recibido;
            close(fd_hijo2[WRITE]);

            for (size_t i = 0; i < TAMANIO_NUMEROS_ALEATORIOS; i++) {
                read(fd_hijo2[READ], &numerosAleatorios_Recibido, sizeof(numerosAleatorios_Recibido));
                printf("Segundo hijo recibe impar: %d\n", numerosAleatorios_Recibido);
            }

            close(fd_hijo2[READ]);
            close(fd_hijo1[WRITE]);
            close(fd_hijo1[READ]);
            exit(0);
        }

        int numerosAleatorios;
        srand(time(NULL));

        for (int i = 0; i < TAMANIO_NUMEROS_ALEATORIOS; i++) {
            numerosAleatorios = rand() % 100;

            if (numerosAleatorios % 2 == 0) {
                write(fd_hijo1[WRITE], &numerosAleatorios, sizeof(numerosAleatorios));
            } else {
                write(fd_hijo2[WRITE], &numerosAleatorios, sizeof(numerosAleatorios));
            }

            printf("Número aleatorio %d: %d\n", i, numerosAleatorios);
        }

        close(fd_hijo1[READ]);
        close(fd_hijo2[READ]);
        close(fd_hijo1[WRITE]);
        close(fd_hijo2[WRITE]);

        // Esperar a que ambos hijos terminen
        wait(NULL);
        wait(NULL);
    }

    return 0;
}
