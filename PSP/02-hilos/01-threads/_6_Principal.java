public class _6_Principal {
        private static final int TOTAL_CARRERA = 100;
        private static final int NUM_CORREDORES = 10;
        private static final int STAR_DORSAL = 1000;
    
        public static void main(String[] args) {
            Thread corredores[] = new Thread[NUM_CORREDORES];
    
            for (int i = 0; i < NUM_CORREDORES; i++) {
                _6_Corredor corredor = new _6_Corredor(TOTAL_CARRERA,STAR_DORSAL+i);
                if(i != NUM_CORREDORES-1){
                    corredor.esperar();
                }
                corredores[i] = new Thread(corredor);
            }
    
            System.out.println("La carrera va a comenzar!!!");
            for (int i = 0; i < NUM_CORREDORES; i++) {
                corredores[i].start();
            }

            
            for (int i = 0; i < NUM_CORREDORES; i++) {
                try {
                    corredores[i].join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            corredores[corredores.length-1].notifyAll();
            System.out.println("¡La carrera ha terminado!");
        }
}
