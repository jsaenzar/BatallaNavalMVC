package edu.udistrital.batallanaval.presentacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Controlador principal del Juego  
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class Controlador implements MouseListener{
    
    private Vista vista;

    public Controlador(Vista vista){
        this.vista = vista;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        getVista().getModelo().checkClick(me.getX(), me.getY());
    }

    public Vista getVista() {
        return vista;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //System.out.println("mousePressed");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //System.out.println("mouseReleased");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //System.out.println("mouseEntered");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //System.out.println("mouseExited");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
