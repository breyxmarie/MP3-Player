/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group7_mp3_player;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author miaag
 */
public class musicPlayer {
    
    static musicPlayer player = new musicPlayer();
    
    static Clip clip;
    
    private musicPlayer(){
        
    }
    
    
    public static musicPlayer getInstance(){
        return player;
    }
    
    public static void loadMusic(String filepath){
        try
        {
            File musicPath = new File(filepath);
            
            
             /*
            AudioSystem stream = AudioSystem.getAudioInputStream(new URL(
        "http://hostname/audiofile"));

    AudioFormat format = stream.getFormat();
    if (format.getEncoding() == AudioFormat.Encoding.ULAW) {
    } else if (format.getEncoding() == AudioFormat.Encoding.ULAW) {
    }

  }
            
            */
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                System.out.println("Initialised");
                
                
            }
            else{
                System.out.println("none");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
