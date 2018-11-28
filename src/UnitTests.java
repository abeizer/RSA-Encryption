import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
		RSA rsa = new RSA();
		rsa.show(new long[] {101, 102, 103, -5, -632145, 183085, 0});
		
		// Put things back
		System.out.flush();
		System.setOut(old);
		
		// See what happened
		System.out.print(stdout.toString());
		
		assertEquals("101 102 103 -5 -632145 183085 0\r\n", stdout.toString());
	}
	
	@Test
	void isPrimeTest()
	{
		RSA rsa = new RSA();
		
		// Primes
		assertEquals(true, rsa.isPrime(2));
		assertEquals(true, rsa.isPrime(3));
		assertEquals(true, rsa.isPrime(5));
		assertEquals(true, rsa.isPrime(7));
		assertEquals(true, rsa.isPrime(11));
		assertEquals(true, rsa.isPrime(13));
		assertEquals(true, rsa.isPrime(17));
		assertEquals(true, rsa.isPrime(19));
		assertEquals(true, rsa.isPrime(23));
		assertEquals(true, rsa.isPrime(29));
		assertEquals(true, rsa.isPrime(31));
		//etc...
		assertEquals(true, rsa.isPrime(173));
		assertEquals(true, rsa.isPrime(199));
		assertEquals(true, rsa.isPrime(12241));
		assertEquals(true, rsa.isPrime(49003));
		assertEquals(true, rsa.isPrime(2147483647));
		
		// Non-primes
		assertEquals(false, rsa.isPrime(4));
		assertEquals(false, rsa.isPrime(6));
		assertEquals(false, rsa.isPrime(8));
		assertEquals(false, rsa.isPrime(9));
		assertEquals(false, rsa.isPrime(10));
		assertEquals(false, rsa.isPrime(12));
		assertEquals(false, rsa.isPrime(14));
		assertEquals(false, rsa.isPrime(15));
		assertEquals(false, rsa.isPrime(16));
		assertEquals(false, rsa.isPrime(18));
		assertEquals(false, rsa.isPrime(20));
		// etc...
		assertEquals(false, rsa.isPrime(81));
		assertEquals(false, rsa.isPrime(2000000002));
		assertEquals(false, rsa.isPrime(1555555));
		assertEquals(false, rsa.isPrime(49010));
		assertEquals(false, rsa.isPrime(49007));
		assertEquals(false, rsa.isPrime(2147483646));
	}

}
