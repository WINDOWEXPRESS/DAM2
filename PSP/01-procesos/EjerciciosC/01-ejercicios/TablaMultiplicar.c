#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#define N 10
void imprimirTabla(int num)
{
    for (int i = 1; i < num; i++)
    {
        printf("%d *s %d = %d\t", num, i, num * i);
    }
    printf("\n");
}

int main(int argc, char const *argv[])
{
    pid_t idHijo;
    for (int i = 1; i < N; i++)
    {
        idHijo = fork();
        if (idHijo == 0)
        {
            imprimirTabla(i);
            exit(0);
        }
    }

    for (int j = 0; j < N; j++)
    {
       printf("wait - ");
        wait(NULL);
        printf("wait - ");
    }

    return 0;
}
