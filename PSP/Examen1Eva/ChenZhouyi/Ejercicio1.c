#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdbool.h>

#define READ 0
#define WRITE 1

#define RANGO_LONGITUD_MINIMO 1
#define MULTIPLE_DIEZ 10
#define RANGO_LONGITUD_MAXIMO 9
#define DIVIDIR_EN_DOS 2
/*
    ASUMO QUE LA CANTIDAD INTRODUCIDA ES PAR
*/

// Función para verificar si un número es primo
bool esPrimo(int numero)
{
    if (numero <= 1)
    {
        return false; // No es primo
    }

    for (int i = 2; i * i <= numero; i++)
    {
        if (numero % i == 0)
        {
            return false; // No es primo
        }
    }

    return true; // Es primo
}

int main(int argc, char const *argv[])
{
    if (argc != 3)
    {
        fprintf(stderr, "Falta parametro por comando : %s <Longitud_numero> <Cantidad_numeros>\n", argv[0]);
        return 1;
    }
    // atoi metodo para pasar un string a int
    int longitud = atoi(argv[1]);
    int cantidad = atoi(argv[2]);

    if (longitud <= 0 || cantidad <= 0)
    {
        fprintf(stderr, "Debe ser un entero positivo.\n");
        return 1;
    }

    int longitud_minimo = RANGO_LONGITUD_MINIMO;
    int longitud_maximo = RANGO_LONGITUD_MAXIMO;

    for (size_t i = 2; i <= longitud; i++)
    {
        longitud_minimo *= (MULTIPLE_DIEZ);
        longitud_maximo += (RANGO_LONGITUD_MAXIMO * longitud_minimo);
    }

    // Inicializar la semilla con el tiempo actual
    srand(time(NULL));

    int pipePadreEscribirHijo1[2];
    int pipePadreEscribirHijo2[2];

    pid_t pidHijo1;
    pid_t pidHijo2;

    // Crear un pipe
    if (pipe(pipePadreEscribirHijo1) == -1)
    {
        perror("pipePadreEscribirHijo1 fallo");
        exit(EXIT_FAILURE);
    }
    // Crear un pipe
    if (pipe(pipePadreEscribirHijo2) == -1)
    {
        perror("pipePadreEscribirHijo2 fallo");
        exit(EXIT_FAILURE);
    }

    // Bifurcar el proceso actual para crear un proceso hijo
    pidHijo1 = fork();
    if (pidHijo1 == -1)
    {
        perror("fork fallo");
        exit(EXIT_FAILURE);
    }

    if (pidHijo1 == 0)
    { // Código del proceso hijo
        int numero_recibido;
        close(pipePadreEscribirHijo1[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        for (int i = 0; i < cantidad / DIVIDIR_EN_DOS; i++)
        {
            // Leer el número del pipe
            read(pipePadreEscribirHijo1[READ], &numero_recibido, sizeof(numero_recibido));

            printf("Soy el hijo 1 con id %d y he recibido numero %d y %s\n", getpid(), numero_recibido, (esPrimo(numero_recibido) ? "es primo" : "no es primo"));
            if (esPrimo(numero_recibido))
            {
                close(pipePadreEscribirHijo2[WRITE]); // El hijo no escribirá en el pipe, así que cerramos
                close(pipePadreEscribirHijo2[READ]);  // El hijo no leera en el pipe, así que cerramos
                close(pipePadreEscribirHijo1[READ]);  // Cerrar el descriptor de lectura después de leer
                exit(0);
            }
        }
    }
    else
    { // Código del proceso padre
        // Crear el segundo fork
        pidHijo2 = fork();

        if (pidHijo2 == -1)
        {
            perror("fork fallo");
            exit(EXIT_FAILURE);
        }

        if (pidHijo2 == 0)
        { // Código del proceso hijo
            int numero_recibido;
            bool primo = false;
            close(pipePadreEscribirHijo1[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
            close(pipePadreEscribirHijo2[WRITE]); // El hijo no escribirá en el pipe, así que cerramos
            close(pipePadreEscribirHijo1[READ]);  // El hijo no leera en el pipe, así que cerramos
            for (size_t i = 0; i < cantidad / DIVIDIR_EN_DOS; i++)
            {
                // Leer el número del pipe
                read(pipePadreEscribirHijo2[READ], &numero_recibido, sizeof(numero_recibido));

                primo = esPrimo(numero_recibido);
                printf("Soy el hijo 2 con id %d y he recibido numero %d y %s\n", getpid(), numero_recibido, (esPrimo(numero_recibido) ? "es primo" : "no es primo"));
            if (esPrimo(numero_recibido))
            {
                close(pipePadreEscribirHijo2[WRITE]); // El hijo no escribirá en el pipe, así que cerramos
                close(pipePadreEscribirHijo2[READ]);  // El hijo no leera en el pipe, así que cerramos
                close(pipePadreEscribirHijo1[READ]);  // Cerrar el descriptor de lectura después de leer
                exit(0);
            }
            }
        }
        else
        {
            // Código del proceso padre
            int numero_a_enviar;

            close(pipePadreEscribirHijo1[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
            close(pipePadreEscribirHijo2[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

            // ENVIAR PRIMER LA MITAD A PRIMER HIJO
            for (int i = 0; i < cantidad; i++)
            {
                if (i % 2 == 0)
                {
                    numero_a_enviar = (rand() % longitud_maximo - longitud_minimo) + longitud_minimo;
                    printf("el numero %d  : %d\n", i, numero_a_enviar);
                    write(pipePadreEscribirHijo1[WRITE], &numero_a_enviar, sizeof(numero_a_enviar));
                }
                else
                {
                    numero_a_enviar = (rand() % longitud_maximo - longitud_minimo) + longitud_minimo;
                    printf("el numero %d  : %d\n", i, numero_a_enviar);
                    write(pipePadreEscribirHijo2[WRITE], &numero_a_enviar, sizeof(numero_a_enviar));
                }
            }

            // Esperar a que el proceso hijo termine
            ;
            printf("el hijo %d ha muerto \n", wait(NULL));
            // tuberia pipeHijoEscribir
            close(pipePadreEscribirHijo1[WRITE]);
            close(pipePadreEscribirHijo2[WRITE]);

            // Imprimir el resultado recibido

            printf("Proceso padre terminó\n");
        }
    }
    return 0;
}
