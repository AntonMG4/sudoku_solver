package si2024.antonmaestrealu.p05;


public class Instancia implements Comparable<Instancia>{
	private Variable variable;
	private int valor;
	
	public Instancia(Variable v) {
		this.variable = v;
		valor = -1;
	}
	
	public Instancia(Instancia i) {
		this.variable = i.variable;
		this.valor = i.valor;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	public Variable getVariable() {
		return variable;
	}

	@Override
	public int compareTo(Instancia o) {
		return Integer.compare(this.variable.dominio.size(), o.variable.dominio.size());
	}
}
