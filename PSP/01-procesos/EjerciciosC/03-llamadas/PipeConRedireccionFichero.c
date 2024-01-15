#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>  // Incluir esta cabecera para las constantes de control de apertura de archivos

#define READ 0
#define WRITE 1

int main() {
    int fd[2];
    pid_t pid;

    // Crear un pipe
    if (pipe(fd) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    // Bifurcar el proceso actual para crear un proceso hijo
    pid = fork();
    if (pid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {  // Código del proceso hijo
        // Abrir un archivo para escritura
        int file = open("salida.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);

        if (file < 0)
        {
            perror("Open");
            return 1;
        }

        int numero_recibido;
        char salida[100];  // Buffer para el mensaje de salida
        close(fd[WRITE]);  // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer el número del pipe
        read(fd[READ], &numero_recibido, sizeof(numero_recibido));
        
        close(fd[READ]);  // Cerrar el descriptor de lectura después de leer

        dup2(file, STDOUT_FILENO);

        // Construir el mensaje de salida
        sprintf(salida, "El numero %d al cubo es %d\n", numero_recibido, numero_recibido * numero_recibido * numero_recibido);

        // Imprimir el número recibido
        printf("Proceso hijo recibió el número: %d\n", numero_recibido);
        printf("Mensaje : %s", salida);

    } else {  // Código del proceso padre
        int numero_a_enviar;

        printf("Ingrese un número: ");
        scanf("%d", &numero_a_enviar);  // El número que el padre enviará al hijo

        close(fd[READ]);  // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribir el número en el pipe
        write(fd[WRITE], &numero_a_enviar, sizeof(numero_a_enviar));
        close(fd[WRITE]);  // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL); 
        printf("Proceso padre terminó\n");
    }

    return 0;
}
