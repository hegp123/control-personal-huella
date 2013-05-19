/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Admon
 */
public class TocarSonido implements Runnable{
    
    private String rutaArchivoAudio;

    public TocarSonido(String rutaArchivoAudio) {
        this.rutaArchivoAudio = rutaArchivoAudio;
    }
    
    @Override
    public void run() {

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(TocarSonido.class.getClassLoader().getResourceAsStream(this.rutaArchivoAudio));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
