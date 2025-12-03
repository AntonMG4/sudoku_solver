package si2024.antonmaestrealu.p05;

public class Arco {
	public Variable distinguida;
	public Variable noDistinguida;
	
	public Arco(Variable v1, Variable v2) {
		this.distinguida = v1;
		this.noDistinguida = v2;
	}
	
	public boolean esConsistente() {
		if(noDistinguida.getDominio().size() == 1) {
			Integer valor = noDistinguida.getDominio().get(0);
			for(Integer v : distinguida.getDominio()) {
				if(valor == v) {
					distinguida.getDominio().remove((Object)v);
					return false;
				}
					
			}
			return true;
		} else
			return true;
	}

	public Variable getDistinguida() {
		return distinguida;
	}

	public Variable getNoDistinguida() {
		return noDistinguida;
	}
	
	
}
