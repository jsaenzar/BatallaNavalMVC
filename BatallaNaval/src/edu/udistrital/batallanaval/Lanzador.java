package edu.udistrital.batallanaval;

import edu.udistrital.batallanaval.presentacion.*;

public class Lanzador {
    private Modelo modelo;

    public Lanzador() {
        modelo = new Modelo();
        modelo.iniciar();        
    }
    
    public static void main(String [] args){
        new Lanzador();
    }   
}
