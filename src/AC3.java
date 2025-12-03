package si2024.antonmaestrealu.p05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import si2024.antonmaestrealu.p05.Restricciones.Debe_Ser;
import si2024.antonmaestrealu.p05.Restricciones.Restriccion;

public class AC3 {

	//v -> v1,v2,v3
	
	private ArrayList<Restriccion> restricciones;
	private Variable[][] tablero;
	private Set<Arco> arcos;
	private Set<Arco> arcosPosibles;
	
	public AC3(ArrayList<Restriccion> restricciones, Variable[][] tablero) {
		this.restricciones = restricciones;
		this.tablero = tablero;
		
		
		
		setArcos();
		
		
	}
	
	public void Algoritmo() {
		
		// REDUCIR LOS DOMINIOS DE LAS RESTRICCIONES 'DEBE SER'
		for(Restriccion r : restricciones) {
			Debe_Ser ds = (Debe_Ser) r;
			for(int i=0; i<ds.getInstancia().getVariable().getDominio().size(); i++) {
				Integer d = ds.getInstancia().getVariable().getDominio().get(i);
				if(d != ds.getValor()) {
					ds.getInstancia().getVariable().getDominio().remove(i);
					i--;
				}
					
			}
		}
		Iterator<Arco> it = arcos.iterator();
		while(it.hasNext()) {
			Arco a = it.next();
			arcos.remove(a);
			if(!a.esConsistente()) {
				anadirArcosNoDistinguidos(a.distinguida);
				
			}
			it = arcos.iterator();
			
		}
	}
	
	private void anadirArcosNoDistinguidos(Variable vD) {
		for(Arco arc : arcosPosibles) {
			if(arc.noDistinguida.equals(vD)) {
				arcos.add(arc);
			}
		}
	}
	
	public void setArcos() {
		arcos = new HashSet<Arco>();
		for(int f=0; f<9; f++) {
			for(int c=0; c<9; c++) {
				arcos3x3(tablero[f][c]);
				arcosFila(tablero[f][c]);
				arcosColumna(tablero[f][c]);
			}
		}
		arcosPosibles = new HashSet<Arco>(arcos);
	}

	public void arcos3x3(Variable v) {
	    int Fila1 = (v.idf / 3) * 3; 
	    int Columna1 = (v.idc / 3) * 3; 

	    for (int i = Fila1; i < Fila1 + 3; i++) {
	        for (int j = Columna1; j < Columna1 + 3; j++) {
	            if (i != v.idf || j != v.idc) {
	                arcos.add(new Arco(v, tablero[i][j]));
	            }
	        }
	    }
	}
	
	public void arcosFila(Variable v) {
		for(int c=0; c<9; c++) {
			if(c != v.idc) {
				arcos.add(new Arco(v, tablero[v.idf][c]));
			}
		}
	}
	
	public void arcosColumna(Variable v) {
		for(int f=0; f<9; f++) {
			if(f != v.idf) {
				arcos.add(new Arco(v, tablero[f][v.idc]));
			}
		}
	}
}
