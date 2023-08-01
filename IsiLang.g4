grammar IsiLang;

@header {
	import br.com.professorisidro.isilanguage.datastructure.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructure.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.datastructure.IsiVariable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import br.com.professorisidro.isilanguage.ast.CommandEnquanto;
	import br.com.professorisidro.isilanguage.ast.CommandEscolha;
	import java.util.ArrayList;
	import java.util.Stack;
	
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	
	
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	
	private String _exprDecision;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	
	private String _exprWhile;
	private ArrayList<AbstractCommand> listaCmdWhile;
	
	private String _switch;
	private String _switch2;
	private String _switch3;
	private ArrayList<AbstractCommand> listaSwitch1;
	private ArrayList<AbstractCommand> listaSwitch2;
	
	
	
	public void verificaID (String id) {
		if (!symbolTable.exists(id)) {
			throw new IsiSemanticException("Symbol" + id + " not declared");
		
		}
	
	}
	
	
	public void notUsedSymbol (IsiSymbol symbol) {
		IsiVariable variable = (IsiVariable) symbol;
		
		if (variable.getValue() == null) {
			System.out.println("Symbol " + symbol.getName() + " is not used");
		
		}
	}
	
	
	public void exibeComandos() {
		for(AbstractCommand c: program.getComandos()) {
			System.out.println(c);
			
		}
	
	
	}
	
	public void generateCode() {
		program.generateTarget();
	
	
	}

}



prog	: 'programa' decl bloco 'fimprog;'
		{	program.setVarTable(symbolTable);
			program.setComandos(stack.pop());
			
			for(IsiSymbol symbol : symbolTable.getAll()) {
           	  	 notUsedSymbol(symbol);
           	}  
		}
		;
		
		
decl 	: (declaravar)+
		;
		
declaravar	: tipo ID {
						_varName = _input.LT(-1).getText();
						_varValue = null;
						symbol = new IsiVariable(_varName, _tipo, _varValue);
						
						
						if (!symbolTable.exists(_varName)) {
							symbolTable.add(symbol);
						}else {
							throw new IsiSemanticException ("Symbol " + _varName + " alread declared");
						}
						

					}



			 (	VIR
			 	ID {
						_varName = _input.LT(-1).getText();
						_varValue = null;
						symbol = new IsiVariable(_varName, _tipo, _varValue);
						
						if (!symbolTable.exists(_varName)) {
							symbolTable.add(symbol);
						}else {
							throw new IsiSemanticException ("Symbol " + _varName + " alread declared");
						}

					}
			 		)* SC
			;

tipo	: 'numero'	{_tipo = IsiVariable.NUMBER;}
		| 'texto'	{_tipo = IsiVariable.TEXT;}
		| 'inteiro'  { _tipo = IsiVariable.NUMBERINT;  }
		;

		
bloco	: 
		{ 	curThread = new ArrayList<AbstractCommand>();
			stack.push(curThread);
		}
		(cmd)+
		;
		
cmd		: cmdleitura 
		 | cmdescrita	
		 | cmdattrib
		 | cmdselecao
		 | cmdenquanto
		 | cmdswitch
		;


		
cmdswitch	:	'escolha'	AP
							(ID | NUMBER | NUMBERINT) { verificaID(_input.LT(-1).getText());
									_switch = _input.LT(-1).getText();
								}
							FP
							ACH
							'caso'	(ID | NUMBER | NUMBERINT) {_switch2 = _input.LT(-1).getText(); }	PO
									{ curThread = new ArrayList<AbstractCommand>(); 
									stack.push(curThread);
									}
									(cmd)+
							{
								listaSwitch1 = stack.pop();	
							} 
								
							'caso'	(ID | NUMBER | NUMBERINT) {_switch3 = _input.LT(-1).getText(); }	PO
									{ curThread = new ArrayList<AbstractCommand>(); 
									stack.push(curThread);
									}
									(cmd)+
							{
								listaSwitch2 = stack.pop();	
							}

							FCH
							{
								CommandEscolha cmd = new CommandEscolha(_switch, _switch2, _switch3, listaSwitch1, listaSwitch2);
								stack.peek().add(cmd);
							}	
			;
		
		
cmdenquanto	: 'enquanto' 	AP
							
							ID {	verificaID( _input.LT(-1).getText() );
				 	 				_exprWhile = _input.LT(-1).getText();
				 	 			}
							
							OPREL { _exprWhile += _input.LT(-1).getText(); }
							
							(ID | NUMBER) {_exprWhile += _input.LT(-1).getText();}
							
							FP 
							
							ACH
							{ 	curThread = new ArrayList<AbstractCommand>();
								stack.push(curThread);
							}
							
							(cmd)+ 
							
							FCH
							{	listaCmdWhile = stack.pop();
								CommandEnquanto cmd = new CommandEnquanto(_exprWhile, listaCmdWhile);
								stack.peek().add(cmd);
							}
			;

		
cmdleitura	: 'leia' AP
				 	 ID {	verificaID( _input.LT(-1).getText() );
				 	 		_readID = _input.LT(-1).getText();
				 	 	}
				 	 FP
				 	 SC
				 	 {
				 	 IsiVariable var = (IsiVariable) symbolTable.get(_readID);
				 	 CommandLeitura cmd = new CommandLeitura(_readID, var);
				 	 stack.peek().add(cmd);
				 	 
				 	 }
			;
	
cmdescrita	: 'escreva' AP
						ID {	verificaID( _input.LT(-1).getText() );
								_writeID = _input.LT(-1).getText();
						}
						FP 
						SC {
								CommandEscrita cmd = new CommandEscrita(_writeID);
								stack.peek().add(cmd);	
							}
			;

cmdattrib	: ID {	verificaID( _input.LT(-1).getText() );
					_exprID = _input.LT(-1).getText();
			}
			 ATTR { _exprContent = ""; }
				 
			 
			 
			 
			 expr 
			 SC {
			 	IsiVariable var = (IsiVariable) symbolTable.get(_exprID);
			 	CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent, var);
			 	stack.peek().add(cmd);
			 
			 	}
			;
			
cmdselecao 	:  'if' AP 
					ID 				{ _exprDecision = _input.LT(-1).getText();}
					OPREL 			{ _exprDecision += _input.LT(-1).getText();}
					(ID | NUMBER) 	{ _exprDecision += _input.LT(-1).getText();}
					FP 
					ACH
					{ 	curThread = new ArrayList<AbstractCommand>();
						stack.push(curThread);
					}
					
					 
					(cmd)+ 
					FCH 
					{
						listaTrue = stack.pop();
					}
			  ('else' 
			  		ACH
			  		{ 	curThread = new ArrayList<AbstractCommand>();
						stack.push(curThread);
					}
			  		
			  		(cmd)+ 
			  		
			  		FCH
			  		{
						listaFalse = stack.pop();
						CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
						stack.peek().add(cmd);
					}
			  		
			  		)?
			;
			
expr		: termo (
				OP {_exprContent += _input.LT(-1).getText();}
				termo
				)*
			;
			
termo		: ID {	verificaID( _input.LT(-1).getText() );
					_exprContent += _input.LT(-1).getText();
		
			}	 
			| NUMBER
			{	_exprContent += _input.LT(-1).getText();
			
			
			}
			
			;

AP	: '('
	;
	
FP	: ')'
	;
	
SC	: ';'
	;
	
OP  : '+' | '-' | '*' | '/'
	;
	
ATTR	: ':='
		;
		
VIR		: ','
		;

ACH	:	'{'
	;
	
FCH : '}'
	;
		
OPREL	: '>' | '<' | '>=' | '<=' | '==' | '!='
		; 		

ID		: [a-z] ([a-z] | [A-Z] | [0-9])*
		;
		
NUMBER  : [0-9]+ ('.'[0-9]+)?
		;
		
WS : (' ' | '\n' | '\t' | '\r') -> skip;

NUMBERINT	: [0-9]+
		;

PO	: ':'
	;

			