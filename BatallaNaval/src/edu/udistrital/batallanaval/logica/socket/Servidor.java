package edu.udistrital.batallanaval.logica.socket;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class Servidor implements Runnable {

//    public ServerSocket sfd;
    public int puerto;
    private Thread hilo;
    private boolean running;

    public static Vector usuarios = new Vector();

    public Servidor() {
        running = false;
    }

    public void iniciarServidor(int puerto) {
        this.puerto = puerto;
        running = true;
        hilo = new Thread(this);
        hilo.start();
        System.out.println("Servidor.iniciarServidor: Starting server in port: " + puerto);
    }

    public void establecerConexion(ServerSocket socketServidor, String nombreJugador) {
        System.out.println("Servidor.iniciarServidor: Waiting for player " + nombreJugador + " connection");

        try (Socket socket = socketServidor.accept()) {
            System.out.println("Servidor.iniciarServidor:  Player " + nombreJugador + " has been conected succesfully");
            ServidorCliente servidorCliente = new ServidorCliente();
            servidorCliente.iniciarServidorCliente(socket);
        } catch (IOException ioe) {
            System.out.println("Servidor.iniciarServidor: Error: " + ioe);
        }
    }

    @Override
    public void run() {
        try (ServerSocket sfd = new ServerSocket(puerto)) {
            System.out.println("Servidor.run: ServerSocket has started succesfully");
            establecerConexion(sfd, "Home");
            establecerConexion(sfd, "Guest");
        } catch (IOException ioe) {
            System.out.println("Comunicacion rechazada." + ioe);
            System.exit(1);
        }
    }
}
