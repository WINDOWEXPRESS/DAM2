#include<stdio.h>
#include<math.h>
int main(void)
{
    int base;
    int exponente;
    printf("ingresar una base y un exponente (ambos enteros) para la potencia.\n");
    scanf("%d",&base);
    scanf("%d",&exponente);

    printf("Resultado: %lf\n",pow(base,exponente));
    return 0;
}
