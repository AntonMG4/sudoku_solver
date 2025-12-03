package si2024.antonmaestrealu.p05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorFichero {

    String nomFichero;

    public GeneradorFichero(String nomFichero) {
        this.nomFichero = nomFichero;
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(this.nomFichero, false))) {

        } catch (IOException e) {
            System.err.println("Error abriendo el fichero: " + e.getMessage());
        }
    }

    public void escribeFich(ArrayList<Instancia> AsigSol) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(this.nomFichero, true))) {
            for(int fila=0; fila<9; fila++) {
                for(int columna=0; columna<9; columna++) {
                    for (Instancia asig : AsigSol) {
                        if(asig.getVariable().idf == fila && asig.getVariable().idc == columna) {
                            escritor.write(String.valueOf(asig.getValor()));
                            break;
                        }
                    }
                }
            }
            escritor.newLine(); // Escribir una nueva línea después de cada sudoku
        } catch (IOException e) {
            System.err.println("Error escribiendo el fichero: " + e.getMessage());
        }
    }
}