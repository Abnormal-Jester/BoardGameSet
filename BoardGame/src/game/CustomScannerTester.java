package game;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class CustomScannerTester {

	Scanner scanner;
	ByteArrayInputStream testInputStream;
	
	
	@Before
	public void setUp() throws Exception {
		scanner = new Scanner(System.in);
	}

	@Test
	public void testTestInputStream() {
		setTestInputString("My string", "Test");
		
		assertEquals("My string", scanner.nextLine());
		assertEquals("Test", scanner.nextLine());
	}

	
	private void setTestInputString(String... input) {
		String oneLine = singleString(input);
		testInputStream = new ByteArrayInputStream(oneLine.getBytes());
		System.setIn(testInputStream);
	}

	private String singleString(String[] input) {
		String out = "";
		for(String str : input)
			out += str + "\n";
		return out;
	}
}
