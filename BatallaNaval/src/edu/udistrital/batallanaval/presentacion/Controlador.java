package edu.udistrital.batallanaval.presentacion;

import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador principal del Juego
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class Controlador implements MouseListener, WindowListener {

    private Vista vista;

    public Controlador(Vista vista) {
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

    /*
     public boolean handleEvent(Event e) {
     System.out.println("handleEvent");
     if ((e.target == this) && (e.id == Event.WINDOW_DESTROY)) {
     System.out.println("IF WINDOW_DESTROY");
     if (getModelo().getSistema().getServidor().getSocket() != null) {
     System.out.println("EXISTE SOCKET A ELIMINAR");
     try {
     getModelo().getSistema().getServidor().getSocket().close();
     System.out.println("SOCKET CERRADO");
     } catch (IOException ioe) {
     System.out.println("Error: " + ioe);
     }
     this.dispose();
     }
     }
     return true;
     }*/
    @Override
    public void windowOpened(WindowEvent we) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        System.out.println("WindowEvent");
        try {
            if (getVista().getModelo().getSistema().getServidor() == null) {
                getVista().getModelo().getSistema().getServidor().getSocket().close();
            } else {
                getVista().getModelo().getSistema().getCliente().getSocket().close();
            }

            System.out.println("Socket cerrado");
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Cerrando Ventana");
        System.exit(0);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent we) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
