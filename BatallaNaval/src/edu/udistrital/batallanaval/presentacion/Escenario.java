package edu.udistrital.batallanaval.presentacion;

/**
 *   
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
import java.awt.image.ImageObserver;
  
public interface Escenario extends ImageObserver {
    public static final int WIDTH=800;
    public static final int HEIGHT=600;
    public static final int SPEED=10;
    public SpriteCache getSpriteCache();
}
