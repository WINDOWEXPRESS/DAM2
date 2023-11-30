package ejer4;

public class Islantilla {
    private static int llegado = 0;
    private static int explotado = 0;
    public static int cohetesTotal = 0;
    public static int cohetesLanzado = 0;
    public static final Object CONTROL = new Object();

    public static void incrementarLlegado() {
        llegado += 1;
    }

    public static void incrementarExplotado() {
        explotado += 1;
    }

    public static int getLlegado() {
        return llegado;
    }

    public static int getExplotado() {
        return explotado;
    }

    public static boolean esExito() {
        return llegado >= 3;
    }

}
