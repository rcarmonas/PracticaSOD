package clases;

public class StrManager {
	
	static int INICIO = 32;
	static int FIN = 126;
	/**
	 * Obtiene la cadena siguiente a la actual para su procesado
	 * @param actual Cadena actual
	 * @return Cadena siguiente
	 */
	public static String getNext(String actual)
	{
		StringBuffer aux = new StringBuffer();
		boolean continuar=true;
		for(int i=actual.length()-1; i>=0; i--)
		{
			char cAux = actual.charAt(i);
			if(cAux<FIN && continuar)//En caso de que no sea el último caracter
			{
				aux.append(cAux+1);
				continuar = false;
			}
			else if(continuar)//En caso de que sea el último
				aux.append(INICIO);
			else//Se añaden los carácteres restantes
				aux.append(cAux);
		}
		String result = aux.reverse().toString();
		
		if(result.charAt(0)!=actual.charAt(0))
			return null;
		else
			return result;
	}
	
}
