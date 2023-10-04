#include <stdio.h>
#include <string.h>
#define NUMERO_0_ASCII 48;
/*
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
*/

int main(int argc, char const *argv[])
{
    char *operador;
    double numero1;
    double numero2;
    double resultado;
    if (argc < 4)
    {
        printf("Error: Se necesitan al menos 3 parámetros.\n");
        return 1; // Código de error
    }
    operador = argv[1];
    numero1 = (double)(*argv[2]) - NUMERO_0_ASCII;
    numero2 = (double)(*argv[3]) - NUMERO_0_ASCII;

    if (strcmp(operador, "suma") == 0)
    {
        resultado = numero1 + numero2;
        operador = "+";
    }
    else if (strcmp(operador, "resta") == 0)
    {
        resultado = numero1 - numero2;
        operador = "-";
    }
    else if (strcmp(operador, "multiplicar") == 0)
    {
        resultado = numero1 * numero2;
        operador = "*";
    }
    else if (strcmp(operador, "division") == 0)
    {
        // Verificar si el segundo operando es 0
        if (numero2 == 0)
        {
            printf("Error: División por cero no permitida.\n");
            return 1; // Código de error
        }
        resultado = numero1 / numero2;
        operador = "/";
    }
    else
    {
        printf("Error: Operación inválida.\n");
        return 1; // Código de error
    }

    // Imprimir el resultado
    printf("%.2lf %s %.2lf = %.2lf\n",numero1,operador,numero2, resultado);

    return 0;
}
