package br.edu.ufabc.compiler.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class IsiSymbolTable {
	
	private HashMap<String, IsiSymbol> map;
	
	public IsiSymbolTable() {
		map = new HashMap<String, IsiSymbol>();
	}
	
	public void add(IsiSymbol symbol) {
		map.put(symbol.getName(), symbol);
	}
	
	public boolean exists(String symbolName) {
		return map.get(symbolName) != null;
	}
	
	public IsiSymbol get(String symbolName) {
		IsiSymbol isiSymbol = map.get(symbolName);
		if (isiSymbol instanceof IsiVariable)
			isiSymbol.setUsed();
		return isiSymbol;
	}

	public List<IsiSymbol> getNotUsedSymbols() {
		ArrayList<IsiSymbol> symbols = this.getAll();
		return symbols.stream()
				.filter(symbol -> !symbol.isUsed)
				.collect(Collectors.toList());
	}
	
	public int getTypeBy(String id) {
		IsiVariable variable = (IsiVariable) this.get(id);
		return variable.getType();
	}

	public boolean checkInitialized(String id) {
		IsiSymbol  symbol = this.get(id);
		return symbol.isInitialized();
	}

	public void setInitializedBy(String id) {
		IsiVariable variable = (IsiVariable) this.get(id);
		variable.setInitialized();
	}

	public ArrayList<IsiSymbol> getAll(){
		ArrayList<IsiSymbol> list = new ArrayList<>();
		for (IsiSymbol symbol : map.values()) {
			list.add(symbol);
		}
		return list;
	}
}