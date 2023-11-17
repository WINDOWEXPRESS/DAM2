#include <stdio.h>

#define LONGITUD_MINIMO_ARGC  1
#define POSICION_NUMERO_HILO  1

int main(int argc, char const *argv[])
{
    int numerohilo ;
    if(argc>LONGITUD_MINIMO_ARGC){
        numerohilo = argv[POSICION_NUMERO_HILO];
    }
    printf("NUMero %d",numerohilo);
    printf("\nNombre %s",argv[POSICION_NUMERO_HILO]);
    return 0;
}
