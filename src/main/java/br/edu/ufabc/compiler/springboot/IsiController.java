package br.edu.ufabc.compiler.springboot;

import br.edu.ufabc.compiler.springboot.CompilerOutput;
import br.edu.ufabc.compiler.springboot.IsiCompiler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RestController
public class IsiController {
    private String decode(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
    }
    private IsiCompiler compiler = new IsiCompiler();
    @CrossOrigin
    @GetMapping("/compile")
    public CompilerOutput compile(
            @RequestParam(
                    value="codigo",
                    defaultValue = "programa texto a. a := \"Ola mundo\". escreva(a). fimprog.",
                    required=false
            ) String codigo,
            @RequestParam(
                    value="linguagem",
                    defaultValue = "java",
                    required=false
            ) String linguagem
    ) {
        CompilerOutput compilerOutput;
        try {
            compilerOutput = compiler.compile(decode(codigo), decode(linguagem));
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            ArrayList<String> warnings = new ArrayList<String>();
            compilerOutput = new CompilerOutput("Invalid code!", ex.getMessage(), warnings);
        }
        return compilerOutput;
    }
}