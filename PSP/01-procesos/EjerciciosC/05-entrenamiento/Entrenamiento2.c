#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h> // Necesario para la función time()
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h> //para uso de open()
#define READ 0
#define WRITE 1
#define TAMANIO_NUMEROS_ALEATORIOS 20

int main(int argc, char const *argv[])
{
    int fd_hijo1[2];
    int fd_hijo2[2];
    // Crear un pipe
    if (pipe(fd_hijo1) == -1)
    { // Mensaje si produce error
        perror("pipe Error");
        exit(EXIT_FAILURE);
    }
    if (pipe(fd_hijo2) == -1)
    { // Mensaje si produce error
        perror("pipe Error");
        exit(EXIT_FAILURE);
    }
    // Crear un fork
    pid_t pid = fork();
    if (pid == -1)
    { // Mensaje si produce error
        perror("pid Error");
        exit(EXIT_FAILURE);
    }
    
    if (pid == 0)
    {// Código del primer hijo
        int numerosAleatorios_Recibido[TAMANIO_NUMEROS_ALEATORIOS];
        // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        close(fd_hijo1[WRITE]);
        
        read(fd_hijo1[READ], &numerosAleatorios_Recibido, sizeof(numerosAleatorios_Recibido));
        close(fd_hijo1[READ]); // Cerrar el descriptor de lectura después de leer
        for (size_t i = 0; i < TAMANIO_NUMEROS_ALEATORIOS; i++)
        {
            printf("Primer hijo recibe par : %d\n", numerosAleatorios_Recibido[i]);
        }
        
        exit(0);
    }
    else
    {
        // Proceso padre crea seguundo hijo
        pid_t pid1 = fork();
        if (pid1 == -1)
        {

            perror("pid1 Error");
            exit(EXIT_FAILURE);
        }
        else if (pid1 == 0)
        {
            int numerosAleatorios_Recibido[TAMANIO_NUMEROS_ALEATORIOS];
            // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
            close(fd_hijo2[WRITE]);

            read(fd_hijo2[READ], &numerosAleatorios_Recibido, sizeof(numerosAleatorios_Recibido));
            close(fd_hijo2[READ]); // Cerrar el descriptor de lectura después de leer
            for (size_t i = 0; i < TAMANIO_NUMEROS_ALEATORIOS; i++)
            {
                printf("Segundo hijo recibe impar : %d\n", numerosAleatorios_Recibido[i]);
            }
            exit(0);
        }

        int numerosAleatorios[TAMANIO_NUMEROS_ALEATORIOS];
        srand(time(NULL));

        for (size_t i = 0; i < TAMANIO_NUMEROS_ALEATORIOS; i++)
        { // Guardar Nuemros aleaatorios entre 0 100
            numerosAleatorios[i] = rand() % 100;
        }
        
        for (int i = 0; i < TAMANIO_NUMEROS_ALEATORIOS; i++)
        {
            printf("numero %d : %d\n", i, numerosAleatorios[i]);
        }

        close(fd_hijo1[READ]);
        close(fd_hijo2[READ]);

        for (int i = 0; i < TAMANIO_NUMEROS_ALEATORIOS; i++)
        {
            if (numerosAleatorios[i] % 2 == 0)
            {
                write(fd_hijo1[WRITE], &numerosAleatorios[i], sizeof(numerosAleatorios));
            }
            else
            {
                write(fd_hijo2[WRITE], &numerosAleatorios[i], sizeof(numerosAleatorios));
            }
        }
        //write(fd[WRITE], &numerosAleatorios, sizeof(numerosAleatorios));
        close(fd_hijo1[WRITE]);
        close(fd_hijo2[WRITE]);
            wait(null);
    wait(null);
    }

    return 0;
}
