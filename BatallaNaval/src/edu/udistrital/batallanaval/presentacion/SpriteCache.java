package edu.udistrital.batallanaval.presentacion;

/**
 *   
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
    
public class SpriteCache {
    private HashMap sprites;
      
    public SpriteCache() {
        sprites = new HashMap();
    }
      
    private BufferedImage loadImage(String nombre) {
        URL url=null;
        try {
          url = getClass().getClassLoader().getResource(nombre);
          return ImageIO.read(url);
        } catch (Exception e) {
          System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
          System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
          System.exit(0);
         return null;
        }
    }
      
    public BufferedImage getSprite(String nombre) {
        BufferedImage img;
        img = (BufferedImage)sprites.get(nombre);
        if (img == null) {
            img = loadImage("edu/udistrital/batallanaval/recursos/"+nombre);
            sprites.put(nombre,img);
        }
        return img;
    }
}
