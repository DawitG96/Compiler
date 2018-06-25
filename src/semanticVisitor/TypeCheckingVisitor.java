package semanticVisitor;

import ast.*;
import symTable.*;

/**
 * Classe che si occupa della visita analizzandone il tipo
 * @author Dawit
 *
 */
public class TypeCheckingVisitor extends AbsVisitor {
	public String errors = "";
	
	@Override
	public void visit(NodeProgram n) {
		for (NodeDecl node : n.getDecs()){
			node.accept(this);
			if (node.getResType() == ResultType.TYPE_ERROR)
				n.setResType(ResultType.TYPE_ERROR);
		}
		
		for (NodeStm node: n.getStms()){
			node.accept(this);
			if (node.getResType() == ResultType.TYPE_ERROR)
				n.setResType(ResultType.TYPE_ERROR);
		}
	}

	@Override
	public void visit(NodePrint n) {
		n.getResType();
		if(n.getId().getResType() == ResultType.TYPE_ERROR)
			n.setResType(ResultType.TYPE_ERROR);
		else
			n.setResType(ResultType.VOID);
	}

	@Override
	public void visit(NodeAssign n) {
		ResultType temp;
		
		STEntry entry = SymTable.lookup(n.getId().toString());
		n.getExpr().accept(this);
		if (entry.getType() == LangType.FLOAT)
			temp = ResultType.FLOAT;
		else 
			temp = ResultType.INT;
		
		
		if (temp == n.getExpr().getResType())
			n.setResType(ResultType.VOID);
		
		else {
			if (temp == ResultType.FLOAT && n.getExpr().getResType() == ResultType.INT) {
				NodeExpr node = TypeCheckingUtil.convert(n.getExpr());
				if (node.getClass() == NodeConv.class) {
					n.setExpr(node);
					node.accept(this);
				}
					
				n.setResType(ResultType.VOID);
			}
			else {
				n.setResType(ResultType.TYPE_ERROR);
				errors += "Errore: conversione impossibile da FLOAT a INT\n";
			}
		}
	}

	@Override
	public void visit(NodeCost n) {

		if (n.getLang() == LangType.FLOAT)
			n.setResType(ResultType.FLOAT);
		else 
			n.setResType(ResultType.INT);

	}

	@Override
	public void visit(NodeDeref n) {
		STEntry tmp = SymTable.lookup(n.getId().toString());
		if (tmp.getType() == LangType.FLOAT)
			n.setResType(ResultType.FLOAT);
		else n.setResType(ResultType.INT);
		
	}
	
	@Override
	public void visit(NodeOpBin n) {
		n.getLeft().accept(this);
		n.getRight().accept(this);
		NodeExpr [] consistentExpr = TypeCheckingUtil.consistent(n.getLeft(),n.getRight());
		
		if (consistentExpr[0].getResType() == ResultType.INT && consistentExpr[1].getResType() == ResultType.INT)
			n.setResType(ResultType.INT);
		else {
			n.setResType(ResultType.FLOAT);
		}
		
	}
	
	@Override
	public void visit(NodeDecl n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NodeId n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NodeConv n) {
		// TODO Auto-generated method stub
		
	}


}
