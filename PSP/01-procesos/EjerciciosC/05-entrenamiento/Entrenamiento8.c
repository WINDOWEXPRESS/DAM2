#include <stdio.h>

int main(int argc, char const *argv[])
{
    char *programa = "ifconfig";
    char *parametros[] = {"ifconfig", NULL};
    execvp(programa, parametros);

    // Si execvp falla, imprimirá un error
    // Si execvp tiene éxito, este código no se ejecutará
    perror("execvp");
    return -1;
}
