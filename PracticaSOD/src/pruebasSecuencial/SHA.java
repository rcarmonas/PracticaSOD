package pruebasSecuencial;

import java.io.IOException;

import clases.StrManager;

public class SHA {
	static final int MAX=3;
	boolean encontrado;
	String hash;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StrManager.imprimirHora();
		
		SHA prueba=new SHA(args[0]);
		StringBuffer cad = new StringBuffer();
		//Añade la primera letra:
		try {
			prueba.probarCadenas(cad);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	SHA(String h){
		hash=h;
	}
	private void probarCadenas(StringBuffer str) throws IOException
	{
		probarCombinacion(str.toString());
		
		if(str.length()<MAX)
			for(char j=(char)StrManager.INICIO;j<=StrManager.FIN && !this.encontrado; j++)
			{
				str.append(j);
				probarCadenas(str);
				str.deleteCharAt(str.length()-1);
			}
	}
	private void probarCombinacion(String actual)
	{
		String aux;
		//En caso de tratarse de un trabajo MD5:
		aux = StrManager.SHA(actual);
		if(aux!=null)
		if(aux.equals(this.hash))
		{
			StrManager.imprimirHora();
			this.encontrado = true;
			System.out.println("Resultado: "+actual);
			
		}
	}
}