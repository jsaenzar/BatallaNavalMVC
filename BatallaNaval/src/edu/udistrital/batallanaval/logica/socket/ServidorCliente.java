package edu.udistrital.batallanaval.logica.socket;

import edu.udistrital.batallanaval.enums.Comando;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class ServidorCliente implements Runnable {

    private boolean running;
    private Socket socket;
    private Thread hilo;
    private DataInputStream flujoLectura;
    private DataOutputStream flujoEscritura;
    private String nombreJugador;
    private Comando comando;

    public ServidorCliente() {
        running = false;
    }

    public void iniciarServidorCliente(Socket socket) {
        System.out.println("ServidorCliente.iniciarServidorCliente: Starting client Thread in server: "
                + socket.getInetAddress());
        this.socket = socket;
        running = true;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream()) {
            OutputStream os = socket.getOutputStream();
            System.out.print("ServidorCliente.run: Client Thread has started succesfully");
        } catch (Exception e) {
            //System.out.print("ServidorCliente.run: Client Thread hasn't started succesfully");
        }
    }
}