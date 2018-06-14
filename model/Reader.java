/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Toolkit;
import java.net.*;
import java.io.*;

/**
 *
 * @author LolaA
 */
public class Reader extends Thread {

    ServerSocket server;
    Socket user;
    BufferedReader reader;
    Parser parser;
    PrintWriter writer;

    @Override
    public void run() {
        try {
            server = new ServerSocket(9000);
            user = server.accept();
            reader = new BufferedReader(new InputStreamReader(user.getInputStream()));
            writer = new PrintWriter(user.getOutputStream(), true);
            parser = new Parser();

            writer.println("Connection established.\n"
                    + "[Screen dimensions] Width:" + Toolkit.getDefaultToolkit().getScreenSize().width + "  Height: "
                    + Toolkit.getDefaultToolkit().getScreenSize().height);
            writer.flush();

            boolean verification;
            String reading;

            while ((reading = reader.readLine()) != null) {
                Command command = parser.parse(reading);
                if (command.equals(Command.STOP)) {
                    writer.println("Closing connection\n"
                            + "Command: " + reading);
                    break;
                } else {
                    if (command.equals(Command.ERROR)) {
                        writer.println("ERROR. Command not recognized\n"
                                + "Command: " + reading);
                    } else {
                        if (command.name().contains("MOVE")) {
                            if (command.name().equals("MOVE")) {
                                verification = Executor.absoluteMove(command, command.getMouseXPosition(), command.getMouseYPosition());
                                if (!verification) {
                                    writer.println("ERROR. Command not recognized\n"
                                            + "Command: " + reading);
                                } else {
                                    writer.println("Command executed successfully\n"
                                            + "Command: " + reading);
                                }
                            } else {
                                verification = Executor.relativeMove(command, command.getRelativeMovement());
                                if (!verification) {
                                    // The command was recognized otherwise parser would have returned ERROR
                                    //The problem occured during the execution of the command
                                    writer.println("ERROR. Command not recognized\n"
                                            + "Command: " + reading);
                                } else {
                                    writer.println("Command executed successfully\n"
                                            + "Command: " + reading);
                                }
                            }
                        } else {
                            if (command.name().contains("SCROLL")) {
                                verification = Executor.relativeMove(command, command.getRelativeMovement());
                                if (!verification) {
                                    // The command was recognized otherwise parser would have returned ERROR
                                    //The problem occured during the execution of the command
                                    writer.println("ERROR. Command not recognized\n"
                                            + "Command: " + reading);
                                } else {
                                    writer.println("Command executed successfully\n"
                                            + "Command: " + reading);
                                }
                            } else {
                                verification = Executor.execute(command);
                                if (!verification) {
                                    // The command was recognized otherwise parser would have returned ERROR
                                    //The problem occured during the execution of the command
                                    writer.println("ERROR. Command not recognized\n"
                                            + "Command: " + reading);
                                } else {
                                    writer.println("Command executed successfully\n"
                                            + "Command: " + reading);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            writer.println("ERROR. There was a problem with the connection");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                user.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
