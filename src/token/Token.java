package token;

public class Token {
	
	private TokenType tktp;
	private String tkname;
	
	public Token(TokenType tktp, String tkname) {
		this.tktp=tktp;
		this.tkname=tkname;
	}

	public TokenType getTktp() {
		return tktp;
	}

	public String getValue() {
		return tkname;
	}
	public String toString() {
		return "<" +tktp + (tkname!=null?", "+tkname:"") + ">";
	}
}
