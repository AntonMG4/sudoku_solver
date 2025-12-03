package si2024.antonmaestrealu.p05.Restricciones;

import si2024.antonmaestrealu.p05.Instancia;

public class Debe_Ser implements Restriccion {
	
	Instancia instancia1;
	Integer valor;
	
	public Debe_Ser(Instancia i1, int valor) {
		this.instancia1 = i1;
		this.valor = valor;
	}
	
	public Debe_Ser(Restriccion r) {
		Debe_Ser ds = (Debe_Ser) r;
		this.instancia1 = ds.instancia1;
		this.valor = ds.valor;
	}
	
	public Instancia getInstancia() {
		return instancia1;
	}
	
	public void setIns(Instancia i) {
		instancia1 = i;
	}
	
	public Integer getValor() {
		return valor;
	}

	@Override
	public boolean seCumple() {
		if(instancia1.getValor() == valor || instancia1.getValor() == -1)
			return true;
		else
			return false;
	}

	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return 1;
	} 

}
