package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ast.NodeProgram;
import ast.NodeStm;
import ast.ResultType;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;
import semanticVisitor.TypeCheckingVisitor;
import semanticVisitor.DeclarationVisitor;
import symTable.SymTable;

public class VisitorTest {
	
	DeclarationVisitor declVisitor = new DeclarationVisitor();
	TypeCheckingVisitor typevisitor =  new TypeCheckingVisitor();
	
	@Before 
	public void before(){
		SymTable.init();
	}
	
	@Test
	public void test00() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner scanner = new Scanner("./src/files/visit1.txt");
		Parser parser = new Parser(scanner);
		NodeProgram node = parser.parse();
		node.accept(declVisitor);
		node.accept(typevisitor);
		ArrayList<NodeStm> array = node.getStms();
		assertEquals(array.get(0).getResType(), ResultType.TYPE_ERROR);
	}
	
	@Test
	public void test01() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner scanner = new Scanner("./src/files/visit2.txt");
		Parser parser = new Parser(scanner);
		NodeProgram node = parser.parse();
		node.accept(declVisitor);
		node.accept(typevisitor);
		for(NodeStm stm: node.getStms())
			assertEquals(stm.getResType(), ResultType.VOID);
	}
	
	@Test
	public void test02() throws IOException, LexicalException, SyntacticException {
		Scanner scanner = new Scanner("./src/files/visit3.txt");
		Parser parser = new Parser(scanner);
		NodeProgram node = parser.parse();
		node.accept(declVisitor);
		assertEquals(declVisitor.warn, ".> a già dichiarata\n" +
								".> n da dichiarare\n" +
								".> z da dichiarare\n" +
								".> q da dichiarare\n" +
								".> s da dichiarare\n");
		assertEquals(3, SymTable.size());
	}
}
