package edu.udistrital.batallanaval.logica;

/**
 *
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
import edu.udistrital.batallanaval.logica.Casilla;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Actor {

    protected int x, y;
    protected int width, height;

//    jsaenzar: SE COMENTA DEL CODIGO ORIGINAL   
//    protected String spriteName;
//    protected Escenario stage;
//    protected SpriteCache spriteCache;
    private ArrayList<Casilla> casillas;

    public Actor(/*Escenario stage*/) {
//      jsaenzar: SE COMENTA DEL CODIGO INICIAL        
//        this.stage = stage;
//        spriteCache = stage.getSpriteCache();
        casillas = new ArrayList<Casilla>();
    }

    //    jsaenzar: SE COMENTA DEL CODIGO ORIGINAL   
//    public void paint(Graphics2D g){
//        g.drawImage( spriteCache.getSprite(spriteName), x,y, stage );
//    }
//    public String getSpriteName() {
//        return spriteName;
//    }
//
//    public void setSpriteName(String string) {
//        spriteName = string;
//        BufferedImage image = spriteCache.getSprite(spriteName);
//        height = image.getHeight();
//        width = image.getWidth();
//    }
//    *********************************************************************************************************+
    

    public int getX() {
        return x;
    }

    public void setX(int i) {
        x = i;
    }

    public int getY() {
        return y;
    }

    public void setY(int i) {
        y = i;
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

    public void act() {
    }

    void setOcupado(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

}
