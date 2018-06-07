package parser;

import java.io.IOException;
import java.util.ArrayList;

import ast.LangOper;
import ast.LangType;
import ast.NodeAssign;
import ast.NodeCost;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeExpr;
import ast.NodeId;
import ast.NodeOpBin;
import ast.NodePrint;
import ast.NodeProgram;
import ast.NodeStm;
import scanner.LexicalException;
import scanner.Scanner;
import token.Token;
import token.TokenType;
/**
 * Il Parser controlla che lo scanner segua le regole di produzione della notra grammatica
 * io sono dawit
 * dawit io sono
 * per lo scanner le due frasi sono corrette perchè i Token vengono generate, ma il parser per 
 * la seconda lancia una Eccezione poichè l'ordine è errato.
 *  
 *  Verrà generato un albero
 * @author Dawit
 *
 */
public class Parser {
	
	private Scanner scanner;
	
	private Token peekedToken;
	
	public Parser(Scanner scanner) {
		this.scanner=scanner;
	}
	
	/**
	 * Crea l'albero.
	 * @return radice dell'albero
	 * @throws LexicalException
	 * @throws IOException
	 * @throws SyntacticException
	 */
	public NodeProgram parse() throws LexicalException, IOException, SyntacticException {
		return parsePrg();
	}
	
	
	private NodeProgram parsePrg() throws LexicalException, IOException, SyntacticException {
		 Token tkn = scanner.peekToken();
		 
		
		switch(tkn.getTktp()) {
		case FLOATDCL: case INTDCL: case ID: case PRINT: case EOF:
			ArrayList<NodeDecl> dec = parseDcls();
			ArrayList<NodeStm> stat= parseStms();
			match(TokenType.EOF);
			return new NodeProgram(dec,stat);
		default:
			throw new SyntacticException("Errore sintattico parsePrg");
		}
	}

	private ArrayList<NodeDecl> parseDcls() throws LexicalException, IOException, SyntacticException {
		 Token tkn = scanner.peekToken();
		 ArrayList<NodeDecl> dec = new ArrayList<NodeDecl>();
		
		switch(tkn.getTktp()) {
		case FLOATDCL: case INTDCL:
			dec.add(parseDcl());
			dec.addAll(parseDcls());
			return dec;
		case ID: case PRINT: case EOF:
			return new ArrayList<NodeDecl>();
			
		default: 
			throw new SyntacticException("Errore sintattico parseDcls");
		}
	}
	
	private NodeDecl parseDcl() throws LexicalException, IOException, SyntacticException{
		 Token tkn = scanner.peekToken();
		 
		switch(tkn.getTktp()) {
		
		case FLOATDCL:
			match(TokenType.FLOATDCL);
			tkn= scanner.peekToken();
			match(TokenType.ID);
			return new NodeDecl(new NodeId(tkn.getValue()), LangType.FLOAT);
			
		case INTDCL:
			match(TokenType.INTDCL);
			tkn = scanner.peekToken();
			match(TokenType.ID);
			return new NodeDecl(new NodeId(tkn.getValue()), LangType.INT);
			
		default:
			throw new SyntacticException("Errore sintattico parseDcl");
		
		}
	}
	
	private ArrayList<NodeStm> parseStms() throws LexicalException, IOException, SyntacticException {
		Token tkn = scanner.peekToken();
		ArrayList<NodeStm> stm = new ArrayList<NodeStm>();
		
		switch(tkn.getTktp()) {
		case ID: case PRINT:
			stm.add(parseStm());
			stm.addAll(parseStms());
			return stm;
			
		case EOF:
			return new ArrayList<NodeStm>();
			
		default:
			throw new SyntacticException("Errore sintattico parseStms");
		}
	}
	
	private NodeStm parseStm() throws LexicalException, IOException, SyntacticException {
		Token tkn = scanner.peekToken();
		
		switch(tkn.getTktp()) {
		case ID:
			match(TokenType.ID);
			NodeId id = new NodeId(peekedToken.getValue());
			match(TokenType.ASSIGN);
			NodeExpr val = parseVal();
		
			NodeExpr expr = parseExpr(val);
			return new NodeAssign(id, expr);
			
		case PRINT:
			match(TokenType.PRINT);
			match(TokenType.ID);
			return new NodePrint(new NodeId(peekedToken.getValue()));
			
		default:
			throw new SyntacticException("Errore sintattico parseStm");
		}
	}
	
	private NodeExpr parseExpr(NodeExpr val) throws LexicalException, IOException, SyntacticException {
		Token tkn = scanner.peekToken();
		switch(tkn.getTktp()) {
		case PLUS:
			match(TokenType.PLUS);
			NodeExpr val1 =parseVal();
			NodeExpr expr1 =parseExpr(val1);
			return new NodeOpBin(val,LangOper.PLUS,expr1);
			
		case MINUS:
			match(TokenType.MINUS);
			NodeExpr val2 =parseVal();
			NodeExpr expr2 =parseExpr(val2);
			return new NodeOpBin(val,LangOper.MINUS,expr2);
			
		case ID: case PRINT: case EOF:
			return val;
		default:
			throw new SyntacticException("Errore sintattico parseExpr");
			
		}
		
	}
	
	private NodeExpr parseVal() throws LexicalException, IOException, SyntacticException {
		Token tkn = scanner.peekToken();
		
		switch(tkn.getTktp()) {
		case ID:
			match(TokenType.ID);
			return new NodeDeref(new NodeId(peekedToken.getValue()));
			
		case INUM:
			match(TokenType.INUM);
			return new NodeCost(peekedToken.getValue(),LangType.INT);
			
		case FNUM:
			match(TokenType.FNUM);
			return new NodeCost(peekedToken.getValue(),LangType.FLOAT);
			
		default:
			throw new SyntacticException("Errore sintattico parseVal");
			
		}
	}
	
	private void match(TokenType type) throws LexicalException, IOException, SyntacticException {
		
		Token temp = scanner.peekToken();
		if(type==temp.getTktp()) {
			peekedToken=temp;
			scanner.nextToken();
		}
		else
			throw new SyntacticException("Errore sintattico match");
			
	}
}
