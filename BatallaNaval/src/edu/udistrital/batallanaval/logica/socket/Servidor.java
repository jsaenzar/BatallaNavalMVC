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
//    private Socket socket;

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

        try{
            Socket socket = socketServidor.accept();
            System.out.println("Servidor.iniciarServidor: " + nombreCliente + " has been connected succesfully");
          
            ServidorCliente servidorCliente = new ServidorCliente();
            servidorCliente.iniciarServidorCliente(socket);
            
            System.out.println("socket.getInetAddress(): " + socket.getInetAddress().toString());

            if (!socket.getInetAddress().toString().equals("/127.0.0.1")) {
//                System.out.println("ENTRO AL IF: " + socket.getInetAddress());
                flujoLectura = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String mensaje = flujoLectura.readUTF();
                System.out.println("Servidor.iniciarServidor: Mensaje recibido: " + mensaje);
            } else {
                System.out.println("ENTRO AL ELSE: " + socket.getInetAddress());

            }
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
//        return socket;
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
            
//            leerMensaje(socket);
        } catch (IOException ioe) {
            System.out.println("Comunicacion rechazada." + ioe);
            System.exit(1);
        }
    }
    
    
}
