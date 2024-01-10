#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#define N 10
void imprimirTabla(int num)
{
    for (int i = 1; i <= num; i++)
    {
        printf("%d * %d = %d\t", i, num, num * i);
    }
    printf("\n");
}

int main(int argc, char const *argv[])
{
    pid_t child_pids[N];
    pid_t idHijo;
    for (int i = 1; i < N; i++)
    {           
        sleep(2);
        child_pids[i] = idHijo = fork();
        if (idHijo == 0)
        {
            imprimirTabla(i);
            exit(42+i);
        }
    }
    /*
    for (int j = 0; j < N; j++)
    {
        printf("%d - ",j);
        int status;
        wait(&status); 
        printf("%d ; ",WEXITSTATUS(status));
    }*/
    for (int j = 0; j < N; j++)
    {
        printf("%d - ",child_pids[j]);
        int status;
        waitpid(child_pids[j],&status,0); 
        if(WIFEXITED(status)){
            printf("%d ; ",WEXITSTATUS(status));
        }
    }
    return 0;
}