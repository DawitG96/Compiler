package scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

import token.Token;
import token.TokenType;

/**
 * Lo scanner tramite il reader legge un file e crea dei token dalle parole che riceve.
 * @author Dawit
 *
 */
public class Scanner {
	private PushbackReader buffer;
	private Token currentToken;
	
	/**
	 * Costruttore dello scanner
	 * @param fileInput
	 * @throws FileNotFoundException
	 */
	public Scanner(String fileInput) throws FileNotFoundException {
		File fp = new File(fileInput);
		FileReader fileR= new FileReader(fp);
		buffer= new PushbackReader(fileR);
		currentToken=null;
	}
	
	public Token peekToken() throws LexicalException, IOException {
		if(currentToken==null)
			currentToken=nextToken();
		return currentToken;
	}
	/**
	 * Metodo che restituisce un Token in base a ciò che lo scanner legge
	 * @return Token
	 * @throws IOException
	 * @throws LexicalException
	 */
	public Token nextToken() throws IOException, LexicalException {
		int car;
		
		if (currentToken != null){
			Token temp = currentToken;
			currentToken = null;
			return temp;
		}
		
		do {
			car=buffer.read();
		}while(car==' ' || car=='\t' || car=='\n' || car=='\r' || Character.toString((char)car)==System.lineSeparator());
		
		if(car==-1)
			return new Token(TokenType.EOF,null);
		
		if(Character.isDigit(car))
		{
			buffer.unread(car);
			return scanNumber();
		}
		
		switch((char)car) {
		case 'i' | 'I':
			return new Token(TokenType.INTDCL,null); 
		case 'f' | 'F':
			return new Token(TokenType.FLOATDCL,null);
		case 'p' | 'P':
			return new Token(TokenType.PRINT,null);
		case '-':
			return new Token(TokenType.MINUS,null);
		case '+':
			return new Token(TokenType.PLUS,null);
		case '/':
			return new Token(TokenType.DIV,null)
;		case '=':
			return new Token(TokenType.ASSIGN,null);
		default:
			return new Token(TokenType.ID,Character.toString((char)car));
		}
	}

	private Token scanNumber() throws IOException {
		String number = new String();
		char digit = 0;
		int dotting=0;
		
		digit = (char)buffer.read();
		
		while(Character.isDigit(digit) || digit=='.') {
			
			number+=digit;
			digit = (char)buffer.read();
			if(digit == '.')
				dotting++;
			if(dotting>1)
				break;
		} 
		
		
		if(dotting>0)
			return new Token(TokenType.FNUM,number);
		else
			return new Token(TokenType.INUM,number);
		
		/*if (digit == '.' ) {
			do {
				number+=digit;
				digit =  (char)buffer.read();
				System.out.println(digit+"-");
			} while(Character.isDigit(digit));
			
			if(digit!=-1) {
				buffer.unread(digit);
			}
			
			return new Token(TokenType.FNUM,number);
		}
		else {
			if(digit!=-1) {
				buffer.unread(digit);
			}
			return new Token(TokenType.INUM,number);
		}*/
	}
}
