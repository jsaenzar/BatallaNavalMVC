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
        System.out.println("ServidorCliente.iniciarServidorCliente: Starting client Thread in server: " + clienteSocket.getInetAddress());
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
                Mensaje mensaje = new Mensaje(dataInputStream);
                mensaje.leerFlujo();
                System.out.println("mensaje.getStrComando(): " + mensaje.getStrComando());

                switch (mensaje.getStrComando()) {
                    case "BNAVAL:CON":
                        System.out.println(mensaje.getStrComando() + "," + mensaje.getStrParam1());
                        dataOutputStream.writeUTF("OK,Leo");
                        dataOutputStream.flush();
//                        escribirMensaje(dataOutputStream, "-OK,Leo-");
                        break;
                    case "BNAVAL:LIS":
                        System.out.println(mensaje.getStrComando());
                        dataOutputStream.writeUTF("OK");
                        dataOutputStream.flush();
//                        escribirMensaje(dataOutputStream, "-OK-");
                        break;
                    case "BNAVAL:ATK":
                        System.out.println(mensaje.getStrComando() + "," + mensaje.getStrParam1()+ "," + mensaje.getStrParam2());
                        servidor.validarAtacante(this, mensaje.getStrComando() + "," + mensaje.getStrParam1()+ "," + mensaje.getStrParam2());
//                        dataOutputStream.writeUTF("OKALGO");
//                        dataOutputStream.flush();//                      
                        break;
                    default:
                        System.out.println("ERROR AL PROCESAR COMANDO");
                        break;
                }
            }
        } catch (Exception e) {

        }
    }

    public void escribirMensaje(String mensaje) {
        try {
            dataOutputStream.writeUTF(mensaje);
            dataOutputStream.flush();
        } catch (Exception e) {
        }
    }

    private void referenceOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }
}
