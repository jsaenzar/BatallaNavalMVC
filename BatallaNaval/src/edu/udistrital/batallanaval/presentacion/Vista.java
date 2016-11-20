package edu.udistrital.batallanaval.presentacion;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Vista principal del juego
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class Vista extends JFrame {

    Modelo modelo;
    private Controlador controlador;
    private JPanel panel;
    private JMenu jMenu1, jMenu2;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private Canvas lienzo;

//    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int WIDTH = 1200;

    public Vista(Modelo modelo) {
        this.modelo = modelo;
        iniciarComponentes();
        capturarEventos();
    }

    public Controlador getControlador() {
        if (controlador == null) {
            this.controlador = new Controlador(this);
        }
        return controlador;
    }

    private void iniciarComponentes() {
        panel = new JPanel();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenu2 = new JMenu();
        lienzo = new Canvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Batalla Naval");

        lienzo.setBounds(0, 0, WIDTH, HEIGHT);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);
        panel.add(lienzo);

        jMenu1.setText("Juego");
        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);
        add(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 483, Short.MAX_VALUE)
        );

        pack();
    }

    private void capturarEventos() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        lienzo.addMouseListener(getControlador());
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public Canvas getLienzo() {
        if (lienzo == null) {
            lienzo = new Canvas();
        }
        return lienzo;
    }

    public Modelo getModelo() {
        return modelo;
    }
/*
    public boolean handleEvent(Event e) {
        if ((e.target == this) && (e.id == Event.WINDOW_DESTROY)) {
            if (sfd != null) {
                try {
                    sfd.close();
                } catch (IOException ioe) {
                    System.out.println("Error: " + ioe);
                }
                this.dispose();
            }
        }
        return true;
    }
*/
}
