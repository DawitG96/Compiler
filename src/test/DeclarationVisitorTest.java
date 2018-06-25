package test;

import semanticVisitor.DeclarationVisitor;
import ast.NodeDecl;
import ast.NodeProgram;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;
import symTable.SymTable;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class DeclarationVisitorTest {

    private Parser parser;
    private DeclarationVisitor visitor;

    @Test
    public void test01() throws IOException, LexicalException, SyntacticException {
        SymTable.init();
        parser = new Parser(new Scanner("./src/files/decl_test1.txt"));
        visitor = new DeclarationVisitor();

        NodeProgram nodeProgram = parser.parse();

        List<NodeDecl> decls = nodeProgram.getDecs();

        for(NodeDecl nodeDecl : decls)
            visitor.visit(nodeDecl);

        assertEquals("", visitor.warn);
        assertEquals("symbol table\n" +
                "=============\n" +
                "a   \tINT\n" +
                "b   \tFLOAT\n", SymTable.toStr());
    }

    @Test
    void test02() throws IOException, LexicalException, SyntacticException {
        SymTable.init();
        parser = new Parser(new Scanner("./src/files/decl_test2.txt"));
        visitor = new DeclarationVisitor();

        NodeProgram nodeProgram = parser.parse();

        List<NodeDecl> decls = nodeProgram.getDecs();

        for(NodeDecl nodeDecl : decls)
            visitor.visit(nodeDecl);

        assertEquals("b -> già dichiarata\n", visitor.warn);
        assertEquals("symbol table\n" +
                "=============\n" +
                "a   \tINT\n" +
                "b   \tINT\n" +
                "c   \tFLOAT\n", SymTable.toStr());
    }
    
    @Test
	public void test03() throws IOException, LexicalException, SyntacticException {
    	SymTable.init();
    	Scanner scanner = new Scanner("./src/files/visit3.txt");
		Parser parser = new Parser(scanner);
		NodeProgram node = parser.parse();
		visitor = new DeclarationVisitor();
		node.accept(visitor);
		System.out.println(visitor.warn);
		assertEquals("a -> già dichiarata\n" +
				"n -> da dichiarare\n" +
				"z -> da dichiarare\n" +
				"q -> da dichiarare\n" +
				"s -> da dichiarare\n",visitor.warn);
		assertEquals(3, SymTable.size());
	}
}
