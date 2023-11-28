#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Función para generar combinaciones de caracteres
void generarCombinaciones(int longitud, char *cadena, int indice, FILE *archivo) {
    if (indice == longitud) {
        fprintf(archivo, "%s\n", cadena);
        return;
    }

    for (char letra = 'a'; letra <= 'z'; letra++) {
        cadena[indice] = letra;
        generarCombinaciones(longitud, cadena, indice + 1, archivo);
    }
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Falta parametro por comando : %s <numero_hijos>\n", argv[0]);
        return 1;
    }
    //atoi metodo para pasar un string a int
    int n = atoi(argv[1]);

    if (n <= 0) {
        fprintf(stderr, "El número de hijos debe ser un entero positivo.\n");
        return 1;
    }

    for (int i = 1; i <= n; i++) {
        char nombreArchivo[20]; // Suficientemente grande para "datos" + número + ".txt"

        //sprintf Parámetros: La cadena de destino, la cadena de formato y los datos a guardar
        sprintf(nombreArchivo, "datos%d.txt", i);

        FILE *archivo = fopen(nombreArchivo, "w");

        if (archivo == NULL) {
            perror("Error al abrir el archivo");
            return 1;
        }

        char cadena[i]; // La cadena tendrá longitud i
        //memset(void *str, int c, size_t n).
        //copia el caracter c (un char sin signo) a los primeros n caracteres de str.
        memset(cadena, 0, sizeof(cadena)); // Inicializar la cadena con caracteres nulos

        generarCombinaciones(i, cadena, 0, archivo);

        fclose(archivo);
    }

    printf("Archivos generados exitosamente.\n");

    return 0;
}
