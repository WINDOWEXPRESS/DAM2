#include<stdio.h>

int main(void){
    float celsius = 0.0f;
    float fahrenheit = 0.0f;

    printf("Ingresar una temperatura en grados Celsius y lo convierta a grados Fahrenheit. \nCelsius: ");

    scanf("%f",&celsius);

    fahrenheit = (celsius * 9/5) + 32;

    printf("Celsius : %.2f  --> Fahrenheit : %.2f",celsius ,fahrenheit);
    return 0;
}
