#include<stdio.h>
#include<string.h>
#include<ctype.h>
#define TAMANIO 1024

int main(void)
{   //declarar array de char de tamanio fijo
    char cadena[TAMANIO];
    char cadenaInversa[TAMANIO];

    //obetener cadena desde entrada
    printf("Ingresar una cadena de caracteres: ");
    fgets(cadena,sizeof(cadena),stdin);

    // Eliminar espacios de la cadena
    int i,contadorEspacio = 0;
    for (i = 0; i < strlen(cadena); i++)
    {
        if(cadena[i] != ' '){
            cadena[contadorEspacio] = tolower(cadena[i]);
            contadorEspacio++;
        }
    }
    cadena[contadorEspacio] = '\0';

    // Calcular cadenaInversa después de eliminar los espacios
    for (i = 0; i < strlen(cadena); i++)
    {
        cadenaInversa[i] = cadena[strlen(cadena)-i-1];
    }
     cadenaInversa[strlen(cadena)] = '\0';

    printf("Cadena sin espacios: %s + longitud : %d\n", cadena,strlen(cadena));
    printf("Cadena invertida: %s + longitud : %d\n", cadenaInversa,strlen(cadenaInversa));

     // Verificar si es un palíndromo
    if(strcmp(cadena,cadenaInversa)==0){
        printf("Es palindromo.\n");
    }else{
        printf("No es palindromo.\n");
    }
    return 0;
}