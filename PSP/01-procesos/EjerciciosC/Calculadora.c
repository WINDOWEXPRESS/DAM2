#include<stdio.h>

int main(void){
    float num1 = 0.0f;
    float num2 = 0.0f;

    printf("=======Calculadora de Suma Simple.=======\n");

    printf("Introducir primero numero :");
    scanf("%f",&num1);
    printf("Introducir segundo numero :");
    scanf("%f",&num2);

    printf("%.2f + %.2f = %.2f",num1,num2,(num1+num2));

    return 0;
}
