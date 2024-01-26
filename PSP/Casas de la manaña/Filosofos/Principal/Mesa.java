package Principal;

public class Mesa 
{
	private boolean tenedores[];
	
	public Mesa ()
	{
		tenedores = new boolean[5];
		for (int i=0;i<5;i++)
			tenedores[i] = true;
	}
	
	public synchronized void dameTenedores(int numero) throws InterruptedException
	{
		
		while(tenedores[numero]==false || tenedores[(numero+1)%5]==false)
			wait();
		tenedores[numero]=tenedores[(numero+1)%5]=false;
		synchronized (System.out) 
		{
			System.out.println("Filosofo "+numero+" comiendo");
		}
	}

	public synchronized void devolverTenedores(int numero)
	{
		tenedores[numero] = true; 	// Devuelve izquierdo
		tenedores[(numero+1)%5] = true; // Devuelve derecho
		synchronized (System.out) 
		{
		  System.out.println("Filosofo "+numero+" termina de comer");
		}
		notifyAll();
	}
}