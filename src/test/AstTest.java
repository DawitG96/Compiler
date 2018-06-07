package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import ast.NodeProgram;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;

public class AstTest {
	
	@Test
	public void assignSumTest() throws LexicalException, SyntacticException, IOException {
		Scanner scanner = new Scanner("./src/files/ast.txt");
		Parser parser = new Parser(scanner);
		NodeProgram node = parser.parse();
		
		assertEquals(node.getDecs().size(), 2);
		assertEquals(node.getDecs().get(0).toString(), "a-INT");
		assertEquals(node.getDecs().get(1).toString(), "b-FLOAT");
		
		assertEquals(node.getStms().get(0).toString(), "a = 2");
		assertEquals(node.getStms().get(1).toString(), "b = 2.4 PLUS a");
		assertEquals(node.getStms().get(2).toString(), "PRINT: b");	
	}

}
