package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;
import symTable.SymTable;
import semanticVisitor.CodeGeneratorVisitor;
import semanticVisitor.DeclarationVisitor;
import semanticVisitor.TypeCheckingVisitor;

public class CodeGeneratorTest {
	 private Parser parser;
	 private DeclarationVisitor declarationVisitor;
	 private TypeCheckingVisitor typeCheckingVisitor;
	 private CodeGeneratorVisitor codeGeneratorVisitor;

	 @Test
	 public void test01() throws IOException, LexicalException, SyntacticException {
		 SymTable.init();
	     parser = new Parser(new Scanner("./src/files/codGen_test.txt"));
	     declarationVisitor = new DeclarationVisitor();
	     typeCheckingVisitor = new TypeCheckingVisitor();
	     codeGeneratorVisitor = new CodeGeneratorVisitor();
	     
	     NodeProgram nodeProgram = parser.parse();
	     declarationVisitor.visit(nodeProgram);
	     typeCheckingVisitor.visit(nodeProgram);
	     codeGeneratorVisitor.visit(nodeProgram);

	     BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/files/checkGenCode.txt"));

	     StringBuilder stringBuilder = new StringBuilder();
	     String line = bufferedReader.readLine();
	     while (line != null){
	    	 stringBuilder.append(line);
	    	 stringBuilder.append("\n");
	     	 line = bufferedReader.readLine();
	     }

	     bufferedReader.close(); 
	     assertEquals(stringBuilder.toString(), codeGeneratorVisitor.getCode());
	 }
}
