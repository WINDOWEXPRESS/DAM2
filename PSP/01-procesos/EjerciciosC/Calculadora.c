#include<stdio.h>

int main(void){
    float num1 = 0L;
    float num2 = 0L;

    printf("Calculadora de Suma Simple.\n");

    printf("Introducir primero numero :");
    scanf("%f\n",num1);
    printf("Introducir segundo numero :");
    scanf("%f\n"+num2);

    printf("La suma es : %f"+(num1+num2));

    return 0;
}
