package pruebasSecuencial;

import java.io.IOException;

import clases.StrManager;
/**
 * Clase encargada de realizar una prueba secuencial de SHA1
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class SHA {
	static final int MAX=4;
	/**
	 * @uml.property  name="encontrado"
	 */
	boolean encontrado;
	/**
	 * @uml.property  name="hash"
	 */
	String hash;
	/**
	 * Encuentra la cadena de origen de un HASH SHA1 de forma secuencial mediante fuerza bruta.
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
	/**
	 * Genera todas las cadenas hasta una longitud máxima
	 * @param str Cadena inicial
	 * @throws IOException
	 */
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
	/**
	 * Prueba si la cadena pasada como parámetros es el origen del HASH a resolver
	 * @param actual cadena a comprobar
	 */
	private void probarCombinacion(String actual)
	{
		String aux;
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