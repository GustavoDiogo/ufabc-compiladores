// Generated from IsiLang.g4 by ANTLR 4.13.0
package br.edu.ufabc.compiler.core;

	import br.edu.ufabc.compiler.datastructures.IsiSymbol;
	import br.edu.ufabc.compiler.datastructures.IsiSymbolTable;
	import br.edu.ufabc.compiler.datastructures.IsiVariable;
	import br.edu.ufabc.compiler.exceptions.IsiSemanticException;
	import br.edu.ufabc.compiler.ast.IsiProgram;
	import br.edu.ufabc.compiler.ast.AbstractCommand;
	import br.edu.ufabc.compiler.ast.CommandLeitura;
	import br.edu.ufabc.compiler.ast.CommandEscrita;
	import br.edu.ufabc.compiler.ast.CommandAtribuicao;
	import br.edu.ufabc.compiler.ast.CommandDecisao;
	import br.edu.ufabc.compiler.ast.CommandEnquanto;
	import java.util.ArrayList;
	import java.util.Stack;
	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, SC=15, OP=16, ATTR=17, VIR=18, 
		ACH=19, FCH=20, OPREL=21, BOOLEAN=22, OPFUNCAOMAT=23, ID=24, NUMBER=25, 
		TEXT=26, CHAR=27, WS=28, MLCOMMENT=29, SLCOMMENT=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", 
			"FCH", "OPREL", "BOOLEAN", "OPFUNCAOMAT", "ID", "NUMBER", "TEXT", "CHAR", 
			"WS", "MLCOMMENT", "SLCOMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'numero'", "'texto'", "'caractere'", 
			"'logico'", "'leia'", "'escreva'", "'se'", "'senao'", "'enquanto'", "'faca'", 
			"'('", "')'", "'.'", null, "':='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "BOOLEAN", 
			"OPFUNCAOMAT", "ID", "NUMBER", "TEXT", "CHAR", "WS", "MLCOMMENT", "SLCOMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


		private int _tipo;
		private String _varName;
		private String _varValue;
		private ArrayList<Integer> _tipoVar = new ArrayList<Integer>();
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _opfunmat;
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> listaEnq;


		public void verificaID(String id){
			if (!symbolTable.exists(id))
				throw new IsiSemanticException("Símbolo "+id+" não declarado");
		}

		public String typeToString(int isiType) {
			switch (isiType) {
				case 0:
					return "NUMBER";
				case 1:
					return "TEXT";
				case 2:
					return "CHAR";
				case 3:
					return "BOOLEAN";
				default:
					return "";
			}
		}

		public void verificaCompatibilidade(ArrayList<Integer> tipos) {
			int tipoEsq = tipos.get(0);
			for (int tipo: tipos) {
				if (tipoEsq != tipo) {
					String errorMsg = String.format("Atribuição incorreta: %s and %s", typeToString(tipoEsq), typeToString(tipo));
					tipos.removeAll(tipos);
					throw new IsiSemanticException(errorMsg);
				}
			}
			tipos.removeAll(tipos);
		}

		public void verificaAttrib(String id) {
			if (symbolTable.exists(id) && symbolTable.get(id) == null)
				throw new IsiSemanticException(String.format("\"%s\" não foi declarada.", id));
		}

		public void checkInitialized(String id) {
	        if(!symbolTable.checkInitialized(id))
	            throw new IsiSemanticException("Símbolo "+id+" não inicializado.");
	    }

	    public void setInitialized(String id) {
	        symbolTable.setInitializedBy(id);
	    }

	    public void showWarningsUnusedVariables() {
	        for(IsiSymbol s: symbolTable.getNotUsedSymbols())
	            System.out.println("AVISO: Variável <" + s.getName() + "> foi declarada, mas não foi utilizada");
	    }

		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos())
				System.out.println(c);
		}

		public void generateCode(){
			program.generateTarget();
		}

		public ArrayList<String> getWarnings() {
	        ArrayList<String> warnings = new ArrayList<String>();
	        for(IsiSymbol s: symbolTable.getNotUsedSymbols())
	            warnings.add(String.format("AVISO: Variável <%s> foi declarada sem valor inicial", s.getName()));
	        return warnings;
	    }


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001e\u0113\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u00ac\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003"+
		"\u0015\u00b7\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00ce"+
		"\b\u0016\u0001\u0017\u0001\u0017\u0005\u0017\u00d2\b\u0017\n\u0017\f\u0017"+
		"\u00d5\t\u0017\u0001\u0018\u0004\u0018\u00d8\b\u0018\u000b\u0018\f\u0018"+
		"\u00d9\u0001\u0018\u0001\u0018\u0004\u0018\u00de\b\u0018\u000b\u0018\f"+
		"\u0018\u00df\u0003\u0018\u00e2\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0005\u0019\u00e8\b\u0019\n\u0019\f\u0019\u00eb\t\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003"+
		"\u001a\u00f3\b\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0005"+
		"\u001c\u00ff\b\u001c\n\u001c\f\u001c\u0102\t\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0005\u001d\u010d\b\u001d\n\u001d\f\u001d\u0110\t\u001d\u0001"+
		"\u001d\u0001\u001d\u0002\u00e9\u0100\u0000\u001e\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e\u0001\u0000\u0007\u0003\u0000*+--//\u0002\u0000"+
		"<<>>\u0001\u0000az\u0003\u000009AZaz\u0001\u000009\u0003\u0000\t\n\r\r"+
		"  \u0002\u0000\n\n\r\r\u0122\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000"+
		"\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000"+
		"\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;"+
		"\u0001\u0000\u0000\u0000\u0001=\u0001\u0000\u0000\u0000\u0003F\u0001\u0000"+
		"\u0000\u0000\u0005O\u0001\u0000\u0000\u0000\u0007V\u0001\u0000\u0000\u0000"+
		"\t\\\u0001\u0000\u0000\u0000\u000bf\u0001\u0000\u0000\u0000\rm\u0001\u0000"+
		"\u0000\u0000\u000fr\u0001\u0000\u0000\u0000\u0011z\u0001\u0000\u0000\u0000"+
		"\u0013}\u0001\u0000\u0000\u0000\u0015\u0083\u0001\u0000\u0000\u0000\u0017"+
		"\u008c\u0001\u0000\u0000\u0000\u0019\u0091\u0001\u0000\u0000\u0000\u001b"+
		"\u0093\u0001\u0000\u0000\u0000\u001d\u0095\u0001\u0000\u0000\u0000\u001f"+
		"\u0097\u0001\u0000\u0000\u0000!\u0099\u0001\u0000\u0000\u0000#\u009c\u0001"+
		"\u0000\u0000\u0000%\u009e\u0001\u0000\u0000\u0000\'\u00a0\u0001\u0000"+
		"\u0000\u0000)\u00ab\u0001\u0000\u0000\u0000+\u00b6\u0001\u0000\u0000\u0000"+
		"-\u00cd\u0001\u0000\u0000\u0000/\u00cf\u0001\u0000\u0000\u00001\u00d7"+
		"\u0001\u0000\u0000\u00003\u00e3\u0001\u0000\u0000\u00005\u00ee\u0001\u0000"+
		"\u0000\u00007\u00f6\u0001\u0000\u0000\u00009\u00fa\u0001\u0000\u0000\u0000"+
		";\u0108\u0001\u0000\u0000\u0000=>\u0005p\u0000\u0000>?\u0005r\u0000\u0000"+
		"?@\u0005o\u0000\u0000@A\u0005g\u0000\u0000AB\u0005r\u0000\u0000BC\u0005"+
		"a\u0000\u0000CD\u0005m\u0000\u0000DE\u0005a\u0000\u0000E\u0002\u0001\u0000"+
		"\u0000\u0000FG\u0005f\u0000\u0000GH\u0005i\u0000\u0000HI\u0005m\u0000"+
		"\u0000IJ\u0005p\u0000\u0000JK\u0005r\u0000\u0000KL\u0005o\u0000\u0000"+
		"LM\u0005g\u0000\u0000MN\u0005.\u0000\u0000N\u0004\u0001\u0000\u0000\u0000"+
		"OP\u0005n\u0000\u0000PQ\u0005u\u0000\u0000QR\u0005m\u0000\u0000RS\u0005"+
		"e\u0000\u0000ST\u0005r\u0000\u0000TU\u0005o\u0000\u0000U\u0006\u0001\u0000"+
		"\u0000\u0000VW\u0005t\u0000\u0000WX\u0005e\u0000\u0000XY\u0005x\u0000"+
		"\u0000YZ\u0005t\u0000\u0000Z[\u0005o\u0000\u0000[\b\u0001\u0000\u0000"+
		"\u0000\\]\u0005c\u0000\u0000]^\u0005a\u0000\u0000^_\u0005r\u0000\u0000"+
		"_`\u0005a\u0000\u0000`a\u0005c\u0000\u0000ab\u0005t\u0000\u0000bc\u0005"+
		"e\u0000\u0000cd\u0005r\u0000\u0000de\u0005e\u0000\u0000e\n\u0001\u0000"+
		"\u0000\u0000fg\u0005l\u0000\u0000gh\u0005o\u0000\u0000hi\u0005g\u0000"+
		"\u0000ij\u0005i\u0000\u0000jk\u0005c\u0000\u0000kl\u0005o\u0000\u0000"+
		"l\f\u0001\u0000\u0000\u0000mn\u0005l\u0000\u0000no\u0005e\u0000\u0000"+
		"op\u0005i\u0000\u0000pq\u0005a\u0000\u0000q\u000e\u0001\u0000\u0000\u0000"+
		"rs\u0005e\u0000\u0000st\u0005s\u0000\u0000tu\u0005c\u0000\u0000uv\u0005"+
		"r\u0000\u0000vw\u0005e\u0000\u0000wx\u0005v\u0000\u0000xy\u0005a\u0000"+
		"\u0000y\u0010\u0001\u0000\u0000\u0000z{\u0005s\u0000\u0000{|\u0005e\u0000"+
		"\u0000|\u0012\u0001\u0000\u0000\u0000}~\u0005s\u0000\u0000~\u007f\u0005"+
		"e\u0000\u0000\u007f\u0080\u0005n\u0000\u0000\u0080\u0081\u0005a\u0000"+
		"\u0000\u0081\u0082\u0005o\u0000\u0000\u0082\u0014\u0001\u0000\u0000\u0000"+
		"\u0083\u0084\u0005e\u0000\u0000\u0084\u0085\u0005n\u0000\u0000\u0085\u0086"+
		"\u0005q\u0000\u0000\u0086\u0087\u0005u\u0000\u0000\u0087\u0088\u0005a"+
		"\u0000\u0000\u0088\u0089\u0005n\u0000\u0000\u0089\u008a\u0005t\u0000\u0000"+
		"\u008a\u008b\u0005o\u0000\u0000\u008b\u0016\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0005f\u0000\u0000\u008d\u008e\u0005a\u0000\u0000\u008e\u008f\u0005"+
		"c\u0000\u0000\u008f\u0090\u0005a\u0000\u0000\u0090\u0018\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0005(\u0000\u0000\u0092\u001a\u0001\u0000\u0000\u0000"+
		"\u0093\u0094\u0005)\u0000\u0000\u0094\u001c\u0001\u0000\u0000\u0000\u0095"+
		"\u0096\u0005.\u0000\u0000\u0096\u001e\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0007\u0000\u0000\u0000\u0098 \u0001\u0000\u0000\u0000\u0099\u009a\u0005"+
		":\u0000\u0000\u009a\u009b\u0005=\u0000\u0000\u009b\"\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0005,\u0000\u0000\u009d$\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0005{\u0000\u0000\u009f&\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005"+
		"}\u0000\u0000\u00a1(\u0001\u0000\u0000\u0000\u00a2\u00ac\u0007\u0001\u0000"+
		"\u0000\u00a3\u00a4\u0005>\u0000\u0000\u00a4\u00ac\u0005=\u0000\u0000\u00a5"+
		"\u00a6\u0005<\u0000\u0000\u00a6\u00ac\u0005=\u0000\u0000\u00a7\u00a8\u0005"+
		"=\u0000\u0000\u00a8\u00ac\u0005=\u0000\u0000\u00a9\u00aa\u0005!\u0000"+
		"\u0000\u00aa\u00ac\u0005=\u0000\u0000\u00ab\u00a2\u0001\u0000\u0000\u0000"+
		"\u00ab\u00a3\u0001\u0000\u0000\u0000\u00ab\u00a5\u0001\u0000\u0000\u0000"+
		"\u00ab\u00a7\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000"+
		"\u00ac*\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005t\u0000\u0000\u00ae\u00af"+
		"\u0005r\u0000\u0000\u00af\u00b0\u0005u\u0000\u0000\u00b0\u00b7\u0005e"+
		"\u0000\u0000\u00b1\u00b2\u0005f\u0000\u0000\u00b2\u00b3\u0005a\u0000\u0000"+
		"\u00b3\u00b4\u0005l\u0000\u0000\u00b4\u00b5\u0005s\u0000\u0000\u00b5\u00b7"+
		"\u0005e\u0000\u0000\u00b6\u00ad\u0001\u0000\u0000\u0000\u00b6\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b7,\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005p\u0000"+
		"\u0000\u00b9\u00ba\u0005o\u0000\u0000\u00ba\u00bb\u0005t\u0000\u0000\u00bb"+
		"\u00bc\u0005e\u0000\u0000\u00bc\u00bd\u0005n\u0000\u0000\u00bd\u00be\u0005"+
		"c\u0000\u0000\u00be\u00bf\u0005i\u0000\u0000\u00bf\u00ce\u0005a\u0000"+
		"\u0000\u00c0\u00c1\u0005l\u0000\u0000\u00c1\u00c2\u0005o\u0000\u0000\u00c2"+
		"\u00c3\u0005g\u0000\u0000\u00c3\u00c4\u0005a\u0000\u0000\u00c4\u00c5\u0005"+
		"r\u0000\u0000\u00c5\u00c6\u0005i\u0000\u0000\u00c6\u00c7\u0005t\u0000"+
		"\u0000\u00c7\u00c8\u0005m\u0000\u0000\u00c8\u00ce\u0005o\u0000\u0000\u00c9"+
		"\u00ca\u0005r\u0000\u0000\u00ca\u00cb\u0005a\u0000\u0000\u00cb\u00cc\u0005"+
		"i\u0000\u0000\u00cc\u00ce\u0005z\u0000\u0000\u00cd\u00b8\u0001\u0000\u0000"+
		"\u0000\u00cd\u00c0\u0001\u0000\u0000\u0000\u00cd\u00c9\u0001\u0000\u0000"+
		"\u0000\u00ce.\u0001\u0000\u0000\u0000\u00cf\u00d3\u0007\u0002\u0000\u0000"+
		"\u00d0\u00d2\u0007\u0003\u0000\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000"+
		"\u00d2\u00d5\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d40\u0001\u0000\u0000\u0000\u00d5"+
		"\u00d3\u0001\u0000\u0000\u0000\u00d6\u00d8\u0007\u0004\u0000\u0000\u00d7"+
		"\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000\u0000\u00da"+
		"\u00e1\u0001\u0000\u0000\u0000\u00db\u00dd\u0005.\u0000\u0000\u00dc\u00de"+
		"\u0007\u0004\u0000\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00de\u00df"+
		"\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0"+
		"\u0001\u0000\u0000\u0000\u00e0\u00e2\u0001\u0000\u0000\u0000\u00e1\u00db"+
		"\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e22\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e9\u0005\"\u0000\u0000\u00e4\u00e5\u0005\\"+
		"\u0000\u0000\u00e5\u00e8\u0005\"\u0000\u0000\u00e6\u00e8\t\u0000\u0000"+
		"\u0000\u00e7\u00e4\u0001\u0000\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000"+
		"\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u00ea\u00ec\u0001\u0000\u0000"+
		"\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00ed\u0005\"\u0000\u0000"+
		"\u00ed4\u0001\u0000\u0000\u0000\u00ee\u00f2\u0005\'\u0000\u0000\u00ef"+
		"\u00f0\u0005\\\u0000\u0000\u00f0\u00f3\u0005\'\u0000\u0000\u00f1\u00f3"+
		"\t\u0000\u0000\u0000\u00f2\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005"+
		"\'\u0000\u0000\u00f56\u0001\u0000\u0000\u0000\u00f6\u00f7\u0007\u0005"+
		"\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u00f9\u0006\u001b"+
		"\u0000\u0000\u00f98\u0001\u0000\u0000\u0000\u00fa\u00fb\u0005/\u0000\u0000"+
		"\u00fb\u00fc\u0005*\u0000\u0000\u00fc\u0100\u0001\u0000\u0000\u0000\u00fd"+
		"\u00ff\t\u0000\u0000\u0000\u00fe\u00fd\u0001\u0000\u0000\u0000\u00ff\u0102"+
		"\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0100\u00fe"+
		"\u0001\u0000\u0000\u0000\u0101\u0103\u0001\u0000\u0000\u0000\u0102\u0100"+
		"\u0001\u0000\u0000\u0000\u0103\u0104\u0005*\u0000\u0000\u0104\u0105\u0005"+
		"/\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000\u0106\u0107\u0006\u001c"+
		"\u0000\u0000\u0107:\u0001\u0000\u0000\u0000\u0108\u0109\u0005/\u0000\u0000"+
		"\u0109\u010a\u0005/\u0000\u0000\u010a\u010e\u0001\u0000\u0000\u0000\u010b"+
		"\u010d\b\u0006\u0000\u0000\u010c\u010b\u0001\u0000\u0000\u0000\u010d\u0110"+
		"\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000\u0000\u0000\u010e\u010f"+
		"\u0001\u0000\u0000\u0000\u010f\u0111\u0001\u0000\u0000\u0000\u0110\u010e"+
		"\u0001\u0000\u0000\u0000\u0111\u0112\u0006\u001d\u0000\u0000\u0112<\u0001"+
		"\u0000\u0000\u0000\u000e\u0000\u00ab\u00b6\u00cd\u00d1\u00d3\u00d9\u00df"+
		"\u00e1\u00e7\u00e9\u00f2\u0100\u010e\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}