#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#define SIZE 512
#define READ 0
#define WRITE 1
int main(void)
{//HECHO PROPIO PELIN COMPLICADO
    pid_t pid;
    int p[2], readbytes;
    char buffer[SIZE];

    pipe(p); // Se crea la tubería
    if ((pid = fork()) == 0)
    {                // hijo
        close(p[WRITE]); /* cerramos el lado de escritura del pipe */

        while ((readbytes = read(p[0], buffer, SIZE)) > 0)
            write(1, buffer, readbytes); // Pantalla

        close(p[READ]);
    }
    else
    {                // padre
        close(p[READ]); /* cerramos el lado de lectura del pipe */
        strcpy(buffer, "Esto llega a traves de la tuberia\n");
        write(p[WRITE], buffer, strlen(buffer));

        close(p[WRITE]);
    }
    waitpid(pid, NULL, 0);
    exit(0);
}
/*      EL EJEMPLO MAS FACIL DE ENTENDER    GPT
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int fd[2]; // array para los descriptores de archivo del pipe
    char mensaje[] = "Hola, mundo!";

    if (pipe(fd) == -1) {
        perror("Error al crear el pipe");
        exit(EXIT_FAILURE);
    }

    pid_t pid = fork();

    if (pid == -1) {
        perror("Error al crear el proceso hijo");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) { // Proceso hijo
        close(fd[1]); // El hijo cierra el descriptor de escritura

        char buffer[20];
        read(fd[0], buffer, sizeof(buffer));
        printf("Hijo: Mensaje recibido del padre: %s\n", buffer);

        close(fd[0]);
        exit(EXIT_SUCCESS);
    } else { // Proceso padre
        close(fd[0]); // El padre cierra el descriptor de lectura

        write(fd[1], mensaje, sizeof(mensaje));
        printf("Padre: Mensaje enviado al hijo\n");

        close(fd[1]);
        wait(NULL);
    }

    return 0;
}
*/