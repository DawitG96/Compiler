package semanticVisitor;

import ast.*;
/**
 * Classe che definisce le varie visite sui Nodi
 * @author Dawit
 *
 */
public abstract class AbsVisitor {
	
	public abstract  void visit (NodeProgram n);
	
	public abstract  void visit (NodeId n);
	
	public abstract void visit(NodeDecl n);
	
	public abstract void visit(NodePrint n);

	public abstract void  visit(NodeAssign n);

	public abstract void visit(NodeCost n);

	public abstract void visit(NodeConv n);

	public abstract void visit(NodeDeref n);

	public abstract void visit(NodeOpBin n);

	public abstract void visit(NodeStm n);

}