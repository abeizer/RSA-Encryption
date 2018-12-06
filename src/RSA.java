
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
		Person Alice = new Person();
		Person Bob = new Person();
		String msg = new String ("Bob, let's have lunch.");
		Alice.decrypt(Bob.encryptTo(msg, Alice));
		show(new long[]{101, 504, 66, -826});
		
		/*
		for(int i = 0; i < 10; i++)
		{
			System.out.println(randPrime(100, 205, new Random()));
		}
		*/
		
		Random rand = new Random();
		for(int i = 0; i < 10; i++)
		{
			long x = (long)(rand.nextDouble()*(2000 - 0));
			long y = relPrime(x, rand);
			System.out.println(x + " " + y + " " + isRelativelyPrime(x, y));
		}
		
		String chars = "84671850";
		System.out.println(chars.substring(0, 2));
		System.out.println(Long.parseLong(chars.substring(0, 2)));
		
		
	}// end main
	
	/**
	 * @author Zachary Reynolds
	 * Find the multiplicative inverse of a long int 
	 * (e^(-1) (mod m))
	 * @param e The long to find an inverse for, mod m
	 * @param m The modulus
	 * @return The inverse of e, mod m. Uses the Extended Euclidean Algorithm
	 */
	public static long inverse(long e, long m)
	{
		long ans = 0;
		long u1 = 0;
		long u2 = 1;
		long u3;
		

		long q = m/e;
		long r = m - (q * e);
		long r2 = e;
		while ((r > 1))
		{
			u3 = u1 - (q * u2);
			u1 = u2;
			u2 = u3;
			q = r2/r;
			long r3 = r;
			r = r2 - (r * q);
			r2 = r3;
			
			
		}
		
		if(r == 1)
		{
			u3 = u1 - (q * u2);
			
			if(u3 < 0)
			{
				ans = u3 + m;
			}
			else
				ans = u3 % m;
		}
		return ans;
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
	 * @author Zachary Reynolds
	 * Raise a number, b, to a power, p, modulo m
	 * @param b Base number
	 * @param p Power (raise b to the p)
	 * @param m Modulus (mod b to the p)
	 * @return b^p (mod m)
	 */
	public static long modPower(long b, long p, long m)
	{
		int e = 1;
		for(int x = 0; x < (p); x++)
		{
			e = e * 2;
			if(e > p)
			{
				e = e/2;
				break;
			}		
		} 
		int num = e;
		long ans = (long) Math.pow(b,e) % m;
		while(e > 1)
		{
			e = e/2;
			if(!(num + e > p))
			{
				long a = (long) Math.pow(b,e);
				ans = (a * ans) % m;
				num = num + e;
			}
			
		}
		

		return ans;
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
	 * @author Abby Beizer
	 * Find a random number relatively prime to a given long int
	 * @param n
	 * @param rand
	 * @return a random number relatively prime to n
	 */
	public static long relPrime(long n, Random rand)
	{
		// Get a random number until it is relatively prime n
		// Then return that number
		long p = 0;
		do 
		{
			p = (long)(rand.nextDouble()*(2000 - 0));
			
		}while(!isRelativelyPrime(n, p));
		
		return p;
	}
	
	/**
	 * @author Abby Beizer
	 * Return whether the two numbers passed are relatively prime.
	 * @param a 
	 * @param b
	 * @return true if a and b are relatively prime
	 */
	public static boolean isRelativelyPrime(long a, long b)
	{
		// This finds the gcd (greatest common divisor), a
		long temp;
		while(b != 0)
		{
			temp = b;
			b = a % b;
			a = temp;
		}
		
		// a is the gcd
		// if a == 1, then the two numbers are relatively prime 
		return (a == 1);
	}
	
	
	/**
	 * @author Abby Beizer
	 * Convert two numeric chars to long int
	 * @param msg Contains at least two numeric chars
	 * @param p Position in msg to start conversion from
	 * @return the two digit number beginning at position p of msg as a long int
	 * @throws IndexOutOfBoundsException
	 */
	public static long toLong(String msg, int p)
	{
		if(p >= msg.length() - 1)
		{
			throw new IndexOutOfBoundsException("index p or p+1 is larger than length of input String.");
		}
		
		String nums = msg.substring(p, p+2);	// end index is exclusive
		
		return Long.parseLong(nums);
	}
	
	
	/**
	 * @author Abby Beizer
	 * Convert a long to 2 chars
	 * @param x
	 * @return The string made up of two numeric digits representing x
	 * @throws IllegalArgumentException
	 */
	public static String longTo2Chars(long x)
	{
		if(x > 99)
		{
			throw new IllegalArgumentException("input cannot be more than 2 digits long.");
		}
		if(x < 0)
		{
			throw new IllegalArgumentException("input cannot be negative.");
		}
		
		String chars = Long.toString(x);
		
		// If long only has one digit, then precede it with a 0
		if(x < 10)
		{
			chars = "0".concat(chars);
		}
		
		return chars;
	}
}
