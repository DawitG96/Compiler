package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import token.Token;
import token.TokenType;

public class TokenTest {
	private Token token;
	
	@Test
	public void testTokenInum() {
		Token suf = new Token(TokenType.INUM, "2");
		assertEquals(suf.toString(),"<INUM, 2>");
	}
	
	@Test
	public void testTokenAssign() {
		token = new Token(TokenType.ASSIGN, null);
		assertEquals("<ASSIGN>", token.toString());
	}
	
	@Test
	public void testTokenEOF() {
		token = new Token(TokenType.EOF, null);
		assertEquals("<EOF>", token.toString());
	}
}
