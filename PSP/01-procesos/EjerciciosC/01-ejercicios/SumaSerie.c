#include<stdio.h>

int main(void)
{
    int numero;
    double resultado = 0;

    printf("La suma de los primeros N términos de la serie armónica inversa.\n");
    scanf("%d",&numero);
    for (size_t i = 1; i <= numero; i++)
    {
        resultado += (1.0/i);
    }
    printf("Resultado : %lf\n",resultado);

    return 0;
}
