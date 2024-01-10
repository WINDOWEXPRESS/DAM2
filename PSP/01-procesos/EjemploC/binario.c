#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NOMBRE_LENGTH 50

struct Registro
{
    int numero;
    char nombre[NOMBRE_LENGTH];
};

int main()
{
    // Declarar una estructura para almacenar datos
    struct Registro datos;

    // Abrir el archivo en modo escritura binaria
    FILE *archivo = fopen("datos.txt", "wb");

    // Comprobar si se pudo abrir el archivo
    if (archivo == NULL)
    {
        perror("No se pudo abrir el archivo");
        return 1;
    }

    // Escribir datos en el archivo
    datos.numero = 65;
    /*char *strcpy(char *dest, const char *src);
    Descripción: Copia la cadena de origen (src) en la cadena de destino (dest).
    Comportamiento: Copia caracteres desde src hasta encontrar el carácter nulo ('\0'), 
        que indica el final de la cadena.
    Precaución: No verifica si hay suficiente espacio en la cadena de destino. 
        Puede causar desbordamiento de búfer si no hay suficiente espacio en dest.
    */
    strcpy(datos.nombre, "Ejemplo 1");
    //size_t fwrite(const void *ptr, size_t size, size_t nmemb, FILE *stream);
    fwrite(&datos, sizeof(struct Registro), 1, archivo);

    datos.numero = 169;
    strcpy(datos.nombre, "Ejemplo 2");
    fwrite(&datos, sizeof(struct Registro), 1, archivo);

    // Cerrar el archivo
    fclose(archivo);

    // Abrir el archivo en modo lectura binaria
    archivo = fopen("datos.txt", "rb");

    // Comprobar si se pudo abrir el archivo
    if (archivo == NULL)
    {
        perror("No se pudo abrir el archivo");
        return 1;
    }

    // Leer datos desde el archivo
    printf("Leyendo datos del archivo:\n");
    while (fread(&datos, sizeof(struct Registro), 1, archivo) == 1)
    {
        printf("Número: %d, Nombre: %s\n", datos.numero, datos.nombre);
    }

    // Cerrar el archivo
    fclose(archivo);

    return 0;
}
/*
size_t fread(void *ptr, size_t size, size_t nmemb, FILE *stream);
    ptr: Puntero al bloque de memoria donde se almacenarán los datos leídos.
    size: Tamaño en bytes de cada elemento a leer.
    nmemb: Número de elementos, cada uno del tamaño de size.
    stream: Puntero al objeto de tipo FILE que especifica el flujo de archivo del cual se leerán los datos.
La función devuelve el número total de elementos leídos con éxito, que puede ser menos que nmemb si se alcanza el final del archivo u ocurre un error.
*/
/*
"r"	    Abre para lectura. Si el archivo no existe o no se encuentra, la llamada fopen genera un error.
"w"	    Abre un archivo vacío para escritura. Si el archivo especificado existe, se destruye su contenido.
"a" 	Abre para escritura al final del archivo (anexo) sin eliminar el marcador de fin de archivo (EOF) 
        antes de que se escriban nuevos datos en el archivo. Crea el archivo si no existe.
"r+"	Abre para lectura y escritura. El archivo debe existir.
"w+"	Abre un archivo vacío para lectura y escritura. Si el archivo existe, se destruye su contenido.
"a+"	Se abre para lectura y anexado. La operación de anexado incluye la eliminación del marcador EOF 
        antes de que los nuevos datos se escriban en el archivo. El marcador EOF no se restablece una vez 
        completada la escritura. Crea el archivo si no existe.
"t"	    Abra en modo de texto (traducido). Las combinaciones de retorno de carro (CR-LF) 
        se traducen en fuentes de línea única (LF) en caracteres de entrada y LF se traducen 
        a combinaciones CR-LF en la salida. Además, CTRL+Z se interpreta como carácter de final de archivo en la entrada.
"b"	    Abra en modo binario (sin traducir); las traducciones que implican los caracteres de retorno de carro y avance de línea se suprimen.
*/