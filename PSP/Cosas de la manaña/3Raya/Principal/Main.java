package Principal;

public class Main 
{
	public static void main(String[] args) 
	{
		Mesa mesa = new Mesa();
		Filosofo f[] = new Filosofo[5];
		
		for (int i=0;i<5;i++)
		{
			f[i] = new Filosofo(i,mesa);
			f[i].start();
		}
	}
}
