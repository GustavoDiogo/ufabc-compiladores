package br.edu.ufabc.compiler.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.edu.ufabc.compiler.exceptions.IsiSemanticException;
import br.edu.ufabc.compiler.core.IsiLangLexer;
import br.edu.ufabc.compiler.core.IsiLangParser;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			//leio o arquivo "input.isi" e isso é entrada para o analisador lexico
			lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			
			
			// crio um fluxo de tokens para passar para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			//crio meu parser a partir desse tokenStream
			parser = new IsiLangParser(tokenStream);
			
			parser.prog();
			
			System.out.println("IsiCompilado com sucesso!");
			
			parser.exibeComandos();
			
			parser.generateCode();
			
			
		} 
		catch (IsiSemanticException ex) {
			System.err.println("Erro de semântica: " + ex.getMessage());
		}
		
		
		
		catch (Exception e) {
			System.err.println("Erro não mapeado:" + e.getMessage());
		}

	}
}
