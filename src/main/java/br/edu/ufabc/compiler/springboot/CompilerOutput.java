package br.edu.ufabc.compiler.springboot;

import java.util.ArrayList;

public class CompilerOutput {
    private String outputSource;
    private String error;
    private ArrayList<String> warnings;

    public CompilerOutput(String outputSource, String error, ArrayList<String> warnings) {
        this.outputSource = outputSource;
        this.error = error;
        this.warnings = warnings;
    }

    public String getOutputSource() {
        return outputSource;
    }

    public void setOutputSource(String outputSource) {
        this.outputSource = outputSource;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) { this.error = error; }

    public ArrayList<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(ArrayList<String> warnings) {
        this.warnings = warnings;
    }
}