/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udistrital.batallanaval.enums;

// Clase que contiene los tipos de barco 
public enum Comando {
    CONECTAR("CON"), //Separamos con comas
    LISTO("LIS"),
    ATACAR("ATK"),
    OK("OK"),
    NK("NK");

    private final String nombre;

    Comando(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
}
