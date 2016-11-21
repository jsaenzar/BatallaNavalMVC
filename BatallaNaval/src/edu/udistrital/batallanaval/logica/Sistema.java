/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udistrital.batallanaval.logica;

import edu.udistrital.batallanaval.enums.TipoBarco;
import edu.udistrital.batallanaval.logica.socket.Cliente;
import edu.udistrital.batallanaval.logica.socket.Servidor;
import edu.udistrital.batallanaval.logica.socket.ServidorCliente;
import java.util.ArrayList;

/**
 *
 * @author jsaenzar
 */
public class Sistema {

    private ArrayList<Actor> barcosAmigosList;
    private ArrayList<Casilla> casillasEnemigo;
    private boolean barcosAmigosCargados;
    private boolean impactoCasillaAmigo;
    private boolean impactoCasillaEnemigo;
    private boolean clicTableroCorrecto;
    private ArrayList casillas;
    private Cliente cliente;
    private Servidor servidor;
    private ServidorCliente servidorCliente;

    private String nombreCliente;

    public Sistema() {

        casillas = new ArrayList();

        barcosAmigosList = new ArrayList<Actor>();
        casillasEnemigo = new ArrayList<Casilla>();
        barcosAmigosCargados = false;
        impactoCasillaAmigo = false;
        impactoCasillaEnemigo = false;
    }

    public void initWorld() {
        int x;
        int y = 10;
//        casillas = new ArrayList();
        for (int j = 0; j < 10; j++) {
            x = 10;
            y += 32;
            for (int i = 0; i < 10; i++) {
                x += 32;
                Casilla m = new Casilla(/*this*/);
                m.setX(x);
                m.setY(y);
                int[][] u = new int[1][2];
                u[0][0] = j;
                u[0][1] = i;
                m.setUbicacion(u);
//                System.out.println("Casillas Amigas - Coordenadas: " + "(" + x + "," + y + ")"
//                        + " Ubicacion: " + "(" + m.getUbicacion()[0][0] + "," + m.getUbicacion()[0][1] + ")");
//                m.setTipo(1);
                casillas.add(m);
            }
        }

        y = 10;
        for (int j = 0; j < 10; j++) {
            x = 400;
            y += 32;
            for (int i = 0; i < 10; i++) {
                x += 32;
                Casilla m = new Casilla(/*this*/);
                m.setX(x);
                m.setY(y);
                int[][] u = new int[1][2];
                u[0][0] = j;
                u[0][1] = i;
                m.setUbicacion(u);
//                m.setTipo(2);
                casillas.add(m);

            }
        }

//        y = 10;
//        for (int j = 0; j < 10; j++) {
//            x = 800;
//            y += 32;
//            for (int i = 0; i < 10; i++) {
//                x += 32;
//                Casilla m = new Casilla(/*this*/);
//                m.setX(x);
//                m.setY(y);
//                int[][] u = new int[1][2];
//                u[0][0] = j;
//                u[0][1] = i;
//                m.setUbicacion(u);
////                m.setTipo(2);
////                System.out.println("Casillas Amigas desde Enemigo - Coordenadas: " + "(" + x + "," + y + ")"
////                        + " Ubicacion: " + "(" + m.getUbicacion()[0][0] + "," + m.getUbicacion()[0][1] + ")");
//                casillas.add(m);
//
//            }
//        }
//
//        cargarCasillasOcupadasEnemigo(casillas);
    }

    public void cargarBarcosAmigos(int x, int y, ArrayList tipoBarcoList/*, ArrayList casillas*/) {
        if (barcosAmigosList.size() < 10) {
            Actor barcoAmigo = new Actor();
            boolean isAgregarBarco = false;
            for (int i = 0; i < 100; i++) {
                Casilla m = (Casilla) casillas.get(i);
                if (!m.isOcupado()) {
                    if (isClickEnCasilla(x, y, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
                        isAgregarBarco = true;
                        TipoBarco t = (TipoBarco) tipoBarcoList.get(0);
                        for (int a = 0; a < t.getNumCasillas(); a++) {
                            cargarCasillaBarcoAmigo(i, a, tipoBarcoList, m, barcoAmigo);
                        }
                    }
                }
            }
            if (isAgregarBarco) {
                barcosAmigosList.add(barcoAmigo);
                isAgregarBarco = false;
            }
        } else {
            barcosAmigosCargados = true;
            getCliente().escribirMensaje("BNAVAL:LIS,ok");
            System.out.println("Contador barcosAmigosList:" + barcosAmigosList.size());
        }
    }

    private boolean isClickEnCasilla(int x, int y, int x0, int y0, int width, int height) {

        boolean clickEnCasilla = false;
        if (x <= width + x0 && x > x0 && y <= height + y0 && y > y0) {
            clickEnCasilla = true;
        }
        return clickEnCasilla;
    }

    public void cargarCasillasOcupadasEnemigo(ArrayList casillas) {

        for (int k = 0; k < 20; k++) {
            int rand = (int) (Math.random() * (100)) + 100;
            Casilla c = (Casilla) casillas.get(rand);
            casillasEnemigo.add(c);
//            System.out.println("BarcoEnemigo: (" + c.getX() + "," + c.getY() + ")" + " Ubicacion: " + "(" + c.getUbicacion()[0][0] + "," + c.getUbicacion()[0][1] + ")");

        }
//        System.out.println(" Numero de casillas de Barcos Enemigos: " + casillasEnemigo.size());

    }

    public void validarImpactoCasillaEnemigo(int x, int y/*, ArrayList casillas*/) {

        impactoCasillaEnemigo = false;
        for (int i = 100; i < 200; i++) {
//             jsaenzar: SE COMENTA DEL CODIGO ORIGINAL
//                    Actor m = (Actor) actors.get(i);

//       jsaenzar:       SE UBICA LA CASILLA DONDE SE DIO CLIC       
            Casilla m = (Casilla) casillas.get(i);
            if (isClickEnCasilla(x, y, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
                clicTableroCorrecto = true;
                System.out.println("Click: (" + m.getX() + "," + m.getY() + ")");
//                        for (int j = 0; j < 10; j++) {
//                            //             jsaenzar: SE COMENTA DEL CODIGO ORIGINAL
////                            Actor n = (Actor) barcosEnemigos.get(j);
//                            Casilla n = (Casilla) barcosEnemigos.get(j);
//
//                            if (m.getX() == n.getX() && m.getY() == n.getY()) {
//                                m.setSpriteName("ship-red.png");
//                                break;
//                            } else {
//                                m.setSpriteName("ship-blue.png");
//                            }
//                        }

                for (Casilla n : casillasEnemigo) {

                    if (m.getX() == n.getX() && m.getY() == n.getY()) {
                        m.setSpriteName("ship-red.png");
                        impactoCasillaEnemigo = true;
                        break;
                    } else {
                        m.setSpriteName("ship-blue.png");
                    }
                }
                break;

            } else {
                clicTableroCorrecto = false;
            }
        }

    }

    public boolean isBarcosAmigosCargados() {
        return barcosAmigosCargados;
    }

    public boolean isImpactoCasillaEnemigo() {
        return impactoCasillaEnemigo;
    }

    public boolean isClicTableroCorrecto() {
        return clicTableroCorrecto;
    }

    public void validarImpactoCasillaAmigo(int x, int y/*, ArrayList casillas*/) {

        impactoCasillaAmigo = false;
        for (int i = 200; i < 300; i++) {

            Casilla m = (Casilla) casillas.get(i);
            if (isClickEnCasilla(x, y, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
                clicTableroCorrecto = true;
                System.out.println("Click: (" + m.getX() + "," + m.getY() + ")");

                for (int j = 0; j < 100; j++) {

                    Casilla n = (Casilla) casillas.get(j);

                    if (m.getUbicacion()[0][0] == n.getUbicacion()[0][0] && m.getUbicacion()[0][1] == n.getUbicacion()[0][1]) {
                        if (n.isOcupado()) {
                            impactoCasillaAmigo = true;
                            m.setSpriteName("ship-red.png");
                            n.setSpriteName("bomb.png");
                            System.out.println("IMPACTO CASILLA AMIGO EXITOSO");
                            break;
                        } else {
                            m.setSpriteName("ship-blue.png");
                            break;
//                            n.setSpriteName("ship-blue.png");

                        }
                    }

                }
                if (clicTableroCorrecto) {
                    break;

                }
            } else {
                clicTableroCorrecto = false;
            }
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isImpactoCasillaAmigo() {

        return impactoCasillaAmigo;
    }

    public ArrayList getCasillas() {
        return casillas;
    }

    private void cargarCasillaBarcoAmigo(int i, int a, ArrayList tipoBarcoList, Casilla m, Actor barcoAmigo) {

        TipoBarco t = (TipoBarco) tipoBarcoList.get(0);
        if (a == 0) {
            m.setSpriteName(t.getNombreImg());
            m.setOcupado(true);
            barcoAmigo.getCasillas().add(m);
        } else {
            TipoBarco t2 = (TipoBarco) tipoBarcoList.get(a);
            Casilla m2 = (Casilla) casillas.get(i + a);
            m2.setSpriteName(t2.getNombreImg());
            m2.setOcupado(true);
            barcoAmigo.getCasillas().add(m2);
        }
    }

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente(nombreCliente);
        }
        return cliente;
    }

    public Servidor getServidor() {
        if (servidor == null) {
            servidor = new Servidor();
        }
        return servidor;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public ServidorCliente getServidorCliente() {
        return servidorCliente;
    }
}
