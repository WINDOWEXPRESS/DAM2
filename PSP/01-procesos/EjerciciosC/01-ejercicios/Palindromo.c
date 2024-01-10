#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#define TAMANIO 1024
#define TAMAÑO_SALTO_LINEA 1
#define TAM 1024
int main(void)
{
    
    // declarar array de char de tamanio fijo
    char cadena[TAMANIO];
    char cadenaInversa[TAMANIO];

    // obetener cadena desde entrada
    printf("Ingresar una cadena de caracteres: ");
    // Utilizar fgets para leer la entrada de manera segura
    fgets(cadena, sizeof(cadena), stdin); //guarda hasta salto de linea \n

    //Aqui cambia el salto de linea por \0
    cadena[strlen(cadena)-TAMAÑO_SALTO_LINEA] = '\0';

    // Eliminar espacios de la cadena
    int i, contadorSinEspacio = 0;
    for (i = 0; i < strlen(cadena); i++)
    {
        if (cadena[i] != ' ')
        {
            cadena[contadorSinEspacio] = tolower(cadena[i]);
            contadorSinEspacio++;
        }
    }
    cadena[contadorSinEspacio] = '\0';

    // Calcular cadenaInversa después de eliminar los espacios
    for (i = 0; i < strlen(cadena); i++)
    {
        cadenaInversa[i] = cadena[strlen(cadena) - i - 1];
    }

    printf("Cadena sin espacios: %s + longitud : %d\n", cadena, strlen(cadena));
    printf("Cadena invertida: %s + longitud : %d\n", cadenaInversa, strlen(cadenaInversa));

    // Verificar si es un palíndromo
    if (strcmp(cadena, cadenaInversa) == 0)
    {
        printf("Es palindromo.\n");
    }
    else
    {
        printf("No es palindromo.\n");
    }

    return 0;
}