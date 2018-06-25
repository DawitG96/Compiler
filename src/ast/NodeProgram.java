package ast;

import java.util.ArrayList;

import semanticVisitor.AbsVisitor;

public class NodeProgram extends NodeAST{
	
	private ArrayList<NodeDecl> decs;
	private ArrayList<NodeStm> stats;

	public NodeProgram(ArrayList<NodeDecl> decs, ArrayList<NodeStm> stats) {
		this.decs=decs;
		this.stats=stats;
	}
	
	public ArrayList<NodeDecl> getDecs() {
		return decs;
	}
	
	public ArrayList<NodeStm> getStms() {
		return stats;
	}
	
	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}
}
