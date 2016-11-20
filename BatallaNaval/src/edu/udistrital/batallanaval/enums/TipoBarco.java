/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udistrital.batallanaval.enums;

// Clase que contiene los tipos de barco 
public enum TipoBarco {
//    SMALL ("ship-white.png", 1), //Separamos con comas
//    MEDIUM ("ship-white64.png","ship-white64.png", 2),
//    MEDIUM_2 ("ship-white64_2.png","ship-white64.png", 2),
//    BIG("ship-white96.png", 3),  //Cuando terminamos cerramos con ;
//    BIGGEST("ship-white128.png", 4);  //Cuando terminamos cerramos con ;

    SMALL("ship-white.png", 1), //Separamos con comas
    MEDIUM("ship-white64.png", 2),
    MEDIUM_2("ship-white64_2.png", 2),
    BIG("ship-white96.png", 3), //Cuando terminamos cerramos con ;
    BIG_2("ship-white96_2.png", 3), //Cuando terminamos cerramos con ;
    BIG_3("ship-white96_3.png", 3), //Cuando terminamos cerramos con ;
    BIGGEST("ship-white128.png", 4),  //Cuando terminamos cerramos con ;
    BIGGEST_2("ship-white128_2.png", 4),  //Cuando terminamos cerramos con ;
    BIGGEST_3("ship-white128_3.png", 4),  //Cuando terminamos cerramos con ;
    BIGGEST_4("ship-white128_4.png", 4);  //Cuando terminamos cerramos con ;

    //Campos tipo constante   
    private final String nombreImg; //Color de la madera
    private final int numCasillas; //Peso específico de la madera

    /**
     * Constructor. Al asignarle uno de los valores posibles a una variable del
     * tipo enumerado el constructor asigna automáticamente valores de los
     * campos
     */
    TipoBarco(String nombreImg, int numCasillas) {
        this.nombreImg = nombreImg;
        this.numCasillas = numCasillas;
    } //Cierre del constructor

    //Métodos de la clase tipo Enum
    public String getNombreImg() {
        return nombreImg;
    }


    public int getNumCasillas() {
        return numCasillas;
    }
} //Cierre del enum
