package clases;

import java.math.BigInteger;

public class BigMath {

	/**
	 * Realiza la raíz cuadrada de un dato de tipo BigInteger
	 * @param x Número del cual se obtendrá la raíz cuadrada
	 * @return Raíz cuadrada del número
	 * @throws IllegalArgumentException
	 */
	public static BigInteger bigIntSqRootCeil(BigInteger x)
	        throws IllegalArgumentException {
	    if (x.compareTo(BigInteger.ZERO) < 0) {
	        throw new IllegalArgumentException("Negative argument.");
	    }
	    if (x == BigInteger.ZERO || x == BigInteger.ONE) {
	        return x;
	    }
	    BigInteger two = BigInteger.valueOf(2L);
	    BigInteger y;
	    for (y = x.divide(two);
	            y.compareTo(x.divide(y)) > 0;
	            y = ((x.divide(y)).add(y)).divide(two));
	    if (x.compareTo(y.multiply(y)) == 0) {
	        return y;
	    } else {
	        return y.add(BigInteger.ONE);
	    }
	}

}
