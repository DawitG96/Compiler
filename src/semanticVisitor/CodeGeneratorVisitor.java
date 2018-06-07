package semanticVisitor;

import ast.NodeAssign;
import ast.NodeConv;
import ast.NodeCost;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeId;
import ast.NodeOpBin;
import ast.NodePrint;
import ast.NodeProgram;
import ast.NodeStm;

public class CodeGeneratorVisitor extends AbsVisitor {
	
	public StringBuffer generated = new StringBuffer();
	
	public StringBuffer getCode() {
		return generated;
	}
	
	@Override
	public void visit(NodeProgram n) {
		// TODO Auto-generated method stub
		for (NodeDecl node: n.getDecs()) 
			node.accept(this);
		for (NodeStm node: n.getStms()) {
			node.accept(this);
			generated.append("\n");
		}
	}

	@Override
	public void visit(NodeId n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NodeDecl n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NodePrint n) {
		generated.append(" l"+ n.getId()+" p");
	}

	@Override
	public void visit(NodeAssign n) {
		n.getExpr().accept(this);
		generated.append(" s"+n.getId());
		generated.append("\n 0 k\n");
	}

	@Override
	public void visit(NodeCost n) {	
		generated.append(" "+n);

	}

	@Override
	public void visit(NodeConv n) {
		n.getExpr().accept(this);
		generated.append("\n 5 k \n");
	}

	@Override
	public void visit(NodeDeref n) {
		generated.append(" l"+n.getId());

	}

	@Override
	public void visit(NodeOpBin n) {
		n.getLeft().accept(this);
		n.getRight().accept(this);
		
		if(n.getOperand().toString().equals("MINUS"))
			generated.append(" -");
		else
			generated.append(" +");
				
	}

	@Override
	public void visit(NodeStm n) {
		// TODO Auto-generated method stub
		
	}

}