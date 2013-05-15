package pruebasSecuencial;

import java.io.IOException;

import clases.StrManager;
/**
 * Clase encargada de realizar una prueba secuencial de MD5
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class MD5 {
	static final int MAX=4;
	boolean encontrado;
	String hash;
	/**
	 * Realiza una prueba secuencial de descifrado de MD5 por fuerza bruta
	 * @param args Hash MD5 a descifrar
	 */
	public static void main(String[] args) {
		StrManager.imprimirHora();
		if(args.length==0)
			args = new String[]{"3af3e0dd85b60de5fbc81594329d2c22"};
		MD5 prueba=new MD5(args[0]);
		StringBuffer cad = new StringBuffer();
		//Añade la primera letra:
		try {
			prueba.probarCadenas(cad);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	MD5(String h){
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
		aux = StrManager.MD5(actual);
		if(aux!=null)
		if(aux.equals(this.hash))
		{
			StrManager.imprimirHora();
			this.encontrado = true;
			System.out.println("Resultado: "+actual);
			
		}
	}
}
