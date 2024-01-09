#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#define TAMANIO 1024
#define LONGITUD_SALTO_LINEA 1
#define TAM 1024
int main(void)
{
    /*
    // declarar array de char de tamanio fijo
    char cadena[TAMANIO];
    char cadenaInversa[TAMANIO];

    // obetener cadena desde entrada
    printf("Ingresar una cadena de caracteres: ");
    fgets(cadena, sizeof(cadena), stdin); //guarda hasta salto de linea \n

    //Aqui cambia el salto de linea por \0
    cadena[strlen(cadena)-LONGITUD_SALTO_LINEA] = '\0';

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
    */
    int indexPrimerPosicion, indexUltimoPosicion;
    bool isPalindromo = true;
    char cadena[TAM];
    
    printf("Ingresa una plabra o frase\n");
    gets(cadena);

    // Eliminar espacios de la cadena
    int contadorSinEspacio = 0;
    for (int i = 0; i < strlen(cadena); i++)
    {
        if (cadena[i] != ' ')
        {
            cadena[contadorSinEspacio] = tolower(cadena[i]);
            contadorSinEspacio++;
        }
    }
    cadena[contadorSinEspacio] = '\0';
    indexUltimoPosicion = strlen(cadena) - 1;
    indexPrimerPosicion = 0;

    while (indexPrimerPosicion < indexUltimoPosicion){
        if(cadena[indexPrimerPosicion] != cadena[indexUltimoPosicion])
            isPalindromo = false;
        indexPrimerPosicion++;
        indexUltimoPosicion--;
    }
    
    if(isPalindromo)
        printf("La palabra es un palindromo\n");
    else
        printf("La palabra no es un palindromo\n");
    printf("La palabra %s\n",cadena);
    return 0;
}