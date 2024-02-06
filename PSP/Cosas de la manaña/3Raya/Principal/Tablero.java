package Principal;

public class Tablero 
{
	char t[][];
	private boolean final_partida;

	public Tablero()
	{
		final_partida = false;
		t = new char[3][3];
		for (int f=0;f<3;f++)
			for (int c=0;c<3;c++)
				t[f][c]=' ';
	}
	
	// Devuelve las coordenadas del hueco si lo hay [1 fila columna]
	// Si no hay hueco devuelve 0 en la posición inicial [0 x x]
	public synchronized int[] getHueco()
	{
		int f,c;
		int hueco[] = new int[3];
		boolean encontrado=false;
		
		hueco[0]=0;
		for (f=0;f<3 && encontrado==false;f++)
			for (c=0;c<3 && encontrado==false;c++)
				if (t[f][c]==' ')
				{
					hueco[0]=1;
					hueco[1]=f;
					hueco[2]=c;
					encontrado = true;
				}
		return hueco;
	}
	
	public synchronized boolean ponerFicha(int f,int c ,char simbolo)
	{
		boolean exito=false;
		if (t[f][c]==' ')
		{
			t[f][c]=simbolo;
			exito=true;
		}
		return exito;
	}
		
	public void imprimir()
	{
		int f,c;
		
		for (f=0;f<3;f++)
		{
			for (c=0;c<3;c++)
				System.out.print(" "+t[f][c]);
			System.out.println("");
		}
	}
		
	
	public synchronized boolean comprobar(char simbolo)
	{
		int f,c,contador;
		boolean ganar=false;
		
		// Verifica por filas
		for (f=0;f<3 && ganar==false;f++)
		{
			contador = 0;
			for (c=0;c<3;c++)
				if (t[f][c]==simbolo)
					contador++;
			if (contador == 3)
				ganar = true;
		}
		
		// Verifica por columnas
		for (c=0;c<3 && ganar==false;c++)
		{
			contador=0;
			for (f=0;f<3;f++)
				if (t[f][c]==simbolo)
					contador++;
			if (contador==3)
				ganar = true;
		}
		// Verifica diagonal principal
		contador=0;
		for (f=0;f<3 && ganar==false;f++)
			if (t[f][f]==simbolo)
				contador++;
		if (contador==3)
			ganar = true;
			
		// Verifica diagonal secundaria
		contador=0;
		for (c=2;c>0 && ganar==false;c--)
			if (t[2-c][c]==simbolo)
				contador++;
		if (contador==3)
			ganar=true;

	notifyAll();
	if (ganar==true)
		final_partida=true;
	else
		try {
			wait();
			} catch (InterruptedException e) {e.printStackTrace();}

	return ganar;
	}
}