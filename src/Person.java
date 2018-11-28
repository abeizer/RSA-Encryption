/**
 * Used to test public-key crypto-system, RSA
 */

public class Person 
{
	
	private long publicModulus;
	private long encryptionExponent;

	/**
	 * Generate a public key for this person, consisting of exponent, e, and modulus, m.
	 * Generate a private key, consisting of an exponent, d.
	 * Provide access to the public key only.
	 */
	public Person()
	{
		
	}
	
	
	/**
	 * Access the public modulus
	 * @return The public modulus for this Person
	 */
	public long getM()
	{
		return this.publicModulus;
	}
	
	
	/**
	 * Access the public encryption exponent
	 * @return The public encryption exponent for this Person
	 */
	public long getE()
	{
		return this.encryptionExponent;
	}
	
	
	/**
	 * Encrypt a plain text message to she.
	 * @param msg A plain-text message to encrypt for she
	 * @param she The Person receiving the encrypted message.
	 * @return An array of longs, which is the cipher text
	 */
	public long[] encryptTo(String msg, Person she)
	{
		return new long[0];
	}
	
	
	/**
	 * Decrypt the cipher text
	 * @param cipher The cipher text this Person will decrypt
	 * @return A string of the plain text
	 */
	public String decrypt(long[] cipher)
	{
		return "";
	}
	
}
