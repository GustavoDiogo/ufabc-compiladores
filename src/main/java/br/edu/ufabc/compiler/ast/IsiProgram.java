package br.edu.ufabc.compiler.ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import br.edu.ufabc.compiler.datastructures.IsiSymbol;
import br.edu.ufabc.compiler.datastructures.IsiSymbolTable;

public class IsiProgram {
	private IsiSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;

	public void generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n");
		str.append("public class MainClass { \n");
		str.append("\tpublic static void main(String args[]){\n ");
		str.append("\t\tScanner _key = new Scanner(System.in);\n");
		for (IsiSymbol symbol: varTable.getAll()) {
			str.append("\t\t"+symbol.generateJavaCode()+"\n");
		}
		for (AbstractCommand command: comandos) {
			str.append("\t\t"+command.generateJavaCode()+"\n");
		}
		str.append("\t}\n");
		str.append("}");
		
		StringBuilder py = new StringBuilder();
		py.append("class MainClass :\n");
		py.append("	@staticmethod\n");
		py.append("	def main(args) :\n");
		py.append("		_key = 'Python-inputs'\n");
		for (IsiSymbol symbol: varTable.getAll()) {
			py.append(symbol.generatePythonCode());
		}
		for (AbstractCommand command: comandos) {
			py.append(command.generatePythonCode());
		}		
		py.append("\n"+"if __name__=='__main__':\n");
		py.append("	MainClass.main([])");

		try {
			// java file
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
			
			// python file
			FileWriter frpy = new FileWriter(new File("MainClass.py"));
			frpy.write(py.toString());
			frpy.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IsiSymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(IsiSymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}