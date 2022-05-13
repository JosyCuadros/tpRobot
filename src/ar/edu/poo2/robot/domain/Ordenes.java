package ar.edu.poo2.robot.domain;

import ar.edu.poo2.robot.exception.InvalidArchivoException;

public class Ordenes {
    private char accion;
    private int digito;

    public Ordenes(char accion, int digito) {
        if (accion == 'A' || accion == 'R'){
            this.accion = accion;
        }else{
            throw new InvalidArchivoException("Se ingreso una orden/comando invalido. Debe ser un par 'An' o 'Rn' (0 =< n <= 9). Por favor, corregir los datos de entrada.");
        }
        if (digito >= 0 && digito <= 9) {
            this.digito = digito;
        }else{
            throw new InvalidArchivoException("Se ingreso una orden/comando invalido. Debe ser un par 'An' o 'Rn' (0 =< n <= 9). Por favor, corregir los datos de entrada.");
        }
    }

    public char getAccion() {
        return accion;
    }

    public int getDigito() {
        return digito;
    }
}
