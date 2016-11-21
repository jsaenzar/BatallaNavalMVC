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
    private ServidorCliente home;
    private ServidorCliente guest;
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

//   public void leerMensaje(Socket socket, String nombreCliente) throws IOException {
////        if (!nombreCliente.equals("orejuela") && !nombreCliente.equals("cliente")) {
//        flujoLectura = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
//        String mensaje = flujoLectura.readUTF();
////        flujoLectura.close();
//        System.out.println("Servidor.iniciarServidor:  Mensaje recibido: " + mensaje);
//
////        }            
////            Mensaje mensaje = new Mensaje(flujoLectura);
////            mensaje.leerFlujo();
////            System.out.println("Servidor.iniciarServidor:  Mensaje recibido: " + mensaje.getStrComando() + ","
////                    + mensaje.getStrParam1());
////        }
//    }
//
//    public Socket getSocket() {
//        return socketCliente;
//    }
//    public DataInputStream getFlujoLectura() {
//        return flujoLectura;
//    }
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor.run: ServerSocket has started succesfully");
            home = conexionJugador(serverSocket);
            guest = conexionJugador(serverSocket);
        } catch (IOException ioe) {
            System.out.println("Comunicacion rechazada." + ioe);
            System.exit(1);
        }
    }

    public ServidorCliente conexionJugador(ServerSocket socketServidor) {
        System.out.println("Servidor.iniciarServidor: Waiting for player connection");

        try {
            Socket socketCliente = socketServidor.accept();
            System.out.println("Servidor.iniciarServidor: " + socketCliente.getInetAddress().toString() + " has been connected succesfully");
            ServidorCliente servidorCliente = new ServidorCliente(this);
            servidorCliente.iniciarServidorCliente(socketCliente);
            return servidorCliente;
//            System.out.println(socketCliente.getInetAddress().toString());
        } catch (IOException ioe) {
            System.out.println("Servidor.iniciarServidor: Error: " + ioe);
            throw new AssertionError("Player Connection Error", ioe);
        }
    }
}
