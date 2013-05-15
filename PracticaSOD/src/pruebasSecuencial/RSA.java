package pruebasSecuencial;
import java.math.BigInteger;

import clases.BigMath;
import clases.StrManager;

/**
 * Clase encargada de realizar una prueba secuencial de RSA
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class RSA {

	/**
	 * Realiza una prueba de factorización de RSA de forma secuencial
	 * @param args No recibe argumentos
	 */
	public static void main(String[] args) {
				 StrManager.imprimirHora();
				 BigInteger n = new BigInteger("42757403753663670371");
		         BigInteger j =  BigMath.bigIntSqRootCeil(n);
		         BigInteger i = new BigInteger("3");
		         BigInteger p = new BigInteger("0");
				 BigInteger dos = new BigInteger("2");
		         boolean continuar = true;
		         for(; i.compareTo(j)<=0 && continuar; i=i.add(dos))
		         {
		        	 if(n.mod(i).equals(BigInteger.ZERO))
		        	 {
		        		 continuar=false;
		        		 p = i.add(BigInteger.ZERO);
		        	 }
		         }
		         System.out.println(""+p);
				 StrManager.imprimirHora();	
	}

	

}
