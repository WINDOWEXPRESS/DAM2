#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define NUM_HIJOS 3  // Puedes ajustar este número según tus necesidades

int main() {
    pid_t hijos[NUM_HIJOS];

    // Crear múltiples hijos
    for (int i = 0; i < NUM_HIJOS; ++i) {
        hijos[i] = fork();
        if (hijos[i] == -1) {
            perror("Error en el fork");
            exit(EXIT_FAILURE);
        }

        if (hijos[i] == 0) {
            // Código del hijo
            printf("Soy el hijo %d, PID: %d\n", i + 1, getpid());
            // ... (acciones del hijo)
            exit(EXIT_SUCCESS);
        }
    }

    // Código del padre después de las bifurcaciones
    // ... (acciones del padre)
    
    // Esperar a que todos los hijos terminen
    for (int i = 0; i < NUM_HIJOS; ++i) {
        wait(NULL);
    }

    printf("Proceso padre terminó\n");

    return 0;
}
