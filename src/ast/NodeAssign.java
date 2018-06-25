package ast;

import semanticVisitor.AbsVisitor;

public class NodeAssign extends NodeStm {
	
	private NodeId id;
	private NodeExpr expr;
	public NodeAssign(NodeId id, NodeExpr expr) {
		this.id=id;
		this.expr=expr;
	}
	
	public NodeExpr getExpr() {
		return expr;
	}
	
	@Override
	public String toString() {
			return id.toString()+" = "+expr.toString();
	}

	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}

	public NodeId getId() {
		return id;
	}

	public void setExpr(NodeExpr node) {
		this.expr = node;
		
	}
}
