package br.edu.ufabc.compiler.ast;

import br.edu.ufabc.compiler.datastructures.IsiVariable;

public class CommandLeitura extends AbstractCommand {
	
	private String id;
	private IsiVariable var;
	
	public CommandLeitura (String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}
	@Override
	public String generateJavaCode() {
		switch (var.getType()) {
			case IsiVariable.NUMBER: 
				return id +"= _key.nextDouble();";
			case IsiVariable.TEXT:
				return id +"= _key.nextLine();";
			case IsiVariable.CHAR:
				return id +"= _key.next().charAt(0);";
			case IsiVariable.BOOLEAN:
				return id +"= _key.nextBoolean();";
			default:
				return "";
		}
	}
	
	@Override
	public String generatePythonCode() {
		switch (var.getType()) {
		case IsiVariable.NUMBER: 
			return "		" + id +"= float(input())\n";
		case IsiVariable.TEXT:
			return "		" + id +"= _key.input()\n;";
		case IsiVariable.CHAR:
			return "		" + id +"= _key.input()\n;";
		case IsiVariable.BOOLEAN:
			return "		" + id +"= _key.input()\n;";
		default:
			return "";
		}
	}
	
	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}

}