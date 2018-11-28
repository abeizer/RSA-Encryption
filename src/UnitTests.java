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

}
