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
	
	private StringBuffer generated = new StringBuffer();
	private boolean first = true;
	
	public String getCode() {
		return generated.toString();
	}
	
	@Override
	public void visit(NodeProgram n) {
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
		generated.append("l"+ n.getId()+" p");
		generated.append(" sf");
	}

	@Override
	public void visit(NodeAssign n) {
		first = true;
		n.getExpr().accept(this);
		
		generated.append(" s"+n.getId());
		generated.append("\n0 k\n");
	}

	@Override
	public void visit(NodeCost n) {
		generated.append((first?"":" ")+n);
		first = false;
	}

	@Override
	public void visit(NodeConv n) {
		n.getExpr().accept(this);
		generated.append(" 5 k");
	}

	@Override
	public void visit(NodeDeref n) {
		generated.append((first?"l":" l")+n.getId());
		first=false;
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
}