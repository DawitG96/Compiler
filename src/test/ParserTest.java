package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import ast.NodeProgram;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;

public class ParserTest {
	@Test (expected = SyntacticException.class)
	public void failingTest() throws IOException, LexicalException, SyntacticException {
		Scanner scanner = new Scanner ("./src/files/parse_fail.txt");
		Parser parser = new Parser(scanner);
		parser.parse();
	}
	
	@Test 
	public void workingTest() throws IOException, LexicalException, SyntacticException {
		Scanner scanner = new Scanner ("./src/files/is_assign.txt");
		Parser parser = new Parser(scanner);
		NodeProgram node = parser.parse();
		
		assertEquals(node.getDecs().get(0).toString(), "a-FLOAT");
		assertEquals(node.getStms().get(0).toString(), "a = 2.3");
	}
}