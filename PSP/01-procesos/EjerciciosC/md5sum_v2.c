#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include "GeneratorMD5.c"

#define ASCII_a 97
#define ASCII_n 109
#define ASCII_z 122
#define LONGITUD_CHAR 5

long long descifra(pid_t hijo, char *password)
{
    unsigned char result[EVP_MAX_MD_SIZE];
    unsigned int result_len;

    char *md5[] = {"582fc884d6299814fbd4f12c1f9ae70f",
                   "74437fabd7c8e8fd178ae89acbe446f2",
                   "28ea19352381b8659df830dd6d5c90a3",
                   "90f077d7759d0d4d21e6867727d4b2bd"};

    for (char j = ASCII_a; j <= ASCII_z; j++)
    {
        password[1] = j;
        for (char k = ASCII_a; k <= ASCII_z; k++)
        {
            password[2] = k;
            for (char l = ASCII_a; l <= ASCII_z; l++)
            {
                password[3] = l;
                // printf("%s\n", password);
                generateMD5(password, result);

                for (int i = 0; i < sizeof(md5) / sizeof(md5[0]); i++)
                {
                    if (0 == strcmp(result, md5[i]))
                    {
                        printf("%s MD5: %s \n", password, result);
                    }
                }
            }
        }
    }
    exit(0);
}

int main(int arc, char *argv[])
{
    int contador = 0;
    pid_t hijo;
    char password[LONGITUD_CHAR];
    // Variables para almacenar el tiempo de inicio y fin
    clock_t start, end;
    double cpu_time_used;


    //start = clock(); // Guardamos el tiempo de inicio
    for (char i = ASCII_a; i <= ASCII_z; i++)
    {
        password[0] = i;
        start = clock(); // Guardamos el tiempo de inicio
        hijo = fork();
        if (hijo == 0)
        {
            descifra(hijo, password);
        }
        
        // Ejecutamos la tarea cuyo tiempo de ejecución queremos medir

        end = clock(); // Guardamos el tiempo de finalización

        // Calculamos el tiempo de ejecución en segundos
        // Tomamos la diferencia entre el tiempo de finalización y el tiempo de inicio
        // Luego dividimos por CLOCKS_PER_SEC para obtener el tiempo en segundos
        cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;

        contador++;

       printf("%d Tiempo de ejecución %d : %f segundos\n", contador, hijo, cpu_time_used);

    }
    /*
        end = clock(); // Guardamos el tiempo de finalización
    
        // Calculamos el tiempo de ejecución en segundos
        // Tomamos la diferencia entre el tiempo de finalización y el tiempo de inicio
        // Luego dividimos por CLOCKS_PER_SEC para obtener el tiempo en segundos
        cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;
        printf("%d Tiempo de ejecución %d : %f segundos\n", contador, hijo, cpu_time_used);
    */
    return 0;
}