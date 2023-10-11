#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int fork1(){
    int childip;
    int i;

    if(fork() ==0){
        for(i = 1; i<=8;i++){
            printf("Este es proceso hijo\n");
        }
    }else{
        for(i = 1; i<=8;i++){
            printf("Este es proceso padre\n");
        }
    }
}
int fork2(){
    int childip;
    int i;

    if(fork() ==0){
        for(i = 1; i<=8;i++){
            printf("Este es proceso hijo\n");
        }
        exit(0);
    }else{
        for(i = 1; i<=8;i++){
            printf("Este es proceso padre\n");
        }
    }
    printf("Paso2 despues de fork() !! \n");
}

int fork3(){
    int childip;
    int i;

    if(fork() ==0){
        for(i = 1; i<=8;i++){
            printf("Este es proceso hijo\n");
        }
        exit(0);
    }else{
        wait();
        for(i = 1; i<=8;i++){
            printf("Este es proceso padre\n");
        }
    }
    printf("Paso2 despues de fork() !! \n");
}


int main(void)
{
    fork1();
    fork2();
    fork3();



    return 0;
}

