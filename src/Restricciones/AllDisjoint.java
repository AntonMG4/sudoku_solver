package si2024.antonmaestrealu.p05.Restricciones;

import java.util.ArrayList;

import si2024.antonmaestrealu.p05.Instancia;

public class AllDisjoint implements Restriccion {
	

	private ArrayList<Instancia> asignaciones;
	
	public AllDisjoint(ArrayList<Instancia> asig) {
		this.asignaciones = asig;
	}
	
	@Override
	public boolean seCumple() {
		for(Instancia ins1 : asignaciones) {
			if(ins1.getValor() != -1) {
				for(Instancia ins2 : asignaciones) {
					if(ins1.getValor() == ins2.getValor() && !ins1.equals(ins2))
						return false;
				}
			}
			
		}
		return true;
	}

	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return 2;
	}

}
