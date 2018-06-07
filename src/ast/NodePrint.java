package ast;

import semanticVisitor.AbsVisitor;

public class NodePrint extends NodeStm {
	
	private NodeId id;
	public NodePrint(NodeId id) {
		this.id=id;
	}
	
	public String toString() {
		return "PRINT: "+id.toString();
	}
	

	public NodeId getId() {
		return id;
	}
	
	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}
}
