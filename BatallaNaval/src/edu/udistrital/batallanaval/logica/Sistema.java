/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udistrital.batallanaval.logica;

import edu.udistrital.batallanaval.enums.TipoBarco;
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
    private boolean clicCasillaEnemigo;

    public Sistema() {

        barcosAmigosList = new ArrayList<Actor>();
        casillasEnemigo = new ArrayList<Casilla>();
        barcosAmigosCargados = false;
        impactoCasillaAmigo = false;
        impactoCasillaEnemigo = false;
    }

    public void cargarBarcosAmigos(int x, int y, TipoBarco tipoBarco, ArrayList casillas) {

        if (barcosAmigosList.size() < 10) {
            Actor barcoAmigo = new Actor();
            boolean isAgregarBarco = false;
            for (int i = 0; i < 100; i++) {
//             jsaenzar: SE COMENTA DEL CODIGO ORIGINAL
//                Actor m = (Actor) actors.get(i);

//                jsaenzar: SE CARGA EL LISTADO DE CASILLAS OCUPADAS POR BARCOS AMIGOS
                Casilla m = (Casilla) casillas.get(i);
                //             jsaenzar: SE COMENTA DEL CODIGO ORIGINAL               
//                if (x <= m.getWidth() + m.getX() && x > m.getX() && y <= m.getHeight() + m.getY() && y > m.getY()) {

                if (!m.isOcupado()) {

                    if (isClickEnCasilla(x, y, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
                        isAgregarBarco = true;
//                        System.out.println("Click - posicion Barco: (" + m.getX() + "," + m.getY() + ")");
//                        System.out.println("Click - tamanio Celda: (" + m.getWidth() + "," + m.getHeight() + ")");
////                    if (y <= m.getHeight() + m.getY() && y > m.getY()) {
////                        m.setSpriteName("ship-white.png");
////                        m.setSpriteName("ship-white64.png");
//
//                        System.out.println("Click - tamanio Barco: (" + m.getWidth() + "," + m.getHeight() + ")");
//                        barcosAmigos.add(m);
//                        cBarcosAmigos++;

//                jsaenzar: SE CARGA BARCO AMIGO EN EL LISTADO DE ACTORES - BARCOS AMIGOS                       
                        for (int a = 0; a < tipoBarco.getNumCasillas(); a++) {

                            if (a == 0) {
                                m.setSpriteName(tipoBarco.getNombreImg());
                                m.setOcupado(true);
                                barcoAmigo.getCasillas().add(m);

                            } else {
                                Casilla m2 = (Casilla) casillas.get(i + a);
                                m2.setOcupado(true);
                                barcoAmigo.getCasillas().add(m2);
                            }
//                            if (a == 0) {
//                                m.setSpriteName(tipoBarco.getNombreImg());
//                            }
//                            m.setOcupado(true);
//                            barcoAmigo.getCasillas().add(m);
//                            m = (Casilla) casillas.get(i + 1);
                        }

                    }

                }

            }
            if (isAgregarBarco) {
                barcosAmigosList.add(barcoAmigo);
                isAgregarBarco = false;

            }
            System.out.println("Contador barcosAmigosList:" + barcosAmigosList.size());

        } else {
            barcosAmigosCargados = true;
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
            System.out.println("BarcoEnemigo: (" + c.getX() + "," + c.getY() + ")" + " Ubicacion: " + "(" + c.getUbicacion()[0][0] + "," + c.getUbicacion()[0][1] + ")");

        }
            System.out.println(" Numero de casillas de Barcos Enemigos: " + casillasEnemigo.size());
        
    }

    public void validarImpactoCasillaEnemigo(int x, int y, ArrayList casillas) {

        impactoCasillaEnemigo = false;
        for (int i = 100; i < 200; i++) {
//             jsaenzar: SE COMENTA DEL CODIGO ORIGINAL
//                    Actor m = (Actor) actors.get(i);

//       jsaenzar:       SE UBICA LA CASILLA DONDE SE DIO CLIC       
            Casilla m = (Casilla) casillas.get(i);
            if (isClickEnCasilla(x, y, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
                clicCasillaEnemigo = true;
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
                clicCasillaEnemigo = false;
            }
        }

    }

    public boolean isBarcosAmigosCargados() {
        return barcosAmigosCargados;
    }

    public boolean isImpactoCasillaEnemigo() {
        return impactoCasillaEnemigo;
    }

    public boolean isClicCasillaEnemigo() {
        return clicCasillaEnemigo;
    }

    public void validarImpactoCasillaAmigo(int x, int y, ArrayList casillas) {

        impactoCasillaAmigo = false;
        for (int i = 200; i < 300; i++) {

            Casilla m = (Casilla) casillas.get(i);
            if (isClickEnCasilla(x, y, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
                System.out.println("Click: (" + m.getX() + "," + m.getY() + ")");

                for (int j = 0; j < 100; j++) {

                    Casilla n = (Casilla) casillas.get(j);

                    if (m.getUbicacion()[0][0] == n.getUbicacion()[0][0] && m.getUbicacion()[0][1] == n.getUbicacion()[0][1]) {
                        if (n.isOcupado()) {
                            impactoCasillaAmigo = true;
                            m.setSpriteName("ship-red.png");
                            n.setSpriteName("ship-red.png");
                            System.out.println("IMPACTO CASILLA AMIGO EXITOSO");
                            break;
                        } else {
                            m.setSpriteName("ship-blue.png");
//                            n.setSpriteName("ship-blue.png");
                            
                        }
                    }

                }
            }
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isImpactoCasillaAmigo() {

        return impactoCasillaAmigo;
    }

}
