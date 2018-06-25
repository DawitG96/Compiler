package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;
import semanticVisitor.DeclarationVisitor;
import semanticVisitor.TypeCheckingVisitor;
import symTable.SymTable;

public class TypeCheckingVisitorTest {

    private Parser parser;
    private DeclarationVisitor declarationVisitor;
    private TypeCheckingVisitor typeCheckingVisitor;

    @Test
    public void test01() throws IOException, LexicalException, SyntacticException {
        SymTable.init();
        parser = new Parser(new Scanner("./src/files/type_check.txt"));
        declarationVisitor = new DeclarationVisitor();
        typeCheckingVisitor = new TypeCheckingVisitor();

        NodeProgram nodeProgram = parser.parse();
        declarationVisitor.visit(nodeProgram);
        typeCheckingVisitor.visit(nodeProgram);

        assertEquals("", typeCheckingVisitor.errors);
    }
}

