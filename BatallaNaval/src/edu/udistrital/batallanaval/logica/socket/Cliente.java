package edu.udistrital.batallanaval.logica.socket;

import edu.udistrital.batallanaval.enums.Comando;
import java.io.*;
import java.net.*;

public class Cliente implements Runnable {

    private boolean running;
    private String hostName;
    private int puerto;
    private Thread hilo;
    private String mensajeRecibido;
    private String nombreCliente;
    private Comando comando;
    private DataOutputStream dataOutputStream;

    public Cliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        running = false;
    }

    public void iniciarCliente(String hostName, int puerto) {
        this.hostName = hostName;
        this.puerto = puerto;
        running = true;
        hilo = new Thread(this);
        hilo.start();
        System.out.println("Cliente.iniciarCliente: Starting client in hostname: " + hostName + " puerto: " + puerto);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(hostName, puerto);
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));) {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            referenceOuputStream(dataOutputStream);

            dataOutputStream.writeUTF("BNAVAL:" + Comando.CONECTAR.getNombre() + "," + nombreCliente);
            dataOutputStream.flush();

            while (running) {
                String strReceivedMessage = dataInputStream.readUTF();
                System.out.println("Cliente.run: Escupalo: " + strReceivedMessage);
            }
//            dataOutputStream.close();
            System.out.println("Cliente: ClientSocket has started succesfully");
        } catch (IOException e) {
            System.out.println("Cliente: El socket del cliente NO ha iniciado satisfactoriamente");
            System.exit(1);
        }
    }

    public void escribirMensaje(String mensaje) {
        try {
            dataOutputStream.writeUTF(mensaje);
            dataOutputStream.flush();
        } catch (Exception e) {
        }
    }
    
//    public void sendMessage(Message message) {
//        try {
//            lastCommand = message.getCommand();
//            String strConnectionMessage = message.getString();
//
//            LOGGER.debug("Client writing Message {}", strConnectionMessage);
//            dataOutputStream.writeUTF(strConnectionMessage);
//            dataOutputStream.flush();
//        } catch (IOException e) {
//            LOGGER.error("Oops! There is an unexpected error", e);
//            throw new AssertionError("Player Connection Error", e);
//        }
//    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    private void referenceOuputStream(DataOutputStream dataOutputStream) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.dataOutputStream = dataOutputStream;
    }
}
