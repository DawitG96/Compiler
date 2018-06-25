package semanticVisitor;


import symTable.STEntry;
import symTable.SymTable;
import ast.LangType;
import ast.NodeAssign;
import ast.NodeConv;
import ast.NodeCost;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeId;
import ast.NodeOpBin;
import ast.NodePrint;
import ast.NodeProgram;
import ast.NodeStm;
import ast.ResultType;

/**
 * Classe che si occupa della visita sulla dichiarazione dei nodi
 * @author Dawit
 *
 */
public class DeclarationVisitor extends AbsVisitor {
	public String warn = "";
	
	@Override
	public void visit(NodeId n) {
		STEntry temp = SymTable.lookup(n.toString());
		if (temp != null){
			n.setEntry(temp);
			if (temp.getType() == LangType.FLOAT)
			n.setResType(ResultType.FLOAT);
			
			if (temp.getType() == LangType.INT)
				n.setResType(ResultType.INT);
			
		}
		else {
			n.setResType(ResultType.TYPE_ERROR);
			 warn += n.toString()+" -> da dichiarare\n";
		}
	}

	@Override
	public void visit(NodeDecl n) {
		
		if(SymTable.enter(n.getId().toString(), new STEntry(n.getType()))==false){
			
			n.setResType(ResultType.TYPE_ERROR);
			warn += n.getId()+" -> già dichiarata\n";
		}
		else n.setResType(ResultType.VOID);
	}
	
	@Override
	public void visit(NodeConv n) {
		n.getExpr().accept(this);	
	}

	@Override
	public void visit(NodeProgram n) {


		for (NodeDecl node : n.getDecs())
			node.accept(this);
		
		for (NodeStm node: n.getStms()){
			node.accept(this);
		
		}
	}

	@Override
	public void visit(NodePrint n) {
		n.getId().accept(this);	
	}

	@Override
	public void visit(NodeAssign n) {
		n.getId().accept(this); 
		n.getExpr().accept(this);
	}

	@Override
	public void visit(NodeCost n) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void visit(NodeDeref n) {
		// TODO Auto-generated method stub
		n.getId().accept(this);
	}

	@Override
	public void visit(NodeOpBin n) {
		// TODO Auto-generated method stub
		n.getLeft().accept(this);
		n.getRight().accept(this);
	}
}
