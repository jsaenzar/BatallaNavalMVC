/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udistrital.batallanaval.enums;


// Clase que contiene los tipos de barco 
public enum TipoBarco {
    SMALL ("ship-white.png", 1), //Separamos con comas
    MEDIUM ("ship-white64.png", 2),
    BIG("ship-white96.png", 3);  //Cuando terminamos cerramos con ;
 
    //Campos tipo constante   
    private final String nombreImg; //Color de la madera
    private final int numCasillas; //Peso específico de la madera
 
    /**
     * Constructor. Al asignarle uno de los valores posibles a una variable del tipo enumerado el constructor asigna 
        automáticamente valores de los campos
     */ 
    TipoBarco (String nombreImg, int numCasillas) { 
        this.nombreImg = nombreImg;
        this.numCasillas = numCasillas;
    } //Cierre del constructor
 
    //Métodos de la clase tipo Enum
    public String getNombreImg() { return nombreImg; }
    public int getNumCasillas() { return numCasillas; }
} //Cierre del enum