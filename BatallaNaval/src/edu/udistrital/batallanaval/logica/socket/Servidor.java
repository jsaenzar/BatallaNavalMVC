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
//    private Socket socketCliente;

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

    public void establecerConexion(ServerSocket socketServidor, String tipoConexion, String nombreCliente) {
        System.out.println("Servidor.iniciarServidor: Waiting for player " + nombreCliente + " connection");

        try {
            Socket socketCliente = socketServidor.accept();
//            System.out.println("Servidor.iniciarServidor: " + nombreCliente + " has been connected succesfully");
            ServidorCliente servidorCliente = new ServidorCliente(this);
            servidorCliente.iniciarServidorCliente(socketCliente);
            System.out.println("socket.getInetAddress(): " + socketCliente.getInetAddress().toString());

//            if (!socketCliente.getInetAddress().toString().equals("/127.0.0.1")) {
////                System.out.println("ENTRO AL IF: " + socketCliente.getInetAddress());
//                flujoLectura = new DataInputStream(new BufferedInputStream(socketCliente.getInputStream()));
//                String mensaje = flujoLectura.readUTF();
//                System.out.println("Servidor.iniciarServidor: Mensaje recibido: " + mensaje);
//            } else {
//                System.out.println("ENTRO AL ELSE: " + socketCliente.getInetAddress());
//
//            }

        } catch (IOException ioe) {
            System.out.println("Servidor.iniciarServidor: Error: " + ioe);
        }
    }

    public void leerMensaje(Socket socket, String nombreCliente) throws IOException {
//        if (!nombreCliente.equals("orejuela") && !nombreCliente.equals("cliente")) {
        flujoLectura = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        String mensaje = flujoLectura.readUTF();
//        flujoLectura.close();
        System.out.println("Servidor.iniciarServidor:  Mensaje recibido: " + mensaje);

//        }            
//            Mensaje mensaje = new Mensaje(flujoLectura);
//            mensaje.leerFlujo();
//            System.out.println("Servidor.iniciarServidor:  Mensaje recibido: " + mensaje.getStrComando() + ","
//                    + mensaje.getStrParam1());
//        }
    }

//    public Socket getSocket() {
//        return socketCliente;
//    }
    public DataInputStream getFlujoLectura() {
        return flujoLectura;
    }

    @Override
    public void run() {
        try (ServerSocket sfd = new ServerSocket(puerto)) {
            System.out.println("Servidor.run: ServerSocket has started succesfully");
            establecerConexion(sfd, "home", "orejuela");
            establecerConexion(sfd, "guest", "orejuela");
//            leerMensaje(socketCliente);
        } catch (IOException ioe) {
            System.out.println("Comunicacion rechazada." + ioe);
            System.exit(1);
        }
    }

}
