package br.edu.ufabc.compiler.datastructures;

public abstract class IsiSymbol {
	
	protected String name;
	protected boolean isUsed = false;
	protected boolean isInitialized = false;

	public abstract String generateJavaCode();

	public IsiSymbol(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed() {
		isUsed = true;
	}
	public boolean isInitialized() {
		return isInitialized;
	}

	public void setInitialized() {
		isInitialized = true;
	}

	@Override
	public String toString() {
		return "IsiVariable [name=" + getName() + ", used=" + isUsed() + ", initialized=" + isInitialized() + "]";
	}
}