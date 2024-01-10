#include<stdio.h>
#include<stdbool.h>
int main(void)
{   
    
    bool primo = true;
    int num;
    printf("ingresar un número entero positivo y determine si es un número primo o no.\n");
    scanf("%d",&num);
    for (size_t i = 2; i < num; i++)
    {
        if(num % i == 0){
            printf("Numero introducido es no primo.\n");
            primo = false;
            break;
        }
    }

    if (primo == true)
    {
        printf("Numero introducido es primo.\n");
    }
    
    return 0;
    /*
    int number;
 
    printf("请输入一个整数: ");
    scanf("%d", &number);
 
    // 判断这个数最后一位是1这为奇数
    if(number&1)
        printf("%d 是奇数。", number);
    else
        printf("%d 是偶数。", number);
 
    return 0;*/
}
