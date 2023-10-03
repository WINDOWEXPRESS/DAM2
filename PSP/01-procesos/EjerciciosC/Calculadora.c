#include <stdio.h>
#include <string.h>
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
        numero1 = atof(argv[2]);
        numero1 = atof(argv[3]);
        
        printf("argv[1]:%s.\n",argv[1]);
        printf("argv[2]:%s.\n",argv[2]);
                printf("argv[3]:%s.\n",argv[3]);


         if (strcmp(operador,"suma")) {
        resultado = numero1 + numero2;
    } else if (operador == "resta" ) {
         resultado = numero1 - numero2;
    } /*else if (operacion[0] == 'm' && operacion[1] == 'u' && operacion[2] == 'l' && operacion[3] == 't' && operacion[4] == 'i' && operacion[5] == 'p' && operacion[6] == 'l' && operacion[7] == 'i' && operacion[8] == 'c' && operacion[9] == 'a' && operacion[10] == 'c' && operacion[11] == 'i' && operacion[12] == 'o' && operacion[13] == 'n') {
        resultado = operando1 * operando2;
    } else if (operacion[0] == 'd' && operacion[1] == 'i' && operacion[2] == 'v' && operacion[3] == 'i' && operacion[4] == 's' && operacion[5] == 'i' && operacion[6] == 'o' && operacion[7] == 'n') {
        // Verificar si el segundo operando es 0
        if (operando2 == 0) {
            printf("Error: División por cero no permitida.\n");
            return 1; // Código de error
        }
        resultado = operando1 / operando2;
    } */else {
        printf("Error: Operación no válida.\n");
        return 1; // Código de error
    }

    // Imprimir el resultado
    printf("Resultado: %lf\n", resultado);
    


    return 0;
}
