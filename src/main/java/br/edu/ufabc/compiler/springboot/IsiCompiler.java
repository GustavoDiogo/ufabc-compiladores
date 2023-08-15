package br.edu.ufabc.compiler.springboot;

import br.edu.ufabc.compiler.springboot.CompilerOutput;

import java.util.ArrayList;
import java.util.Arrays;

import br.edu.ufabc.compiler.exceptions.IsiSemanticException;
import br.edu.ufabc.compiler.core.IsiLangLexer;
import br.edu.ufabc.compiler.core.IsiLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class IsiCompiler {
    public CompilerOutput compile(String codigo, String linguagem) {
        String content = "";
        ArrayList<String> warnings = new ArrayList<String>();//Arrays.asList("Warnings 1","Warnings 2"));
        String error = "";

        try {
            IsiLangLexer lexer;
            IsiLangParser parser;
            lexer = new IsiLangLexer(CharStreams.fromString(codigo));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            parser = new IsiLangParser(tokenStream);
            parser.prog();
            parser.generateCode();
            
            if (linguagem.contains("python")) {
            	content = CharStreams.fromFileName("MainClass.py").toString();
            } else {
            	content = CharStreams.fromFileName("MainClass.java").toString();
            }
            
            warnings = parser.getWarnings();
        }
        catch(IsiSemanticException ex) {
            error = "Erro de semântica: - " + ex.getMessage();
        }
        catch (NullPointerException ex) {
            error = "Erro de compilação - " + ex.getMessage();
        }

        catch(Exception ex) {
            ex.printStackTrace();
            System.err.println("Erro não mapeado - "+ex.getMessage());
        }

        return new CompilerOutput(content, error, warnings);
    }
}