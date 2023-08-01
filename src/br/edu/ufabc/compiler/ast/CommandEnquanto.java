package br.edu.ufabc.compiler.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand {

	private String condition;
	private ArrayList<AbstractCommand> listaCmdWhile;
	
	
	public CommandEnquanto(String condition, ArrayList<AbstractCommand> lw ) {
		this.condition = condition;
		this.listaCmdWhile = lw;		
	}
	

	@Override
	public String generateJavaCode() {
		
		StringBuilder str = new StringBuilder();
		str.append("\n");
		str.append("while (" + condition + ") {");
		for (AbstractCommand cmd: listaCmdWhile) {
			str.append(cmd.generateJavaCode());
			
		}
		str.append("}");
		str.append("\n");
				
		return str.toString();
	}
	
	@Override
	public String generatePythonCode() {
		
		StringBuilder str = new StringBuilder();		
		str.append("		while (" + condition + ") :\n");
		for (AbstractCommand cmd: listaCmdWhile) {
			str.append("	"+cmd.generatePythonCode());
			
		}	
				
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandEnquanto [condition=" + condition + ", listaTrue=" + listaCmdWhile + "]";
	}
}
