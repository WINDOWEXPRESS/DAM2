public class _5_Principal {
    public static void main(String[] args) {
        _5_Bancos banco = new _5_Bancos("Chen bank", 1000000);
        _5_Usuario alice = new _5_Usuario("alice", 40000);
        banco.aniadirUsuario(alice);

        _5_Usuario bob = new _5_Usuario("bob", 70000);
        banco.aniadirUsuario(bob);

        _5_Usuario chen = new _5_Usuario("chen", 0);
        banco.aniadirUsuario(chen);

        Thread hiloAlice = new Thread(alice);
        Thread hiloBob = new Thread(bob);

        hiloAlice.start();

        hiloBob.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("alice.getDinero() : " + alice.getDinero());
        System.out.println("bob.getDinero() : " + bob.getDinero());
        System.out.println("chen.getDinero() : " + chen.getDinero());
    }
}
