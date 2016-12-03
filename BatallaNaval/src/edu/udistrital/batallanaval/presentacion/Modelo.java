package edu.udistrital.batallanaval.presentacion;

import edu.udistrital.batallanaval.logica.Casilla;
import edu.udistrital.batallanaval.enums.TipoBarco;
import edu.udistrital.batallanaval.logica.Sistema;
import edu.udistrital.batallanaval.logica.socket.Cliente;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Modelo de la Aplicacion
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class Modelo implements Runnable, Escenario {

    private Vista vista;
    private Thread hilo;
    public BufferedImage buffer;
    public BufferStrategy strategy;
//  private ArrayList casillas;
    private String estado;
    private SpriteCache spriteCache;
    private int cBarcosEnemigos;
    private Thread hiloSocket;

//    jsaenzar: DECLARANDO LISTADO DE ACTORES - BARCOS AMIGOS y BARCOS ENEMIGOS
    private Sistema sistema;

    public Modelo() {
        cBarcosEnemigos = 0;
        spriteCache = new SpriteCache();
        hilo = new Thread(this);
        //buffer = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        getVista().getLienzo().createBufferStrategy(2);
        strategy = getVista().getLienzo().getBufferStrategy();
        getVista().getLienzo().requestFocus();
    }

    public void iniciar() {
        estado = "Nuevo";
        getVista().setSize(1200, 600);
        getVista().setVisible(true);
        boolean esServidor = true;
        boolean esCliente = false;
        if (esServidor) {
            getSistema().getServidor().iniciarServidor(7000);
            getSistema().getCliente().iniciarCliente("localhost", 7000);
        }
        if (esCliente) {
            getSistema();
            getSistema().setNombreCliente("Saenz");
            getSistema().getCliente().iniciarCliente("192.168.0.36", 7000);
        }
        hilo.run();
    }

    @Override
    public void run() {
        initWorld();
        while (getVista().getLienzo().isVisible()) {
//          jsaenzar: WARNING - VALIDAR SI ESTA LINEA SOBRA
//            updateWorld();
            dibujarMundo();
//          jsaenzar: WARNING - VALIDAR SI ESTA LINEA SOBRA
//            dibujar(getVista().getLienzo().getGraphics());
            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException e) {
            }
        }
    }

    public void initWorld() {
        getSistema().initWorld();
        System.out.println("***********************MUNDO CARGADO******************************************");
    }

    public void dibujarMundo() {
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, getVista().getLienzo().getWidth(), getVista().getLienzo().getHeight());

//        SE CARGA LA IMAGEN A LA CASILLA
        for (int i = 0; i < getSistema().getCasillas().size(); i++) {
            Casilla m = (Casilla) getSistema().getCasillas().get(i);
            paint(g, m);

        }
        g.setColor(Color.white);
        strategy.show();
    }

//    ********************************************************************************
    //        jsaenzar: WARNING - VALIDAR SI ESTE METODO SOBRA
//    protected void dibujar(Graphics lapiz) {
//        lapiz.drawImage(buffer, 0, 0, vista.getLienzo());
//    }
//    public void updateWorld() {
//        for (int i = 0; i < casillas.size(); i++) {
//            Casilla m = (Casilla) casillas.get(i);
//            m.act();
//        }
//    }
//***********************************************************************************++
    private Vista getVista() {
        if (vista == null) {
            vista = new Vista(this);
        }
        return vista;
    }

    public void checkClick(int x, int y) {
//       jsaenzar: SE COMENTA CODIGO ORIGINAL 
//        if (estado.compareTo("Nuevo") == 0 && cBarcosAmigos < 10) {
//        if (estado.compareTo("Nuevo") == 0) {
        if (!getSistema().isBarcosAmigosCargados()) {
            ArrayList<TipoBarco> tipoBarcoList = new ArrayList<>();
            seleccionarBarco(tipoBarcoList);
//            getSistema().cargarBarcosAmigos(x, y, tipoBarco, casillas);
            getSistema().cargarBarcosAmigos(x, y, tipoBarcoList);

        } else if (cBarcosEnemigos < 2) {
//            getSistema().validarImpactoCasillaEnemigo(x, y, casillas);
            getSistema().impactarCasillaEnemigo(x, y);
            if (getSistema().isClicTableroCorrecto()) {
                if (getSistema().isImpactoCasillaEnemigo()) {
                    cBarcosEnemigos++;
                    System.out.println("Barco Enemigo impactado" + cBarcosEnemigos);
                } else {
                    System.out.println("Impacto Fallido");
                }
            } else {
                System.out.println("No hizo click en casilla de enemigo. Intente nuevamente!");
            }

            if (cBarcosEnemigos == 2) {

                System.out.println("***************VALIDAR IMPACTOS EN CASILLAS DE AMIGOS******************");

            }

        } else {

            System.out.println("GANO!");
////            getSistema().validarImpactoCasillaAmigo(x, y, casillas);
//            getSistema().validarImpactoCasillaAmigo(x, y);
//            if (!getSistema().isClicTableroCorrecto()) {
//                System.out.println("No hizo click en casilla indicada. Intente nuevamente!");
//            }
        }
    }

    public Sistema getSistema() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SpriteCache getSpriteCache() {
        return spriteCache;
    }

    public void paint(Graphics2D g, Casilla casilla) {

//    jsaenzar: SE CARGAN A LA CASILLA LOS VALORES DEL ANCHO Y ALTO DE LA IMAGEN        
        BufferedImage image = spriteCache.getSprite(casilla.getSpriteName());
        casilla.setHeight(image.getHeight());
        casilla.setWidth(image.getWidth());

//    jsaenzar: SE PINTA LA IMAGEN QUE SE PINTABA EN EL ACTOR        
        g.drawImage(spriteCache.getSprite(casilla.getSpriteName()), casilla.getX(), casilla.getY(), this);

    }

    private void seleccionarBarco(ArrayList<TipoBarco> tipoBarcoList) {

//        TipoBarco tipoBarco;
//        for (int i = 1; i < 5; i++) {
//
//            if (i == 1) {
//
//                for (int j = 0; j < i; j++) {
//                    tipoBarco = TipoBarco.BIGGEST;
//                    tipoBarcoList.add(tipoBarco);
//                    tipoBarco = TipoBarco.BIGGEST_2;
//                    tipoBarcoList.add(tipoBarco);
//                    tipoBarco = TipoBarco.BIGGEST_3;
//                    tipoBarcoList.add(tipoBarco);
//                    tipoBarco = TipoBarco.BIGGEST_4;
//                    tipoBarcoList.add(tipoBarco);
//                }
//
//            } else if (i == 2) {
//                for (int j = 1; j < 5; j++) {
//
//                }
//            }
//            {
//            }
//        }
        int rand = (int) (Math.random() * (4) + 1);
        TipoBarco tipoBarco;

        if (rand == 1) {
            tipoBarco = TipoBarco.SMALL;
            tipoBarcoList.add(tipoBarco);
        }
        if (rand == 2) {
            tipoBarco = TipoBarco.MEDIUM;
            tipoBarcoList.add(tipoBarco);
            tipoBarco = TipoBarco.MEDIUM_2;
            tipoBarcoList.add(tipoBarco);
        } else if (rand == 3) {
            tipoBarco = TipoBarco.BIG;
            tipoBarcoList.add(tipoBarco);
            tipoBarco = TipoBarco.BIG_2;
            tipoBarcoList.add(tipoBarco);
            tipoBarco = TipoBarco.BIG_3;
            tipoBarcoList.add(tipoBarco);
        } else if (rand == 4) {
            tipoBarco = TipoBarco.BIGGEST;
            tipoBarcoList.add(tipoBarco);
            tipoBarco = TipoBarco.BIGGEST_2;
            tipoBarcoList.add(tipoBarco);
            tipoBarco = TipoBarco.BIGGEST_3;
            tipoBarcoList.add(tipoBarco);
            tipoBarco = TipoBarco.BIGGEST_4;
            tipoBarcoList.add(tipoBarco);

        }
        System.out.println("Tipo de Barco: " + rand);

    }

}
