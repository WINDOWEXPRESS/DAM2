#include<stdio.h>
#include<stdbool.h>
int main(void)
{   
    bool primo = true;
    int num;
    printf("ingresar un número entero positivo y determine si es un número primo o no.\n");
    scanf("%d",&num);
    for (size_t i = 1; i < num; i++)
    {
        if((i!=1 )&& num % i == 0){
            printf("Numero introducido es no primo.");
            primo = false;
            break;
        }
    }

    if (primo == true)
    {
        printf("Numero introducido es primo.");
    }
    
    return 0;
}
