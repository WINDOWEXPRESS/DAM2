package Principal;

//Para gestionar este intervalo de tiempo, se dispone de una clase Temporizador
//El método iniciarTemporizador arranca un temporizador que deja pasar un cierto tiempo. 
//Cuando el tiempo expira, se invoca el método autorizarDespegue 
//del objeto de la clase GestorDespegue que se pasa en el constructor. 
public class Temporizador 
{
	private GestorDespegue gestorDespegue;
	public Temporizador(GestorDespegue gestor) 
	{
		this.gestorDespegue = gestor;
	}
	public void iniciarTemporizador(int minutos) 
	{
		try 
		{
			Thread.sleep((long)minutos*1000);
		} catch (InterruptedException e) {e.printStackTrace();} 
		gestorDespegue.autorizarDespegue();
	}
}


