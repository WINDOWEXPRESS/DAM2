#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main()
{
    // Abrir un archivo para escritura
    int file = open("output.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);

    if (file < 0)
    {
        perror("Open");
        return 1;
    }

    // El nombre del programa a ejecutar
    char *program = "ipconfig";

    // Argumentos para el programa: el nombre del programa y NULL al final
    char *arguments[] = {"ipconfig", NULL};
    
    // Llamar a execvp para ejecutar el comando ls con argumentos y lo redirige a el
    dup2(file, STDOUT_FILENO);

    execvp(program, arguments);

    // Si execvp falla, imprimirá un error
    perror("execvp");
    return 1;
    // Cerrar el archivo
    close(file);

    return 0;
}

