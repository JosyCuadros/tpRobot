package ar.edu.poo2.robot.main;

import ar.edu.poo2.robot.domain.ProcesarRobot;

import java.io.IOException;

public class GenerarArchSalida {

    public static void main (String [] arg) throws IOException {

        ProcesarRobot procesarRobot = new ProcesarRobot("C:/Users/pame3/IdeaProjects/tpRobot/src/resources/robot.in");
        procesarRobot.GenerarRobotOut();

    }
}