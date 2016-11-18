package edu.udistrital.batallanaval.logica;

import edu.udistrital.batallanaval.presentacion.Escenario;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class Casilla /*extends Actor*/ {

    protected int vx;
    protected boolean ocupado;
    private int tipo;

//    jsaenzar: SE DECLARAN VARIABLES TRAIDAS DE LA CLASE ACTOR
    private int x, y;
    private int width, height;

//    jsaenzar: SE AGREGAN NUEVO ATRIBUTOS
//    protected SpriteCache spriteCache;
    protected String spriteName;
    protected Escenario stage;
    
    
    private int [][] ubicacion ;

    public Casilla(/*Escenario stage*/) {
//        jsaenzar: SE COMENTA DEL COD INICIAL
//        super(stage);
//        setSpriteName("celda.png");

//        jsaenzar: SE CARGA EL CACHE QUE ESTABA EN LA CLASE ACTOR
//        this.stage = stage;
//        spriteCache = stage.getSpriteCache();

        spriteName = "celda.png";

        ocupado = false;
        
        ubicacion = new int[1][2];
    }


//    public void paint(Graphics2D g) {
// 
////    jsaenzar: SE CARGAN A LA CASILLA LOS VALORES DEL ANCHO Y ALTO DE LA IMAGEN        
//        BufferedImage image = spriteCache.getSprite(spriteName);
//        height = image.getHeight();
//        width = image.getWidth();
//        
////    jsaenzar: SE PINTA LA IMAGEN QUE SE PINTABA EN EL ACTOR        
//        g.drawImage(spriteCache.getSprite(spriteName), x, y, stage);
//
//    }

    public void act() {
        x += vx;
        if (x < 0 || x > Escenario.WIDTH) {
            vx = -vx;
        }
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int i) {
        vx = i;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    void setTipo(int tipo) {
        this.tipo = tipo;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getTipo() {
        return tipo;
    }

//   jsaenzar: SE TRAEN GETTERS Y SETTERS DE LA CLASE ACTOR 
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int i) {
        height = i;
    }

    public void setWidth(int i) {
        width = i;
    }

    public int[][] getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int[][] ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getSpriteName() {
        return spriteName;
    }
    
    

    public void setSpriteName(String string) {
        spriteName = string;
//        BufferedImage image = spriteCache.getSprite(spriteName);
//        SE COMENTA DEL CODIGO ORIGINAL
//        height = image.getHeight();
//        width = image.getWidth();
    }
//        ******************************************************************************************
}
