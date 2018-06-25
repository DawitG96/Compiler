package ast;

import semanticVisitor.AbsVisitor;

public abstract class NodeStm extends NodeAST{
	public NodeStm() {
	}
	
	@Override
	public void accept(AbsVisitor a) {}
}
