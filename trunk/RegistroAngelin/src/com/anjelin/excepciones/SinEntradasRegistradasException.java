/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.excepciones;

/**
 *
 * @author Admon
 */
public class SinEntradasRegistradasException extends Exception {

    public SinEntradasRegistradasException() {
        super("No cuenta con un registro de entrada válido!");
    }
    
}
