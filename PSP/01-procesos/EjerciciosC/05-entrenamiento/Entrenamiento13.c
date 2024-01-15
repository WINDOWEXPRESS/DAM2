#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// Función para verificar si un número es primo
int esPrimo(int num) {
    if (num < 2) {
        return 0;  // Los números menores a 2 no son primos
    }

    for (int i = 2; i * i <= num; i++) {
        if (num % i == 0) {
            return 0;  // No es primo si se encuentra un divisor
        }
    }

    return 1;  // Es primo
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Uso: %s <numero1> <numero2>\n", argv[0]);
        return 1;
    }

    int num1 = atoi(argv[1]);
    int num2 = atoi(argv[2]);

    pid_t hijo1, hijo2;
    int status1, status2;
    int primosEncontrados = 0;

    hijo1 = fork();

    if (hijo1 == 0) {
        // Código del primer hijo
        if (esPrimo(num1)) {
            printf("Hijo 1: %d es primo\n", num1);
            exit(1);  // Indica que el número es primo
        } else {
            printf("Hijo 1: %d no es primo\n", num1);
            exit(0);  // Indica que el número no es primo
        }
    } else {
        // Código del padre
        hijo2 = fork();

        if (hijo2 == 0) {
            // Código del segundo hijo
            if (esPrimo(num2)) {
                printf("Hijo 2: %d es primo\n", num2);
                exit(1);  // Indica que el número es primo
            } else {
                printf("Hijo 2: %d no es primo\n", num2);
                exit(0);  // Indica que el número no es primo
            }
        } else {
            // Código del padre
            waitpid(hijo1, &status1, 0);
            waitpid(hijo2, &status2, 0);

            if (WEXITSTATUS(status1) == 1) {
                primosEncontrados++;
            }

            if (WEXITSTATUS(status2) == 1) {
                primosEncontrados++;
            }

            printf("Total de números primos encontrados: %d\n", primosEncontrados);
        }
    }

    return 0;
}
