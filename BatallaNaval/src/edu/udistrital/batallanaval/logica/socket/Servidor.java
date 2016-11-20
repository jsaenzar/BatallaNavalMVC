package edu.udistrital.batallanaval.logica.socket;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class Servidor implements Runnable {

    public int puerto;
    private Thread hilo;
    private boolean running;
    private DataInputStream flujoLectura;
    private DataOutputStream flujoEscritura;
    //private Socket socket;

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
            System.out.println("Servidor.iniciarServidor: " + nombreJugador + " has been connected succesfully");
            ServidorCliente servidorCliente = new ServidorCliente();
            servidorCliente.iniciarServidorCliente(socket);
            leerMensaje(socket, nombreJugador);
            
        } catch (IOException ioe) {
            System.out.println("Servidor.iniciarServidor: Error: " + ioe);
        }
    }

    public void leerMensaje(Socket socket, String nombreJugador) throws IOException {
        if (!nombreJugador.equals("orejuela") && !nombreJugador.equals("cliente")) {
            flujoLectura = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            Mensaje mensaje = new Mensaje(flujoLectura);
            mensaje.leerFlujo();
            System.out.println("Servidor.iniciarServidor:  Mensaje recibido: " + mensaje.getStrComando() + ","
                    + mensaje.getStrParam1());
        }
    }

    @Override
    public void run() {
        try (ServerSocket sfd = new ServerSocket(puerto)) {
            System.out.println("Servidor.run: ServerSocket has started succesfully");
            establecerConexion(sfd, "orejuela");
            establecerConexion(sfd, "cliente");
//            leerMensaje(socket);
        } catch (IOException ioe) {
            System.out.println("Comunicacion rechazada." + ioe);
            System.exit(1);
        }
    }
}
