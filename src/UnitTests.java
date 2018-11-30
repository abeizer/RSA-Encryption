import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

class UnitTests {

	@SuppressWarnings("static-access")
	@Test
	void showTest() 
	{
		// Create dummy output stream
		final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		// Save old stream
		PrintStream old = System.out;
		// Use new stream
		System.setOut(new PrintStream(stdout));
		RSA.show(new long[] {101, 102, 103, -5, -632145, 183085, 0});
		
		// Put things back
		System.out.flush();
		System.setOut(old);
		
		assertEquals("101 102 103 -5 -632145 183085 0\r\n", stdout.toString());
	}
	
	@Test
	void isPrimeTest()
	{
		// Primes
		assertEquals(true, RSA.isPrime(2));
		assertEquals(true, RSA.isPrime(3));
		assertEquals(true, RSA.isPrime(5));
		assertEquals(true, RSA.isPrime(7));
		assertEquals(true, RSA.isPrime(11));
		assertEquals(true, RSA.isPrime(13));
		assertEquals(true, RSA.isPrime(17));
		assertEquals(true, RSA.isPrime(19));
		assertEquals(true, RSA.isPrime(23));
		assertEquals(true, RSA.isPrime(29));
		assertEquals(true, RSA.isPrime(31));
		//etc...
		assertEquals(true, RSA.isPrime(173));
		assertEquals(true, RSA.isPrime(199));
		assertEquals(true, RSA.isPrime(12241));
		assertEquals(true, RSA.isPrime(49003));
		assertEquals(true, RSA.isPrime(2147483647));
		
		// Non-primes
		assertEquals(false, RSA.isPrime(4));
		assertEquals(false, RSA.isPrime(6));
		assertEquals(false, RSA.isPrime(8));
		assertEquals(false, RSA.isPrime(9));
		assertEquals(false, RSA.isPrime(10));
		assertEquals(false, RSA.isPrime(12));
		assertEquals(false, RSA.isPrime(14));
		assertEquals(false, RSA.isPrime(15));
		assertEquals(false, RSA.isPrime(16));
		assertEquals(false, RSA.isPrime(18));
		assertEquals(false, RSA.isPrime(20));
		// etc...
		assertEquals(false, RSA.isPrime(81));
		assertEquals(false, RSA.isPrime(2000000002));
		assertEquals(false, RSA.isPrime(1555555));
		assertEquals(false, RSA.isPrime(49010));
		assertEquals(false, RSA.isPrime(49007));
		assertEquals(false, RSA.isPrime(2147483646));
	}
	
	@Test
	void randPrimeTest()
	{
		for(int i = 0; i < 20; i++)
		{
			long num = RSA.randPrime(50, 8000, new Random());
			assertEquals(true, RSA.isPrime(num));
		}
		
	}

	@Test
	void isRelativelyPrimeTest()
	{
		// Numbers that should be relatively prime
		assertEquals(true, RSA.isRelativelyPrime(5, 7));
		assertEquals(true, RSA.isRelativelyPrime(2, 3));
		assertEquals(true, RSA.isRelativelyPrime(9, 4));
		assertEquals(true, RSA.isRelativelyPrime(3, 11));
		
		// Numbers that should not be relatively prime
		assertEquals(false, RSA.isRelativelyPrime(5, 10));
		assertEquals(false, RSA.isRelativelyPrime(14, 7));
		assertEquals(false, RSA.isRelativelyPrime(2, 4));
		assertEquals(false, RSA.isRelativelyPrime(555, 10));
		
		// Order of arguments doesn't matter
		assertEquals(false, RSA.isRelativelyPrime(100, 25));
		assertEquals(false, RSA.isRelativelyPrime(25, 100));
		
		assertEquals(true, RSA.isRelativelyPrime(10, 11));
		assertEquals(true, RSA.isRelativelyPrime(11, 10));
	}
	
	@Test
	void relPrimeTest()
	{
		Random rand = new Random();
		for(int i = 0; i < 20; i++)
		{
			long x = (long)(rand.nextDouble()*(2000 - 0));
			long y = RSA.relPrime(x, rand);
			assertEquals(true, RSA.isRelativelyPrime(x, y));
		}
	}
	
	@Test
	void toLongTest()
	{
		// Index out of bounds
		assertThrows(IndexOutOfBoundsException.class, () -> {
					RSA.toLong("12345", 10);	
					});
		
		
		// Correct indeces
		assertEquals(12, RSA.toLong("12345678", 0));
		assertEquals(34, RSA.toLong("12345678", 2));
		assertEquals(56, RSA.toLong("12345678", 4));
		assertEquals(78, RSA.toLong("12345678", 6));		
	}
	
}
