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
    private Socket clienteSocket;
    private Thread hilo;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String nombreJugador;
    private Comando comando;
    private Servidor servidor;

    public ServidorCliente(Servidor servidor) {
        running = false;
        this.servidor = servidor;
    }

    public void iniciarServidorCliente(Socket clienteSocket) {
        System.out.println("ServidorCliente.iniciarServidorCliente: Starting client Thread in server: "
                + clienteSocket.getInetAddress());
        this.clienteSocket = clienteSocket;
        running = true;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(clienteSocket.getInputStream())) {
            DataOutputStream dataOutputStream = new DataOutputStream(clienteSocket.getOutputStream());
            referenceOutputStream(dataOutputStream);
            while (running) {
                String msjRecibido = dataInputStream.readUTF();
                System.out.println("Escupalo: " + msjRecibido);
            }
        } catch (Exception e) {

        }

//        try (InputStream is = clienteSocket.getInputStream()) {
//            OutputStream os = clienteSocket.getOutputStream();
//            System.out.println("ServidorCliente.run: Client Thread has started succesfully");
//        } catch (Exception e) {
//            //System.out.print("ServidorCliente.run: Client Thread hasn't started succesfully");
//        }
    }

    private void referenceOutputStream(DataOutputStream dataOutputStream) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.dataOutputStream = dataOutputStream;
    }
}
