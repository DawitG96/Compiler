package semanticVisitor;

import ast.NodeConv;
import ast.NodeExpr;
import ast.ResultType;

/**
 * Classe che si occupa della conversione di tipo
 * @author Dawit
 *
 */
public class TypeCheckingUtil {
	
	/**
	 * Funzione che restituisce true se uno dei due Nodi è di tipo float e quindi la conversione è possibile
	 * 
	 * @param tipo1 ResultType del Nodo espressione a sx
	 * @param tipo2 ResultType del Nodo espressione a dx
	 * @return true se uno dei due nodi è Float, false otherwise.
	 */
	@SuppressWarnings("unused")
	static public boolean generalize(ResultType tipo1, ResultType tipo2) {
		return (tipo1==ResultType.FLOAT || tipo2==ResultType.FLOAT);
	}
	
	/**
	 * Metodo che chiama la funzione convert() nel caso in cui la generalize() dei due Node expressione restituisse true
	 * @param exp1 Nodo espressione a sx
	 * @param exp2 Nodo espressione a dx
	 * @return array di Nodi espressione-
	 */
	@SuppressWarnings("unused")
	static public NodeExpr[] consistent(NodeExpr exp1,NodeExpr exp2) {
		if(generalize(exp1.getResType(), exp2.getResType())) {
			exp1 = convert(exp1);
			exp2 =convert(exp2);
			return new NodeExpr[]{exp1, exp2};
		}
		
		return new NodeExpr[]{exp1, exp2};
	}
	
	/**
	 * Metodo che restituisce un NodeConv nel caso in cui il Node espressione fosse di tipo Int
	 * @param expr Nodo espressione
	 * @return NodeConv se il NodeExpr necessitasse la conversione, NodeExpr otherwise.
	 */
	static public NodeExpr convert(NodeExpr expr) {
		if(expr.getResType() == ResultType.FLOAT || expr.getResType() == ResultType.TYPE_ERROR)
			return expr;
		else 
			return new NodeConv (expr);
	}
}
