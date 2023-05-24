/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import model.musics;

/**
 *
 * @author Jamie Beatrice
 */
public class MusicPlayerEvent extends EventObject{
    
   // static MusicPlayerEvent player = new MusicPlayerEvent();
    ArrayList<musics> musics;
    String lyricss;

    public String getLyricss() {
        return lyricss;
    }

    public void setLyricss(String lyricss) {
        this.lyricss = lyricss;
    }
    
    
    
    
    public ArrayList<musics> getMusics() {
        return musics;
    }

    public void setMusics(ArrayList<musics> musics) {
        this.musics = musics;
    }
  
    
    
    public MusicPlayerEvent(Object o){
        super(o);
    }
    
    
    public MusicPlayerEvent(Object source, String lyricss){
        super(source);
        this.lyricss = lyricss;
        
    }
   // public static MusicPlayerEvent getInstance(){
   //     return player;
   // }
    
    

}
