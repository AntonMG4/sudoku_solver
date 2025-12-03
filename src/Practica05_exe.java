package si2024.antonmaestrealu.p05;

import java.io.FileNotFoundException;

import java.util.ArrayList;

public class Practica05_exe {
	public static void main(String[] args) throws FileNotFoundException {
		LectorFichero lf = new LectorFichero("tableros.txt");
		GeneradorFichero gf = new GeneradorFichero("solucion.txt");
		Long timeT = (long) 0.00;
		int cont = 0;
		while(lf.haySigLinea()) {
			lf.leerLinea();
			Long timeI = System.currentTimeMillis();
			AC3 ace3 = new AC3(lf.restricciones, lf.tablero);
			Busqueda bee = new Busqueda();
		
			ace3.Algoritmo();
			
			Estado inicial = new Estado(lf.asignaciones, lf.restricciones, lf.tablero);
			ArrayList<Instancia> asignacionesFinales = bee.Algoritmo(inicial, lf.restricciones);
			
			/*for(int i=0; i<81; i++) {
				System.out.print(asignacionesFinales.get(i).getValor());
			}*/
			//System.out.println();
			gf.escribeFich(asignacionesFinales);
			Long timeF = System.currentTimeMillis();
			timeT = (long) (timeT + ((timeF-timeI)/1000.00));
			cont++;
			System.out.println(cont);
		}
		System.out.println(timeT);
		
	}
}
