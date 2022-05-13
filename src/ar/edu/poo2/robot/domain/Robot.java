package ar.edu.poo2.robot.domain;

import ar.edu.poo2.robot.exception.InvalidArchivoException;

public class Robot {
    private int xColumna;
    private int yFila;
    private char sentido;
    private Cuadricula cuadricula;

    public Robot(int xColumna, int yFila, char sentido, Cuadricula cuadricula) {
        if (xColumna == 0 || yFila == 0){
            throw new InvalidArchivoException("La columna o fila no pueden ser cero. Por favor, corregir los datos de entrada.");
        }else{
            if (xColumna > cuadricula.getMaximaColumna()){
                 throw new InvalidArchivoException("La columna no puede ser mayor al maximo de columna. Por favor, corregir los datos de entrada.");
            }
            if (yFila > cuadricula.getMaximaFila()){
                throw new InvalidArchivoException("La fila no puede ser mayor al maximo de columna. Por favor, corregir los datos de entrada.");
            }
            this.xColumna = xColumna;
            this.yFila = yFila;
        }
        if (sentido == 'N' || sentido == 'S' || sentido == 'E' || sentido == 'O') {
            this.sentido = sentido;
        }else{
            throw new InvalidArchivoException("Informo un Sentido invalido. Solo puede ser 'N', 'S', 'E' u 'O'. Por favor, corregir los datos de entrada.");
        }
        this.cuadricula = cuadricula;
    }

    public String devolverPosFinal(Ordenes[] ordenes) {
        for (Ordenes orden: ordenes){
            if(orden.getAccion() == 'A'){
                avanzar(orden.getDigito());
            }
            if (orden.getAccion() == 'R'){
                rotar(orden.getDigito());
            }
        }

        String posFinal = xColumna + " " + yFila;

        return posFinal;
    }

    public char getSentido() {
        return sentido;
    }

    private void rotar(int giro) {
        if ((giro == 1) || (giro == 5) || (giro == 9)){
            switch(sentido){
                case 'N':
                    sentido = 'E';
                    break;
                case 'S':
                    sentido = 'O';
                    break;
                case 'E':
                    sentido = 'S';
                    break;
                case 'O':
                    sentido = 'N';
                    break;
            }
        }

        if ((giro == 2) || (giro == 6)){
            switch(sentido){
                case 'N':
                    sentido = 'S';
                    break;
                case 'S':
                    sentido = 'N';
                    break;
                case 'E':
                    sentido = 'O';
                    break;
                case 'O':
                    sentido = 'E';
                    break;
            }
        }

        if ((giro == 3) || (giro == 7)){
            switch(sentido) {
                case 'N':
                    sentido = 'O';
                    break;
                case 'S':
                    sentido = 'E';
                    break;
                case 'E':
                    sentido = 'N';
                    break;
                case 'O':
                    sentido = 'S';
                    break;
            }
        }
    }

    private void avanzar(int pasos) {
        if (sentido == 'N'){
            for (; yFila < cuadricula.getMaximaFila(); pasos--){
                if (pasos == 0){
                    return;
                }
                
                yFila++;
            }
        }
        
        if (sentido == 'S'){
            for (; yFila > 1; pasos--){
                if (pasos == 0){
                    return;
                }
                yFila--;
            }
        }
        
        if (sentido == 'E'){
            for (; xColumna < cuadricula.getMaximaColumna(); pasos--){
                if (pasos == 0){
                    return;
                }
                xColumna ++;
            }
        }
        
        if (sentido == 'O'){
            for (; xColumna > 1; pasos--){
                if (pasos == 0){
                    return;
                }
                xColumna --;
            }
        }
    }
}
