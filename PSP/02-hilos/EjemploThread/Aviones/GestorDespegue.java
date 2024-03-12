package Aviones;
// Los aviones al despegar generan turbulencias, por lo que entre dos despegues
// consecutivos tiene que transcurrir un intervalo de tiempo mínimo:
// • 3 minutos después del despegue de un avión.
// • 2 minutos después del despegue de una avioneta.
// Se debe impedir que despeguen consecutivamente dos avionetas si hay aviones esperando
// No hay restricciones de este tipo respecto a los aviones.

public class GestorDespegue 
{
	private boolean aeronaveDespegando;
	private boolean ultimoEsAvioneta;
	private int avionesEsperando;
	
	public GestorDespegue ()
	{
		this.aeronaveDespegando = false;
		this.ultimoEsAvioneta = false;
		this.avionesEsperando = 0;
	}
	// lo invoca un avión cuando quiere despegar
    public synchronized void despegarAvion(int id) throws InterruptedException 
    {
    	avionesEsperando++;
    	System.out.println("El Avión "+id+" solicita pista. Hay "+avionesEsperando+" esperando");
    	while (aeronaveDespegando==true)
    		wait();
    	System.out.println("El avión "+id+" acaba de despegar");
    	aeronaveDespegando = true;
    	avionesEsperando--;
    	ultimoEsAvioneta=false;
    } 
    // Lo invoca una avioneta cuando quiere despegar
	public synchronized void despegarAvioneta(int id) throws InterruptedException
	{
		while (aeronaveDespegando==true || (avionesEsperando>0 && ultimoEsAvioneta==true))
		{
			if (aeronaveDespegando==false)
				System.out.println("La avioneta "+id+" tendrá que esperar");
		
			wait();
		}
		System.out.println("La avioneta "+id+" acaba de despegar");
		aeronaveDespegando = true;
		ultimoEsAvioneta=true;
	}  

	// lo invoca el temporizador 
	// para indicar que ha transcurrido el intervalo
	// mínimo desde el despegue anterior
	public synchronized void autorizarDespegue() 
	{
		aeronaveDespegando=false;
		notifyAll();
	} 
	
}
