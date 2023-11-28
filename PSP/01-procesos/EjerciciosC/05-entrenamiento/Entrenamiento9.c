#include <stdlib.h>

int main() {
     char *args[] = {"ls", "-l", NULL};
    //execvp("ls", args);//reemplazar el proceso actual con un nuevo programa. y no ejecutara el resto
    system("ipconfig");
    system("ifconfig");
    
    int retorno = system("ps u");

    // Verificar el estado de terminación del comando
    if (retorno == -1) {
        perror("Error al ejecutar system");
        return 1;
    }
    return 0;
}

/*
    system:
Ventajas:
    Más simple de usar para ejecutar comandos desde la línea de comandos.
    No requiere manejar detalles de la creación de procesos y la gestión de señales.
Desventajas:
    Menos control sobre la ejecución del programa.
    Puede introducir riesgos de seguridad si se utilizan datos no confiables para construir comandos.
*/
/*
    execvp:
Ventajas:
    Ofrece mayor control y flexibilidad al ejecutar programas.
    Permite reemplazar el espacio de direcciones del proceso actual con un nuevo programa.
Desventajas:
    Después de llamar a execvp, el código que sigue a esa llamada no se ejecuta, 
    ya que el proceso original se ha reemplazado por completo por el nuevo programa.
*/