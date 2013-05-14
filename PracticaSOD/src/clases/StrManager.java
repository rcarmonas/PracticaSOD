package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase que contiene varias funciones de manejo de cadenas. En concreto para
 * aplicar los algoritmos MD5 y SHA1
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
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
	
	/**
	 * Imprime la hora actual del sistema
	 */
	public static void imprimirHora()
	{
		Calendar calendario = new GregorianCalendar();
		int hora, minutos, segundos;
		calendario = new GregorianCalendar();
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		System.out.println(hora + ":" + minutos + ":" + segundos);
	}
	
}