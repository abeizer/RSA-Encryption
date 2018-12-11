import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Used to test public-key crypto-system, RSA
 * @author Matthew Mallon
 */

public class Person 
{
	
	private long publicModulus;
	private long encryptionExponent;
	private long privateKey;
	private long decryptionExponent;
	private long d;

	/**
	 * @author Matthew Mallon
	 * Generate a public key for this person, consisting of exponent, e, and modulus, m.
	 * Generate a private key, consisting of an exponent, d.
	 * Provide access to the public key only.
	 */
	public Person()
	{
		long p;
		long q;
		long n;
		Random rand = new Random();
		while (true) {
            p = (long) (Math.random() * (20000-16384) + 128);
            if(RSA.isPrime(p)) {
            	break;
            }
        }
		while (true) {
            q = (long) (Math.random() * (200-128) + 128);
            if(RSA.isPrime(q)) {
            	break;
            }
        }
		publicModulus = p*q;
		n = (p-1) * (q-1);
		encryptionExponent = RSA.relPrime(n, rand);
		d = RSA.inverse(encryptionExponent, n);
	}
	
	/**
	 * @author Matthew Mallon
	 * Access the public modulus
	 * @return The public modulus for this Person
	 */
	public long getM()
	{
		return this.publicModulus;
	}
	
	
	/**
	 * @author Matthew Mallon
	 * Access the public encryption exponent
	 * @return The public encryption exponent for this Person
	 */
	public long getE()
	{
		return this.encryptionExponent;
	}
	
	
	/**
	 * @author Matthew Mallon
	 * Encrypt a plain text message to she.
	 * @param msg A plain-text message to encrypt for she
	 * @param she The Person receiving the encrypted message.
	 * @return An array of longs, which is the cipher text
	 */
	public long[] encryptTo(String msg, Person she)
	{
		List<String> stringArray = new ArrayList<String>();
        int len = msg.length();
        for (int i = 0; i < len; i += 2)
        {
        	stringArray.add(msg.substring(i, Math.min(len, i + 2)));
        }
        int arrayLength = stringArray.size();
        long[] longArray = new long[arrayLength];
        for(int i = 0; i < arrayLength; i++) {
        	String character = stringArray.get(i);
        	long value = ((long) character.charAt(0) * 128) + character.charAt(1);
        	longArray[i] = value;
        }
        for(int i = 0; i < longArray.length; i++) {
        	long encryptedValue = RSA.modPower(longArray[i], she.getE(), she.getM());
        	longArray[i] = encryptedValue;
        }
		return longArray;
	}
	
	
	/**
	 * @author Matthew Mallon
	 * Decrypt the cipher text
	 * @param cipher The cipher text this Person will decrypt
	 * @return A string of the plain text
	 */
	public String decrypt(long[] cipher)
	{
		long[] decryptedArray = new long[cipher.length];
		ArrayList<String> stringArray = new ArrayList<String>();
		for(int i = 0; i < cipher.length; i++) {
			long decryptedValue = RSA.modPower(cipher[i], d, publicModulus);
			decryptedArray[i] = decryptedValue;
		}
		for(int i = 0; i <decryptedArray.length; i++) {
			long number = decryptedArray[i];
			String str = Character.toString((char) (number/128));
			str = str + (char) (number%128);
			stringArray.add(str);
		}
		return String.join("", stringArray);
	}
	
}
