#include<stdio.h>
#include<stdbool.h>
<<<<<<< HEAD
int main(void)
{   
    
    bool primo = true;
    int num;
    printf("ingresar un número entero positivo y determine si es un número primo o no.\n");
    scanf("%d",&num);
=======
bool isPrimo(int num){
    if(num <= 1){
        return false;
    }
>>>>>>> refs/remotes/origin/main
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
