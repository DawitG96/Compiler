package ast;
import semanticVisitor.AbsVisitor;
import symTable.*;
public class NodeId extends NodeAST {
	
	private String name;
	private STEntry entry;
	
	public NodeId(String name) {
		this.name=name;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}

	public STEntry getEntry() {
		return entry;
	}

	public void setEntry(STEntry entry) {
		this.entry = entry;
	}
}
