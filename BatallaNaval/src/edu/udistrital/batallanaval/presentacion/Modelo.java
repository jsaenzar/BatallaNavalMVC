package edu.udistrital.batallanaval.presentacion;

import edu.udistrital.batallanaval.logica.Casilla;
import edu.udistrital.batallanaval.enums.TipoBarco;
import edu.udistrital.batallanaval.logica.Sistema;
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
    private ArrayList casillas;
    private String estado;
    private SpriteCache spriteCache;
    private int cBarcosEnemigos;

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

//        getVista().setSize(800, 600);
        getVista().setSize(1200, 600);
        getVista().setVisible(true);
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
        int x;
        int y = 10;
        casillas = new ArrayList();
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
                System.out.println("Casillas Amigas - Coordenadas: " + "(" + x + "," + y + ")"
                        + " Ubicacion: " + "(" + m.getUbicacion()[0][0] + "," + m.getUbicacion()[0][1] + ")");
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

        y = 10;
        for (int j = 0; j < 10; j++) {
            x = 800;
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
                System.out.println("Casillas Amigas desde Enemigo - Coordenadas: " + "(" + x + "," + y + ")"
                        + " Ubicacion: " + "(" + m.getUbicacion()[0][0] + "," + m.getUbicacion()[0][1] + ")");
                casillas.add(m);

            }
        }

        getSistema().cargarCasillasOcupadasEnemigo(casillas);

    }

    public void dibujarMundo() {
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, getVista().getLienzo().getWidth(), getVista().getLienzo().getHeight());

//        SE CARGA LA IMAGEN A LA CASILLA
        for (int i = 0; i < casillas.size(); i++) {
            Casilla m = (Casilla) casillas.get(i);
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

        TipoBarco tipoBarco = TipoBarco.SMALL;
        int rand = (int) (Math.random() * (3) + 1);
        System.out.println("rand: " + rand);

        if (rand == 2) {
            tipoBarco = TipoBarco.MEDIUM;
        } else if (rand == 3) {
            tipoBarco = TipoBarco.BIG;
        }

//        if (estado.compareTo("Nuevo") == 0) {
        if (!getSistema().isBarcosAmigosCargados()) {

            getSistema().cargarBarcosAmigos(x, y, tipoBarco, casillas);

        } else if (cBarcosEnemigos < 2) {
            getSistema().validarImpactoCasillaEnemigo(x, y, casillas);
            if (getSistema().isClicCasillaEnemigo()) {
                if (getSistema().isImpactoCasillaEnemigo()) {
                    cBarcosEnemigos++;
                    System.out.println("Barco Enemigo impactado" + cBarcosEnemigos);
                } else {
                    System.out.println("Impacto Fallido");
                }
            } else {
                System.out.println("No hizo click en casilla de enmigo. Intente nuevamente!");
            }

        } else {
            System.out.println("VALIDAR IMPACTOS EN CASILLAS DE AMIGOS");

            getSistema().validarImpactoCasillaAmigo(x, y, casillas);
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

}
