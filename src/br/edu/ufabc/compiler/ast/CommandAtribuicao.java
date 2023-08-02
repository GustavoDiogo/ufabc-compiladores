package br.edu.ufabc.compiler.ast;

public class CommandAtribuicao extends AbstractCommand{

	private String id;
	private String expr;
	
	public CommandAtribuicao(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	@Override
	public String generateJavaCode() {
		expr = expr.replace("logaritmo", "Math.log");
		expr = expr.replace("potencia", "Math.pow");
		expr = expr.replace("raiz", "Math.pow");
		return id + " = "+expr+";";
	}
	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}
}