package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import scanner.Scanner;
import token.Token;
import token.TokenType;

public class ScannerTest {

	@Test 
	public void emptyFileTest() throws IOException {

			Scanner s = new Scanner("./src/files/is_empty.txt");
			assertEquals(TokenType.EOF, s.nextToken().getTktp());
	}

	@Test 
	public void shiftFileTest() throws IOException {
		
		Scanner s = new Scanner("./src/files/is_not_empty.txt");
		Token tkn;
		
		do {
			tkn = s.nextToken();
		} while(tkn.getTktp() != TokenType.EOF);
			
		assertEquals(TokenType.EOF, tkn.getTktp());
			
	}

	@Test
	public void plusTest () throws IOException{
		Scanner s = new Scanner("./src/files/is_sum.txt");
		Token tkn;
		
		tkn = s.nextToken();
		assertEquals (TokenType.FLOATDCL, tkn.getTktp());
		tkn=s.nextToken();
		assertEquals (TokenType.ID, tkn.getTktp());
		assertEquals ("a", tkn.getValue());
		tkn=s.nextToken();
		assertEquals (TokenType.INTDCL, tkn.getTktp());
		tkn=s.nextToken();
		assertEquals (TokenType.ID, tkn.getTktp());
		assertEquals ("b", tkn.getValue());
		tkn=s.nextToken();
		assertEquals (TokenType.ID, tkn.getTktp());
		assertEquals ("a", tkn.getValue());
		tkn=s.nextToken();
		assertEquals (TokenType.ASSIGN, tkn.getTktp());
		tkn=s.nextToken();
		assertEquals (TokenType.FNUM, tkn.getTktp());
		assertEquals ("5.1", tkn.getValue());
		tkn = s.nextToken();
		tkn = s.nextToken();
		tkn = s.nextToken();
		tkn = s.nextToken();
		assertEquals (TokenType.PLUS, tkn.getTktp());
		tkn=s.nextToken();
		assertEquals (TokenType.INUM, tkn.getTktp());
		assertEquals ("4", tkn.getValue());
		tkn=s.nextToken();
		assertEquals (TokenType.PRINT, tkn.getTktp());
		tkn=s.nextToken();
		tkn=s.nextToken();
		assertEquals (TokenType.EOF, tkn.getTktp());
	}
	
	
	@Test()
	public void assignTest() throws IOException{
		Scanner s = new Scanner("./src/files/is_assign.txt");
		Token tkn;
		
		tkn = s.nextToken();
		assertEquals (TokenType.FLOATDCL, tkn.getTktp());
		tkn=s.nextToken();
		assertEquals (TokenType.ID, tkn.getTktp());
		assertEquals ("a", tkn.getValue());
		tkn=s.nextToken();
		tkn=s.nextToken();
		assertEquals (TokenType.ASSIGN, tkn.getTktp());
		tkn=s.nextToken();
		assertEquals (TokenType.FNUM, tkn.getTktp());
		assertEquals ("2.3", tkn.getValue());
		tkn=s.nextToken();
		assertEquals (TokenType.EOF, tkn.getTktp());
	}

	@Test 
	public void makeItFail() throws IOException {
		//TODO test che potrebbe far fallire
	}
}