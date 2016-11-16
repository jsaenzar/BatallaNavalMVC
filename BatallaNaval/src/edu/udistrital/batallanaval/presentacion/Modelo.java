package edu.udistrital.batallanaval.presentacion;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * Modelo de la Aplicacion  
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class Modelo extends Thread implements Escenario{
    private Vista vista;
    private Thread hilo;
    public BufferedImage buffer;
    public BufferStrategy strategy;
    private SpriteCache spriteCache;
    private ArrayList actors;
    private String estado;
    private ArrayList barcosAmigos;
    private ArrayList barcosEnemigos;
    private int cBarcosAmigos;
    private int cBarcosEnemigos;
    
    public Modelo() {    
        spriteCache = new SpriteCache();
        hilo = new Thread(this);
        //buffer = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        getVista().getLienzo().createBufferStrategy(2);
        strategy = getVista().getLienzo().getBufferStrategy();
        getVista().getLienzo().requestFocus();

    }
    
    public void iniciar() {
        estado = "Nuevo";
        barcosAmigos = new ArrayList();
        barcosEnemigos = new ArrayList();
        cBarcosAmigos = 0;
        cBarcosEnemigos = 0;
        getVista().setSize(800, 600);
        getVista().setVisible(true);        
        hilo.run();
    }
    
    protected void dibujar(Graphics lapiz){
        lapiz.drawImage(buffer, 0, 0, vista.getLienzo());      
    }

    public void dibujarMundo() {
        Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
        g.setColor(Color.blue);
        g.fillRect(0,0,getVista().getLienzo().getWidth(),getVista().getLienzo().getHeight());
        //System.out.println("actors.size(): " + actors.size());
        for (int i = 0; i < actors.size(); i++) {
            Actor m = (Actor)actors.get(i);
            m.paint(g);
        }
        g.setColor(Color.white);
        strategy.show();
    }  
    
    public void initWorld() {
        int x;
        int y = 10;
        actors = new ArrayList();
        for (int j = 0; j < 10; j++){
            x = 10;
            y += 32;
            for (int i = 0; i < 10; i++){
                x += 32;
                Casilla m = new Casilla(this);
                m.setX(x);
                m.setY(y);
                //System.out.println("("+x+","+y+")");
                m.setTipo(1);
                actors.add(m);
            }
        }
        
        y = 10;
        for (int j = 0; j < 10; j++){
            x = 400;
            y += 32;
            for (int i = 0; i < 10; i++){
                x += 32;
                Casilla m = new Casilla(this);
                m.setX(x);
                m.setY(y);
                m.setTipo(2);
                actors.add(m);
                
            }          
        }
        for(int k = 0; k < 10; k++){
            int rand = (int)(Math.random() * (100)) + 100;
            Actor m = (Actor)actors.get(rand);
            barcosEnemigos.add(m);  
            System.out.println("BarcoEnemigo: (" + m.getX() + "," + m.getY() + ")");
        }
        
    }

    private Vista getVista(){
        if (vista==null){
            vista = new Vista(this);
        }
        return vista;
    }
    
    public void run(){
        initWorld();
        while (getVista().getLienzo().isVisible()) {
            updateWorld();
            dibujarMundo();
            dibujar(getVista().getLienzo().getGraphics());
            try { 
             Thread.sleep(SPEED);
          } catch (InterruptedException e) {}
            
        }
    }
    
    public void updateWorld() {
        for (int i = 0; i < actors.size(); i++) {
            Actor m = (Actor)actors.get(i);
            m.act();
        }
    }
    
    public SpriteCache getSpriteCache() {
        return spriteCache;
    } 

    public boolean imageUpdate(Image image, int i, int i1, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void checkClick(int x, int y){
        if(estado.compareTo("Nuevo") == 0 && cBarcosAmigos < 10){
            for (int i = 0; i < 100; i++) {
                Actor m = (Actor)actors.get(i);
                if(x <= m.getWidth() + m.getX() && x > m.getX()){
                    if(y <= m.getHeight() + m.getY() && y > m.getY()){
                        m.setSpriteName("ship-white.png");
                        barcosAmigos.add(m);
                        cBarcosAmigos++;                        
                    }
                }           
            }            
        }
        else{
            estado = "Juego";
            if(estado.compareTo("Juego")==0){
                for (int i = 100; i < 200; i++) {
                    Actor m = (Actor)actors.get(i);
                    if(x <= m.getWidth() + m.getX() && x > m.getX() && y <= m.getHeight() + m.getY() && y > m.getY()){
                        System.out.println("Click: (" + m.getX() + "," + m.getY() + ")");
                        for(int j = 0; j < 10; j++){
                            Actor n = (Actor)barcosEnemigos.get(j);
                            
                            if(m.getX() == n.getX() && m.getY() == n.getY()){
                                m.setSpriteName("ship-red.png");
                                break;
                            }
                            else{
                                m.setSpriteName("ship-blue.png");                                 
                            }
                        }            
                    }
                }
            }   
        }
    }
}

