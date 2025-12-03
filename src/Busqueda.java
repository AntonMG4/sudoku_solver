package si2024.antonmaestrealu.p05;

import java.util.ArrayList;

import si2024.antonmaestrealu.p05.Restricciones.*;

public class Busqueda {
	private ArrayList<Estado> Abierta = new ArrayList<Estado>();
	private ArrayList<Restriccion> restricciones;
	private int indAnterior = -1;
	
	public ArrayList<Instancia> Algoritmo(Estado inicial, ArrayList<Restriccion> resDS) {
		this.restricciones = new ArrayList<Restriccion>();
		for(Restriccion r : resDS) {
			this.restricciones.add(new Debe_Ser(r));
		}
		
		Abierta.add(inicial);
		Estado actual = inicial;
		
		while(!Abierta.isEmpty()) {
			
			actual = Abierta.remove(Abierta.size()-1);
			
			if(actual.valorSucesor != 0) {
				if(indAnterior >= actual.indice) { // Backtracking
					int max = actual.indice + (indAnterior - actual.indice);
					actual.forwardCheckingReverse();
					for(int i = actual.indice; i<max; i++) {
						actual.getAsignaciones().get(i).setValor(-1);
						actual.forwardCheckingReverse();
					}
				}
				indAnterior = actual.indice;
				actual.getAsignaciones().get(actual.indice-1).setValor(actual.valorSucesor);
				actual.forwardChecking(actual.getAsignaciones().get(actual.indice-1));
				actual.ordenarInstancias(actual.indice);
				
			}
			if(esMeta(actual))
				return actual.getAsignaciones();
			else {
				ArrayList<Estado> sucesores = getSucesores(actual);
			    Abierta.addAll(sucesores);
				
			}
		}
		return null;
	}
	
	private boolean esMeta(Estado act) {
		if(act.numAsignaciones == 81)
			return true;
		else
			return false;
	}
	
	private ArrayList<Estado> getSucesores(Estado act) {
		ArrayList<Estado> sucesores = new ArrayList<Estado>();
		int ind = act.indice;
		Instancia ins = act.getAsignaciones().get(ind);
		for(int i : ins.getVariable().getDominio()) {
			
				act.getAsignaciones().get(ind).setValor(i);
				if(seCumple(act)) {
					Estado est = new Estado(act, restricciones);
					est.valorSucesor = i;
					est.indice = ind + 1;
					sucesores.add(est);
				} 
					
		}
		act.getAsignaciones().get(ind).setValor(-1);
		return sucesores;
	}
	
	private boolean seCumple(Estado e) {
		for(Restriccion r : e.restricciones) {
			if(!r.seCumple())
				return false;
		}
		return true;
	}
}
