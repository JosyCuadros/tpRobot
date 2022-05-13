package ar.edu.poo2.robot.domain;

import ar.edu.poo2.robot.exception.InvalidArchivoException;

public class Cuadricula {
    private int maximaColumna;
    private int maximaFila;

    public Cuadricula(int maximaColumna, int maximaFila) {
        if (maximaColumna > 0 && maximaColumna <= 100) {
            this.maximaColumna = maximaColumna;
        } else {
            throw new InvalidArchivoException("Maximo de Columnas Invalida. Debe ser mayor a cero y menor igual a 100. Por favor, corregir los datos de entrada.");
        }
        if (maximaFila > 0 && maximaFila <= 100) {
            this.maximaFila = maximaFila;
        } else {
            throw new InvalidArchivoException("Maximo de Filas Invalida. Debe ser mayor a cero y menor igual a 100. Por favor, corregir los datos de entrada.");
        }
    }

    public int getMaximaColumna() {
        return maximaColumna;
    }

    public int getMaximaFila() {
        return maximaFila;
    }
}
