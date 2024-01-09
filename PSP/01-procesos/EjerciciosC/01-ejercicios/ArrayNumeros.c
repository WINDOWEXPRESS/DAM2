#include<stdio.h>
#include<string.h>

int main(void)
{   //declara arrays de notas con 5 de tamanio
    double notas[5]; 
    double notaMedia = 0;
    double notaMasAlta = -999;// Inicializa con un valor bajo para asegurar que cualquier nota sea alto
    double notaMasBaja = 999;// Inicializa con un valor alto para asegurar que cualquier nota sea menor
    
    printf("Introducir las notas que ha sacado en 5 exámenes:");
     // Leer notas y encontrar la nota más alta y mas baja
    for (int i = 0; i < 5; i++)
    {   
        scanf("%lf",&notas[i]);
        // Encontrar la nota mas alta
        if(notas[i]>notaMasAlta){
            notaMasAlta = notas[i];
        }
        // Encontrar la nota mas baja
        if(notas[i]<notaMasBaja){
            notaMasBaja = notas[i];
        }
        //La nota media
        notaMedia +=  notas[i];
    }
    int meid = strlen(notas);
    notaMedia = (notaMedia/(sizeof(notas)/8));
    printf("La nota mas alta es : %lf\t",notaMasAlta);
    printf("La nota mas baja es : %lf\t",notaMasBaja);
    printf("La media es : %lf\t",notaMedia);
    printf("La sizeof es : %d\t",meid);
    
    return 0;

}
/* Los arrays pueden ser de cualquier tipo de dato.
 Crea un programa que pida al usuario las notas que ha sacado en 5 exámenes. 
 Almacena la información en un array. Después mostrá una lista con la nota de los exámenes y si ha aprobado o no. 
 La nota media, la nota más alta y la nota más baja. */