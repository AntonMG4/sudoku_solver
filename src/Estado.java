package si2024.antonmaestrealu.p05;

import java.util.ArrayList;
import java.util.Collections;

import si2024.antonmaestrealu.p05.Restricciones.AllDisjoint;
import si2024.antonmaestrealu.p05.Restricciones.Debe_Ser;
import si2024.antonmaestrealu.p05.Restricciones.Restriccion;

public class Estado {
	
	public class EstadoFC {
		public int valor;
		public ArrayList<Variable> vAfectadas = new ArrayList<Variable>();
		
		public EstadoFC(int valor, ArrayList<Variable> vAfectadas) {
			this.valor = valor;
			this.vAfectadas = vAfectadas;
		}
		
	}
	
	private ArrayList<Instancia> asignaciones = new ArrayList<Instancia>();
	public ArrayList<Restriccion> restricciones;
	public int numAsignaciones;
	public int indice;
	public int valorSucesor;
	public static ArrayList<EstadoFC> listaFC = new ArrayList<EstadoFC>();	
	public static Variable[][] tablero;
	//public int Frecuencia[];
	
	// PARA EL ESTADO INICIAL
	public Estado(ArrayList<Instancia> asignaciones, ArrayList<Restriccion> restricciones, Variable[][] tablero) {
		this.asignaciones = asignaciones;
		Estado.tablero = tablero;
		this.numAsignaciones = 0;
		this.restricciones = new ArrayList<Restriccion>(restricciones);
		this.indice = 0;
		this.valorSucesor = 0;
		//Frecuencia = new int[9];
		/*for(int i=0; i<9; i++) {
			Frecuencia[i] = 0;
		}*/
		// Inicializo los alldisjoint
	    for(int i=0; i<9; i++) {
	    	ADFilas(i);
	    	ADColumnas(i);
	    	AD3x3(i);
	    }
	    Collections.sort(asignaciones);
	}

	// PARA EL RESTO DE ESTADOS
	public Estado(Estado e, ArrayList<Restriccion> restricciones) {
		this.indice = e.indice;
		this.valorSucesor = e.valorSucesor;
		this.numAsignaciones = e.numAsignaciones + 1;
		this.asignaciones = e.asignaciones;
		
		this.restricciones = e.restricciones;
		//this.Frecuencia = e.Frecuencia;
		//this.Frecuencia[valorSucesor-1]++;
		for(Restriccion res : restricciones) {
			Debe_Ser ds = (Debe_Ser) res;
			Variable v = ds.getInstancia().getVariable();
			for(Instancia i : asignaciones) {
				if(i.getVariable().equals(v)) {
					ds.setIns(i);
				}
			}
		}
		/*
		for(int i=0; i<9; i++) {
	    	ADFilas(i);
	    	ADColumnas(i);
	    	AD3x3(i);
	    }
		*/
	}
	
	public void forwardCheckingReverse() {
		EstadoFC fc = listaFC.remove(listaFC.size()-1);
		for (Variable v : fc.vAfectadas) {
			v.getDominio().add(fc.valor);
		}
	}
	
	public void forwardChecking(Instancia asig) {
		int fila = asig.getVariable().idf;
		int colum = asig.getVariable().idc;
		int val = asig.getValor();
		
		ArrayList<Variable> vAfectadas = new ArrayList<Variable>();
		
		for(int i = 0; i<9; i++) { // filas y columnas
			if(Estado.tablero[fila][i].getDominio().remove((Object)val))    
				vAfectadas.add(tablero[fila][i]);
			if(Estado.tablero[i][colum].getDominio().remove((Object)val))
				vAfectadas.add(tablero[i][colum]);
		}
		
		int iF = (fila / 3) * 3; 
	    int iC = (colum / 3) * 3; 

	    for (int fi = iF; fi < iF + 3; fi++) {
	        for (int co = iC; co < iC + 3; co++) {
	            if (Estado.tablero[fi][co].getDominio().remove((Object)val)) {
	            	vAfectadas.add(tablero[fi][co]);
	            }
	        }
	    }
		listaFC.add(new EstadoFC(val, vAfectadas));
		
	}
	
	public ArrayList<Instancia> getAsignaciones() {
		return asignaciones;
	}
	
	public void ordenarInstancias(int indice) {
		ArrayList<Instancia> subLista = new ArrayList<Instancia>(asignaciones.subList(indice, asignaciones.size()));
		Collections.sort(subLista);
		for(int i=0; i<subLista.size(); i++) {
			asignaciones.set(indice + i, subLista.get(i));
		}
	}
	
	private void ADFilas(int fila) {
		ArrayList<Instancia> ad = new ArrayList<Instancia>();
		for(int i=fila*9; i<81; i++) {
			if(fila == asignaciones.get(i).getVariable().idf) {
				ad.add(asignaciones.get(i));
			}
			if(asignaciones.get(i).getVariable().idf == fila+1)
				break;
		}
		AllDisjoint alldj = new AllDisjoint(ad);
		restricciones.add(alldj);
	}
	
	private void ADColumnas(int columna) {
		ArrayList<Instancia> ad = new ArrayList<Instancia>();
		
		for(int i=columna; i<81; i = i+9) {
			if(columna == asignaciones.get(i).getVariable().idc) {
				ad.add(asignaciones.get(i));
			}
		}
		AllDisjoint alldj = new AllDisjoint(ad);
		restricciones.add(alldj);
	}
	
	private void AD3x3(int cuadro) {
		ArrayList<Instancia> ad = new ArrayList<Instancia>();
		int inicio = 0;
		switch(cuadro) {
			case 0:
				inicio = 0;
				break;
			case 1:
				inicio = 3;
				break;
			case 2:
				inicio = 6;
				break;
			case 3:
				inicio = 27;
				break;
			case 4:
				inicio = 30;
				break;
			case 5:
				inicio = 33;
				break;
			case 6:
				inicio = 54;
				break;
			case 7:
				inicio = 57;
				break;
			case 8:
				inicio = 60;
				break;
		}
		for(int f=0; f<3; f++) {
			for(int c=0; c<3; c++) {
				ad.add(asignaciones.get(inicio));
				inicio++;
			}
			inicio = inicio+6;
		}
		AllDisjoint alldj = new AllDisjoint(ad);
		restricciones.add(alldj);
		
		
	}
	
}
