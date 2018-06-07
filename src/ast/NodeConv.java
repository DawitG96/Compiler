package ast;

import semanticVisitor.AbsVisitor;

public class NodeConv extends NodeExpr {
	
	private NodeExpr expr;

	public NodeConv(NodeExpr expr) {
		this.expr = expr;
		setExpr();
	}
	
	public NodeExpr getExpr() {
		// TODO Auto-generated method stub
		return expr;
	}
	
	public void setExpr() {
		expr.setResType(ResultType.FLOAT);
	}
	
	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}

	
}