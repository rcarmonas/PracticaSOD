package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StrManager {
	
	static int INICIO = 32;
	static int FIN = 126;
	private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
	
	
	
	/**
	 * 
	 * @param stringAEncriptar Cadena que será encriptada en MD5
	 * @return Cadena MD5
	 */
	public static String MD5(String stringAEncriptar)
    {
        try
        {
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++)
           {
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
	
	
	/**
	 * 
	 * @param stringAEncriptar Cadena que será encriptada en SHA
	 * @return Cadena SHA
	 */
	public static String SHA(String stringAEncriptar)
    {
        try
        {
           MessageDigest msgd = MessageDigest.getInstance("SHA");
           byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++)
           {
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
	
}


















/**
 * AL FINAL NO SE USA, ME GUSTA MÁS OTRO MÉTODO
 */
/**
 * Obtiene la cadena siguiente a la actual para su procesado
 * @param actual Cadena actual
 * @return Cadena siguiente
 */
/*
public static String getNext(String actual, int tam_max)
{
	StringBuffer aux = new StringBuffer();
	boolean continuar=true; //Continua procesando caracteres o no
	for(int i=actual.length()-1; i>0; i--)
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
	aux.append(actual.charAt(0));
	String result = aux.reverse().toString();
	
	if(result.charAt(0)!=actual.charAt(0))
		return null;
	else
		return result;
}
*/
