#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int main() {
    int fd[2];  // Descriptores de archivo para el pipe
    pid_t pid;

    // Crear un pipe
    if (pipe(fd) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    pid = fork();
    if (pid < 0) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {  // Proceso hijo
        close(fd[0]);  // Cerrar el extremo de lectura del pipe

        // Redirigir la salida estándar al extremo de escritura del pipe
        dup2(fd[1], STDOUT_FILENO);
        close(fd[1]);

        // Datos para execvp: comando "ls" con argumentos "-l" y "-a"
        char *cmd = "ls";
        char *args[] = {"ls", "-l", "-a", NULL};
        
        execvp(cmd, args);
        perror("execvp");  // Se ejecutará solo si execvp falla
        exit(EXIT_FAILURE);
    } else {  // Proceso padre
        char buffer[1024];
        ssize_t bytes;

        close(fd[1]);  // Cerrar el extremo de escritura del pipe
        /*
        // Leer la salida del comando
        while ((bytes = read(fd[0], buffer, sizeof(buffer)-1)) > 0) {
            buffer[bytes] = '\0';  // Asegurarse de que es una cadena terminada en NULL
            printf("%s", buffer);
        }
        */
        read(fd[0], &buffer, sizeof(buffer));
        printf("mENSJA:%s", buffer);
        
        close(fd[0]);  // Cerrar el extremo de lectura del pipe
        wait(NULL);    // Esperar a que el proceso hijo termine
    }

    return 0;
}