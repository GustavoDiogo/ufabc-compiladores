grammar GrammarExpression;

@header{
	import java.util.ArrayList;
}

@members{
	private ArrayList<String> listaDeTokens = new ArrayList<String>();
	
	public void exibirTodosTokens(){
		for(String s: listaDeTokens){
			System.out.println("Token lido:" + s);
		}
	}
}

programa : 'programa' expr 'fimprog'
         ;
         
expr	 : termo exprl
         ;
         
exprl	 : (OP termo)* 
		 ;        
         
termo    : ID   {listaDeTokens.add(_input.LT(-1).getText());} 
         | NUM  
         ;
         
OP		 : '+' | '-' | '*' | '/'
         ;
         
ID		 : [a-z] ([a-z] | [A-Z] | [0-9])*
		 ;
		 
NUM		 : [0-9]+ ('.' [0-9]+)?
		 ;
		 
WS       : (' ' | '\t' | '\n' | '\r') -> skip
         ;