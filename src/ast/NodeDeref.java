package ast;

import semanticVisitor.AbsVisitor;

public class NodeDeref extends NodeExpr {
	
	private NodeId id;
	public NodeDeref(NodeId id) {
		this.id=id;
	}
	
	public NodeId getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
	
	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}
}
