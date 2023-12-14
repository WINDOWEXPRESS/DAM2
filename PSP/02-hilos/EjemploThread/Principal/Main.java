package Principal;

public class Main {

	public static void main(String[] args) 
	{
		GestorDespegue gestorDespegue = new GestorDespegue();
		Temporizador temporizador = new Temporizador(gestorDespegue);
		
		//Aeronave aeronave = new Aeronave(100,1,gestorDespegue,temporizador);
		
		Aeronave aeronaves[] = new Aeronave[10];
		
		for (int i=0;i<10;i++)
		{
			aeronaves[i] = new Aeronave (i,i%2,gestorDespegue,temporizador);
			aeronaves[i].start();
		}
		
	

	}

}
