#include<stdio.h>
#include<stdbool.h>
bool isPrimo(int num){
    if(num <= 1){
        return false;
    }
    for (size_t i = 2; i < num; i++)
    {
        if(num % i == 0){
            return false;
        }
    }
    return true;
}
int main(void)
{   
    int num;
    printf("ingresar un número entero positivo y determine si es un número primo o no.\n");
    scanf("%d",&num);

    if (isPrimo(num))
    {
        printf("Numero introducido es primo.\n");
    }else{
        printf("Numero introducido no es primo.\n");
    }
    
    return 0;
}
