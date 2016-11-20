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

    public Mensaje(DataInputStream flujoEntrada) {
        this.flujoLectura = flujoEntrada;
    }

    public void leerFlujo() throws IOException {
        String mensaje = flujoLectura.readUTF();
        String[] strings = mensaje.split(",");
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
