package ast;

import semanticVisitor.AbsVisitor;

public class NodeCost extends NodeExpr {
	
	private String value;
	private LangType lang;
	
	public NodeCost(String value, LangType lang) {
		this.value=value;
		this.setLang(lang);
	}
	
	public String toString() {
		return value;
	}

	public LangType getLang() {
		return lang;
	}

	public void setLang(LangType lang) {
		this.lang = lang;
	}
	
	@Override
	public void accept(AbsVisitor a) {
		a.visit(this);
	}
}
