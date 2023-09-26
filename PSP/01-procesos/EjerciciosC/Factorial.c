#include<stdio.h>
#include<math.h>
int calculo(int numeroFactorial){
    if (numeroFactorial == 1)
    {
        return 1;
    }else{
        return numeroFactorial * calculo(numeroFactorial-1);
    }
    
}
int main(void)
{
    int numeroFactorial ;
    int resultado;

    printf("ingresar un número entero no negativo y calcule su factorial.\n");
    scanf("%d",&numeroFactorial);
    numeroFactorial = fabs(numeroFactorial);

    resultado = calculo(numeroFactorial);
    
    printf("Factorial de %d es: %d\n",numeroFactorial,resultado);

    return 0;
}

