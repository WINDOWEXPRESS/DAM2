#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(void)
{
    pid_t id_hijo;

    int n = 42;
    double pi = 3.14;

    //¡¡¡Clonacion!!!
    id_hijo = fork();

    //Hay dos procesos
    if(id_hijo != 0){
        //¿Padre?
        printf("Soy el padre %d\n",id_hijo);
    }else{
        //¿?
        printf("Soy el hijo %d\n",id_hijo);
    }
    



    return 0;
}
