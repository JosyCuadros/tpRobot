package ar.edu.poo2.robot.tests;

import ar.edu.poo2.robot.domain.Cuadricula;
import ar.edu.poo2.robot.domain.Ordenes;
import ar.edu.poo2.robot.domain.Robot;
import ar.edu.poo2.robot.exception.InvalidArchivoException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProcesarRobotTest {

    // ROBOT
    @Test
    public void crearRobotConColumnaEnCeroTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Robot(0, 1, 'N', new Cuadricula(5, 5));});

        String expectedMessage = "La columna o fila no pueden ser cero. Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearRobotConFilaEnCeroTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Robot(2, 0, 'N', new Cuadricula(5, 5));});

        String expectedMessage = "La columna o fila no pueden ser cero. Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearRobotConColumnaMayorAColumnaMaximaTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Robot(8, 2, 'N', new Cuadricula(5, 5));});

        String expectedMessage = "La columna no puede ser mayor al maximo de columna. Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearRobotConFilaMayorAFilaMaximaTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Robot(2, 9, 'N', new Cuadricula(5, 5));});

        String expectedMessage = "La fila no puede ser mayor al maximo de columna. Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearRobotConSentidoInvalidoTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Robot(2, 3, 'L', new Cuadricula(5, 5));});

        String expectedMessage = "Informo un Sentido invalido. Solo puede ser 'N', 'S', 'E' u 'O'. Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void devolverPosFinalTest(){
         Robot robot = new Robot(2, 2, 'E', new Cuadricula(10, 10));
         Ordenes [] ordenes = {new Ordenes('A', 5), new Ordenes('R', 3), new Ordenes('A', 2)};

         String actualPosFinal = robot.devolverPosFinal(ordenes);
         String expectedPosFinal = "7 4";

         assertEquals(expectedPosFinal, actualPosFinal);
    }

    @Test
    public void avanzarCuandoEstaApuntandoAlNorteTest(){
        Robot robot = new Robot(1, 1, 'N', new Cuadricula(5, 5));
        Ordenes [] ordenes = {new Ordenes('A', 2)};

        String actualPosFinal = robot.devolverPosFinal(ordenes);
        String expectedPosFinal = "1 3";

        assertEquals(expectedPosFinal, actualPosFinal);
    }

    @Test
    public void avanzarCuandoEstaApuntandoAlSurTest(){
        Robot robot = new Robot(1, 5, 'S', new Cuadricula(5, 10));
        Ordenes [] ordenes = {new Ordenes('A', 3)};

        String actualPosFinal = robot.devolverPosFinal(ordenes);
        String expectedPosFinal = "1 2";

        assertEquals(expectedPosFinal, actualPosFinal);
    }

    @Test
    public void avanzarCuandoEstaApuntandoAlEsteTest(){
        Robot robot = new Robot(1, 1, 'E', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('A', 4)};

        String actualPosFinal = robot.devolverPosFinal(ordenes);
        String expectedPosFinal = "5 1";

        assertEquals(expectedPosFinal, actualPosFinal);
    }

    @Test
    public void avanzarCuandoEstaApuntandoAlOesteTest(){
        Robot robot = new Robot(7, 1, 'O', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('A', 6)};

        String actualPosFinal = robot.devolverPosFinal(ordenes);
        String expectedPosFinal = "1 1";

        assertEquals(expectedPosFinal, actualPosFinal);
    }

    @Test
    public void rotarCuandoGiro90GradosYestaApuntandoAlNorteTest(){
        Robot robot = new Robot(1, 1, 'N', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 1)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'E';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro90GradosYestaApuntandoAlSurTest(){
        Robot robot = new Robot(1, 1, 'S', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 1)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'O';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro90GradosYestaApuntandoAlEsteTest(){
        Robot robot = new Robot(1, 1, 'E', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 1)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'S';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro90GradosYestaApuntandoAlOesteTest(){
        Robot robot = new Robot(1, 1, 'O', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 1)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'N';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro180GradosYestaApuntandoAlNorteTest(){
        Robot robot = new Robot(1, 1, 'N', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 2)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'S';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro180GradosYestaApuntandoAlSurTest(){
        Robot robot = new Robot(1, 1, 'S', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 2)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'N';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro180GradosYestaApuntandoAlEsteTest(){
        Robot robot = new Robot(1, 1, 'E', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 2)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'O';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro180GradosYestaApuntandoAlOesteTest(){
        Robot robot = new Robot(1, 1, 'O', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 2)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'E';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro270GradosYestaApuntandoAlNorteTest(){
        Robot robot = new Robot(1, 1, 'N', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 3)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'O';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro270GradosYestaApuntandoAlSurTest(){
        Robot robot = new Robot(1, 1, 'S', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 3)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'E';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro270GradosYestaApuntandoAlEsteTest(){
        Robot robot = new Robot(1, 1, 'E', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 3)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'N';

        assertEquals(sentidoActual, sentidoExpected);
    }

    @Test
    public void rotarCuandoGiro270GradosYestaApuntandoAlOesteTest(){
        Robot robot = new Robot(1, 1, 'O', new Cuadricula(10, 10));
        Ordenes [] ordenes = {new Ordenes('R', 3)};

        robot.devolverPosFinal(ordenes);

        char sentidoActual = robot.getSentido();
        char sentidoExpected = 'S';

        assertEquals(sentidoActual, sentidoExpected);
    }

    // Ordenes
    @Test
    public void crearOrdenesConAccionDistintoaAoRTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Ordenes('S', 1);});

        String expectedMessage = "Se ingreso una orden/comando invalido. Debe ser un par 'An' o 'Rn' (0 =< n <= 9). Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearOrdenesConAccionNumericoTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Ordenes('5', 1);});

        String expectedMessage = "Se ingreso una orden/comando invalido. Debe ser un par 'An' o 'Rn' (0 =< n <= 9). Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearOrdenesConDigitoMayorA9Test(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Ordenes('A', 10);});

        String expectedMessage = "Se ingreso una orden/comando invalido. Debe ser un par 'An' o 'Rn' (0 =< n <= 9). Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Cuadricula
    @Test
    public void crearCuadriculaConColumnaMaximaEnCeroTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {new Cuadricula(0, 5);});

        String expectedMessage = "Maximo de Columnas Invalida. Debe ser mayor a cero y menor igual a 100. Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearCuadriculaConColumnaMaximaMayorA100Test(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {
            new Robot(1, 1, 'N', new Cuadricula(200, 5));});

        String expectedMessage = "Maximo de Columnas Invalida. Debe ser mayor a cero y menor igual a 100. Por favor, corregir los datos de entrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearCuadriculaConFilaMaximaEnCeroTest(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {new Cuadricula(5, 0);});

        String expectedMessage = "Maximo de Filas Invalida. Debe ser mayor a cero y menor igual a 100. Por favor, corregir los datos de entrada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void crearCuadriculaConFilaMaximaMayorA100Test(){
        Exception exception = assertThrows(InvalidArchivoException.class, () -> {new Cuadricula(5, 200);});

        String expectedMessage = "Maximo de Filas Invalida. Debe ser mayor a cero y menor igual a 100. Por favor, corregir los datos de entrada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
