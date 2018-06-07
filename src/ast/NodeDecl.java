package ast;

import semanticVisitor.AbsVisitor;

public class NodeDecl extends NodeAST {

	private NodeId id;
	private LangType type;
	
	public NodeDecl(NodeId id, LangType type) {
		this.id=id;
		this.type=type;
	}
	
	@Override
	public String toString() {
		return id.toString()+"-"+type;
		
	}
	
	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}

	public NodeId getId() {
		return id;
	}

	public LangType getType() {
		return type;
	}
	
	public void setType(LangType type) {
		this.type = type;
	}
}
