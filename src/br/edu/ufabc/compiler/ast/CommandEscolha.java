package br.edu.ufabc.compiler.ast;

import java.util.ArrayList;

public class CommandEscolha extends AbstractCommand {
 
	private String condition;
	private String cases;
	private String cases3;
	private ArrayList<AbstractCommand> listaSwitch;
	private ArrayList<AbstractCommand> listaSwitch2;
	
	public CommandEscolha(String condition, String cases, String cases3, ArrayList<AbstractCommand> listaSwitch, ArrayList<AbstractCommand> listaSwitch2) {
		this.condition = condition;
		this.cases = cases;
		this.cases3 = cases3;
		this.listaSwitch = listaSwitch;
		this.listaSwitch2 = listaSwitch2;
	}
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("switch ("+condition+") {\n");
		str.append("	case "+cases+": \n");
		for (AbstractCommand cmd: listaSwitch) {
			str.append("		");
			str.append(cmd.generateJavaCode());
		}
		str.append("\n");
		str.append("		break;\n");

		str.append("	case "+cases3+": \n");
		for (AbstractCommand cmd: listaSwitch2) {
			str.append("		");
			str.append(cmd.generateJavaCode());
		}
		str.append("\n");
		str.append("		break;\n");
		str.append("}\n");
		return str.toString();
	}
	
	@Override
	public String generatePythonCode() {
		StringBuilder str = new StringBuilder();
		str.append("		if ("+condition+" == "+cases+") :\n");		
		for (AbstractCommand cmd: listaSwitch) {
			str.append("	");
			str.append(cmd.generatePythonCode());
		}


		str.append("		elif("+condition+" == "+cases3+") : \n");
		for (AbstractCommand cmd: listaSwitch2) {
			str.append("	");
			str.append(cmd.generatePythonCode());
		}		
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandEscolha [condition=" + condition + ", cases=" + cases + ", cases3=" + cases3 + ", listaSwitch=" + listaSwitch+ ", listaSwitch2=" + listaSwitch2
				+ "]";
	}
}
