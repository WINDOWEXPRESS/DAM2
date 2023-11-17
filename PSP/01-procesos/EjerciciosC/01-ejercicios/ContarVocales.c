#include <stdio.h>
#include <string.h>
#define TAMANIO_STRING 1024
/*
int contadorVocales(char cadena)
{   int numeroVocal = 0;
    int longitud = strlen(cadena);
    for (size_t i = 0; i < longitud; i++)
    {   
        char caracter = cadena[i];
        if(caracter == 'a' || caracter == 'A' ){
            numeroVocal += 1;
        }
    }
    return numeroVocal;
}*/

int main(void)
{
    char cadena[TAMANIO_STRING];
    printf("Ingresar una cadena de caracteres:");
    fgets(cadena,sizeof(cadena),stdin);

    int longitud = strlen(cadena);

    int numeroVocal = 0;
    for (int i = 0; i < longitud; i++)
    {   
        char caracter = cadena[i];
        if(caracter == 'a' || caracter == 'A' || caracter == 'e' || caracter == 'E' || caracter == 'i' || caracter == 'I' 
            || caracter == 'o' || caracter == 'O' || caracter == 'u' || caracter == 'U' ){
            numeroVocal += 1;
            printf("%c",caracter);
        }
    }
    printf("Numero vocales : %d\n", numeroVocal);
    
    return 0;
}
