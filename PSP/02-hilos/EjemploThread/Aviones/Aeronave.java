package Aviones;

public class Aeronave extends Thread 
{
	private int id;   // Identificador de la aeronave
	private int tipo; // Tipo de aeronave 0=Avión, 1=Avioneta.
	private GestorDespegue gestorDespegue;
	private Temporizador temporizador;
	
	public Aeronave (int id,int tipo,GestorDespegue gestorDespegue,Temporizador temporizador)
	{
		this.id = id;
		this.tipo = tipo;
		this.gestorDespegue = gestorDespegue;
		this.temporizador = temporizador;
	}
	
	public void run()
	{
		try
		{
			switch (tipo)
			{
			case 0:
				gestorDespegue.despegarAvion(id);
				temporizador.iniciarTemporizador(3);
				break;
			case 1:
				gestorDespegue.despegarAvioneta(id);
				temporizador.iniciarTemporizador(2);
				break;
			default:
				System.out.println("ERROR: Tipo de aeronave desconocido");
			}
		}
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
