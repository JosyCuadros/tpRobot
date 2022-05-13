package ar.edu.poo2.robot.domain;

import ar.edu.poo2.robot.exception.InvalidArchivoException;

import java.io.*;

public class ProcesarRobot {
    private Robot robot;
    private Ordenes[] ordenes ;
    private String datosSalida;

    public ProcesarRobot(String nomArch) throws FileNotFoundException{
        setRobot(nomArch);
        datosSalida = robot.devolverPosFinal(ordenes);
    }

    private void setRobot(String nomArch)throws FileNotFoundException{
        FileReader fr = null;
        BufferedReader br;

        try {
            fr = new FileReader (nomArch);
            br = new BufferedReader(fr);
            String linea;
            String [] columnas;
            int contLinea = 0;

            linea = br.readLine();

            if (linea == null){
                throw new InvalidArchivoException("El archivo se encuentra vacio. Por favor, carguele datos.");
            }

            while(linea !=null) {
                if (contLinea == 0) {
                    columnas = linea.split(" ");

                    try {
                        int columna = Integer.parseInt(columnas[0]);
                        int fila = Integer.parseInt(columnas[1]);
                        int columnaMaxima = Integer.parseInt(columnas[3]);
                        int filaMaxima = Integer.parseInt(columnas[4]);
                        char sentido = columnas[2].charAt(0);

                        robot = new Robot(columna, fila, sentido, new Cuadricula(columnaMaxima, filaMaxima) );

                    }catch (NumberFormatException e) {
                        throw new InvalidArchivoException("Se ingreso un dato no numerico. Por favor, corregir los datos de entrada.");
                    }catch (ArrayIndexOutOfBoundsException e1){
                        throw new InvalidArchivoException("Falto ingresar algunos datos. Por favor, corregir los datos de entrada completos.");
                    }
                }else if(contLinea == 1){
                     if (linea.length() <= 250){
                         cargarOrdenes(linea);
                     }else{
                         throw new InvalidArchivoException("Se ingreso mas de 125 ordenes. Por favor, corregir los datos de entrada.");
                     }
                }else {
                    throw new InvalidArchivoException( "Se ingreso mas de dos lineas/registros en el archivo. Por favor, corregir el mismo.");
                }
                contLinea++;

                linea = br.readLine();
            }
        }catch(IOException e2){
            e2.printStackTrace();
        }finally{
            try{
                if( fr != null ){
                    fr.close();
                }
            }catch (Exception e3){
                e3.printStackTrace();
            }
        }
    }

    private void cargarOrdenes(String linea) {
        if (linea.length() % 2 == 0) {
            int tamanioOrdenes = linea.length() / 2;
            ordenes = new Ordenes[tamanioOrdenes];
            int indOrdenes = 0;

            for (int i = 0; i < linea.length(); i += 2) {
                char accion = linea.charAt(i);

                try {
                    int digito = Integer.parseInt(linea.substring(i + 1, i + 2));
                    ordenes[indOrdenes] = new Ordenes(accion, digito);
                    indOrdenes ++;
                }catch (NumberFormatException e) {
                    throw new InvalidArchivoException("Se ingreso una orden/comando invalido. Debe ser par 'An' o 'Rn' (0 =< n <= 9). Por favor, corregir los datos de entrada.");
                }
            }
        }else{
            throw new InvalidArchivoException("Se ingreso una orden/comando invalido. Debe ser par 'An' o 'Rn' (0 =< n <= 9). Por favor, corregir los datos de entrada.");
        }
    }


    public void GenerarRobotOut() throws IOException {
        FileWriter archivo = new FileWriter("C:/Users/pame3/IdeaProjects/tpRobot/src/resources/robot.out");
        PrintWriter robotOut = new PrintWriter(archivo);

        robotOut.println(datosSalida);

        robotOut.close();
    }
}
