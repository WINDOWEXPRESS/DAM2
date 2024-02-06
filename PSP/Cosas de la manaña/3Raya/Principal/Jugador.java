package Principal;

public class Jugador extends Thread 
{
	private String nombre;
	private char simbolo;
	private boolean fin;
	private Tablero tablero;
	public Jugador(String nombre,char simbolo,Tablero tablero)
	{
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.fin = false;
		this.tablero = tablero;
	}
	
	public void run()
	{
		int hueco[];
		
		while (!fin)
		{
			hueco = tablero.getHueco();
			if (hueco[0]==1)
			{
				tablero.ponerFicha(hueco[1], hueco[2], simbolo);
				if (tablero.comprobar(simbolo))
				{
					System.out.println("Ha ganado "+nombre);
					fin=true;
					tablero.imprimir();
				}
			}
			else
			{
				System.out.println("Parece que hemos empatado");
				fin=true;
			}
		}
	}
}
