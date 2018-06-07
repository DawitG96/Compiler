package ast;

import semanticVisitor.AbsVisitor;

public class NodeOpBin extends NodeExpr {
	

	private LangOper op;
	private NodeExpr left;
	private NodeExpr right;
	public NodeOpBin(NodeExpr left, LangOper op, NodeExpr right) {
		this.left=left;
		this.op=op;
		this.right=right;
	}
	
	public String toString() {
		return left.toString()+" "+op+" "+right.toString();
	}
	
	public NodeExpr getLeft() {
		return left;
	}
	
	public NodeExpr getRight() {
		return right;
	}
	
	public LangOper getOperand() {
		return op;
	}
	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}
}
