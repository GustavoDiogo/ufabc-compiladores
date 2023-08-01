package br.edu.ufabc.compiler.ast;

import br.edu.ufabc.compiler.datastructures.IsiVariable;

public class CommandLeitura extends AbstractCommand{
	
	private String id;
	private IsiVariable var;
	private String typesReturn;
	
	public CommandLeitura (String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}
	
	@Override
	public String generateJavaCode() {
		if (var.getType()==IsiVariable.NUMBER) {
			typesReturn =  "nextDouble();";
		} else if (var.getType()==IsiVariable.NUMBERINT) {
			typesReturn =  "nextInt();";
		} else {
			typesReturn = "nextLine();";
		}
		return id +"= _key." + typesReturn;	
	}
	
	@Override
	public String generatePythonCode() {
		if (var.getType()==IsiVariable.NUMBER) {
			typesReturn =  "float(input())\n";
		} else if (var.getType()==IsiVariable.NUMBERINT) {
			typesReturn =  "int(input())\n";
		} else {
			typesReturn = "input()\n";
		}
		return "		"+ id + " = " + typesReturn;
	}

	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}
	
	

}