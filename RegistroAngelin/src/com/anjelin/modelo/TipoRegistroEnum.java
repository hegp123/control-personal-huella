/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.modelo;

/**
 *
 * @author Admon
 */
public enum TipoRegistroEnum {
    
    ENTRADA(1), SALIDA(2);
    
    private int valor;
    private String cadena;

    private TipoRegistroEnum(int valor) {
        this.valor = valor;
        switch (valor){
            case 1:
                this.cadena = "Entrada";
                break;
            case 2: 
                this.cadena = "Salida";
                break;                
        }
    }

    public int getValor() {
        return valor;
    }

    public String getCadena() {
        return cadena;
    }
   
    
}
