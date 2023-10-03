# POSIX

Generales

## POSIX (Portable Operating System Interface)

fork(): Crea un nuevo proceso hijo duplicando el proceso padre, lo que permite la ejecución de múltiples tareas de manera concurrente.

exec(): Reemplaza la imagen del proceso actual con una nueva imagen ejecutable, generalmente utilizada después de una llamada a fork() para iniciar un programa en el proceso hijo.

wait() y waitpid(): Esperan a que un proceso hijo termine su ejecución y recuperan su estado de salida. waitpid() permite un mayor control y la espera de un proceso específico.

exit(): Termina un proceso y devuelve un código de salida al sistema operativo que puede ser recogido por el proceso padre a través de wait() o waitpid().

pipe(): Crea una tubería (pipe) que permite la comunicación unidireccional entre procesos. Se utiliza para la transferencia de datos entre procesos relacionados.

dup() y dup2(): Duplican un descriptor de archivo existente, lo que es útil para redirigir la entrada/salida estándar o establecer la entrada/salida de un proceso a través de un descriptor de archivo personalizado.

kill(): Envía una señal a un proceso o grupo de procesos, lo que permite la comunicación y el control entre procesos.

shmget(), shmat(), shmdt(): Estas llamadas al sistema se utilizan para la creación, conexión y desconexión de segmentos de memoria compartida, lo que permite a los procesos compartir datos en la memoria.

msgget(), msgsnd(), msgrcv(): Permiten la creación, envío y recepción de mensajes en colas de mensajes, lo que facilita la comunicación entre procesos a través de mensajes.

semget(), semop(), semctl(): Estas llamadas al sistema se utilizan para la creación, operación y control de conjuntos de semáforos, que son mecanismos de sincronización utilizados para la exclusión mutua entre procesos.

## threads

pthread_create(): Crea un nuevo hilo y comienza su ejecución. Puedes especificar la función que el hilo ejecutará como su punto de entrada.

pthread_join(): Espera a que un hilo termine su ejecución y recupera su estado de salida. Es útil para sincronizar la ejecución de hilos.

pthread_detach(): Marca un hilo como "desvinculado", lo que significa que el sistema liberará automáticamente los recursos del hilo cuando termine su ejecución.

pthread_mutex_init(), pthread_mutex_lock(), pthread_mutex_unlock(): Estas funciones permiten la creación, bloqueo y desbloqueo de mutex (semáforos binarios) para lograr la exclusión mutua entre hilos.

pthread_cond_init(), pthread_cond_wait(), pthread_cond_signal(): Estas funciones se utilizan para crear y gestionar variables de condición, que son útiles para la sincronización de hilos.

## Señales

signal(): Establece un manejador de señal personalizado para una señal específica. Esto permite que un proceso o hilo maneje señales de manera personalizada cuando se producen.

sigaction(): Proporciona un control más avanzado sobre el manejo de señales en comparación con signal(). Permite especificar opciones detalladas para el manejo de señales, como el uso de funciones de manipulación específicas.

kill(): Envía una señal a un proceso o grupo de procesos desde otro proceso. Esto se utiliza para la comunicación y el control entre procesos o hilos.

sigwait(): Bloquea un hilo hasta que se reciba una señal específica, lo que puede ser útil para sincronizar la ejecución de hilos.

pthread_kill(): Envía una señal a un hilo específico en lugar de a un proceso completo. Esto es útil cuando se trabaja con múltiples hilos en un proceso.