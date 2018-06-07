package ast;

import semanticVisitor.AbsVisitor;

public abstract class NodeAST {

	private ResultType resType;
	//TODO decl, nodeID se definito in symboltable fa la entry, negli altri si propaga la visita
	
	public abstract void accept(AbsVisitor a);
	public NodeAST() {
	}

	public ResultType getResType() {
		return resType;
	}

	public void setResType(ResultType resType) {
		this.resType = resType;
	}

}
