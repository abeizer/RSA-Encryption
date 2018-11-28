
import java.util.Random;

/**
 * Public-key encryption implementation
 *
 */

public class RSA 
{

	public RSA(){}
	
	/**
	 * Driver to test RSA encryption
	 */
	public static void main(String[] args)
	{
		show(new long[]{101, 504, 66, -826});
		
		for(int i = 0; i < 10; i++)
		{
			System.out.println(randPrime(100, 205, new Random()));
		}
		
		
	}
	
	/**
	 * Find the multiplicative inverse of a long int 
	 * (e^(-1) (mod m))
	 * @param e The long to find an inverse for, mod m
	 * @param m The modulus
	 * @return The inverse of e, mod m. Uses the Extended Euclidean Algorithm
	 */
	public static long inverse(long e, long m)
	{
		return -1;
	}
	
	
	/**
	 * @author Abby Beizer
	 * Display an array of longs on stdout
	 * @param cipher The cipher text to display as an array of longs
	 */
	public static void show(long[] cipher)
	{
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < cipher.length; i++)
		{
			s.append(cipher[i] + " ");
		}
		
		// Remove the trailing space
		s.delete(s.length() - 1, s.length());
		
		System.out.println(s.toString());
	}
	
	
	/**
	 * Raise a number, b, to a power, p, modulo m
	 * @param b Base number
	 * @param p Power (raise b to the p)
	 * @param m Modulus (mod b to the p)
	 * @return b^p (mod m)
	 */
	public static long modPower(long b, long p, long m)
	{
		return -1;
	}
	
	
	/**
	 * @author Abby Beizer
	 * Find a random prime number
	 * @param m Lower bound of prime's value
	 * @param n Upper bound of prime's value
	 * @param rand
	 * @return A random prime in the range m..n, using rand to generate the number
	 */
	public static long randPrime(int m, int n, Random rand)
	{
		// Get a random number until it is prime
		// Then return that number
		long p = 0;
		do 
		{
			p = rand.nextInt(n) + m;
			
		}while(!isPrime(p));
		
		return p;
	}
	
	/**
	 * @author Abby Beizer
	 * Evaluates whether a given number is a prime
	 * @param p
	 * @return true if p is prime
	 */
	public static boolean isPrime(long p)
	{
		// Case: less than 2
		if(p < 2)
			return false;
		
		// Case: equals 2, 3, or 5
		if(p == 2 || p == 3 || p == 5)
			return true;
		
		// Case: Divisible by 2, by 3, or by 5
		if(p % 2 == 0 || p % 3 == 0 || p % 5 == 0)
			return false;
		
		// else
		
		int max = (int) Math.sqrt(p) + 1;
		for(int i = 7; i <= max; i += 2)
		{
			if(p % i == 0)
				return false;
		}
		
		return true;
	}
	
	
	/**
	 * Find a random number relatively prime to a given long int
	 * @param n
	 * @param rand
	 * @return a random number relatively prime to n
	 */
	public static long relPrime(long n, Random rand)
	{
		return -1;
	}
	
	
	/**
	 * Convert two numeric chars to long int
	 * @param msg Contains at least two numeric chars
	 * @param p Position in msg to start conversion from
	 * @return the two digit number beginning at position p of msg as a long int
	 */
	public static long toLong(String msg, int p)
	{
		return -1;
	}
	
	
	/**
	 * Convert a long to 2 chars
	 * @param x
	 * @return The string made up of two numeric digits representing x
	 */
	public static String longTo2Chars(long x)
	{
		return "";
	}
}
