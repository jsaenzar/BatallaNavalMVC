package edu.udistrital.batallanaval.logica.socket;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class Mensaje {

    private DataInputStream flujoLectura;
    private String strComando;
    private String strParam1;
    private String strParam2;
    private String strReceivedMessage;

    public Mensaje() {
//        this.flujoLectura = flujoEntrada;
//        this.flujoLectura = flujoEntrada;
//        this.strReceivedMessage = strReceivedMessage;
    }

    public void leerFlujo(DataInputStream flujoEntrada) throws IOException {
        String mensaje = flujoLectura.readUTF();
        String[] strings = mensaje.split(",");
        setStrComando(strings[0]);
        setStrParam1(strings[1]);
        if (strings.length == 3) {
            setStrParam2(strings[2]);
        }
    }
    
    public void splitString(String strReceivedMessage) throws IOException {
        String[] strings = strReceivedMessage.split(",");
        setStrComando(strings[0]);
        setStrParam1(strings[1]);
        if (strings.length == 3) {
            setStrParam2(strings[2]);
        }
    }

    public String getStrComando() {
        return strComando;
    }

    public void setStrComando(String strComando) {
        this.strComando = strComando;
    }

    public String getStrParam1() {
        return strParam1;
    }

    public void setStrParam1(String strParam1) {
        this.strParam1 = strParam1;
    }

    public String getStrParam2() {
        return strParam2;
    }

    public void setStrParam2(String strParam2) {
        this.strParam2 = strParam2;
    }
}
