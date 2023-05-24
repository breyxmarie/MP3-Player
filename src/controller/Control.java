/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.musics;
import model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//import model.Database;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miaag
 */
public class Control {
    
    //import javax.sound.sampled.Clip
    static Control control = new Control();
    
    static Clip music;
    static int musicindex = 0;    ///// current queue of the music playing
    static int musiccount;      /////// total number of songs in the database 
    static String filepath;     ///// directory of the music file
    static String musicname;    ///// music name to be displayed
    static long clipTimePosition;
    String song;
    Scanner output;              ////// to print the lyrics
    File txtfile;                 ////// to print the lyrics
    static boolean isPlaying = true;
    static boolean isLooping = false;
    static boolean starting = false;
    static boolean newSong = false;
    Database datas = new Database();
    private ArrayList<musics> musicss;
    static FloatControl fc;
    static float previousVolume = 0;
    static float currentVolume = 0;
    
    
    public Control(){
     //    datas.getConnection();
        musicss = datas.ShowRec();
       // musicss.get(1);
        filepath = musicss.get(musicindex).getFileID();
      //  loadMusic(filepath);
        musiccount = datas.musiccount() - 1;
       // fc = (FloatControl)music.getControl(FloatControl.Type.MASTER_GAIN);
        
        try{
         //  fc = (FloatControl)music.getControl(FloatControl.Type.MASTER_GAIN);
        }
        catch(Exception e){
            
        }
    }
    
    
    
    /*Finding the music file and loading it into memory so when we want to play, we could just play the music without having it to load 
      again*/
    public static void load (String filepath){
        try{
            //import java.io.File
            File musicPath = new File(filepath);
            
           

            if(musicPath.exists()){
                //import javax.sound.sampled.AudioInputStream;
                //import javax.sound.sampled.AudioSystem;
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                music = AudioSystem.getClip();
                music.open(audioInput);
                System.out.println("Initialised");
                fc = (FloatControl)music.getControl(FloatControl.Type.MASTER_GAIN);
            }
            else{
                System.out.println("none");
            }
        }
        
        catch(Exception e){
            System.out.println("Yes");
        }
    }
    
    
    public ArrayList<musics> allMusics(){
        return musicss;
    }
    
    public void playmusic(){
        filepath = musicss.get(musicindex).getFileID();
       //  player.loadMusic(filepath);
        
        if(starting == false){
            load(filepath);
        music.setMicrosecondPosition(0);
        clipTimePosition = 0;
        music.start();
        starting = true;
       // isPlaying = !isPlaying;
       isPlaying = true;
        }
        
        else{
        if(isPlaying == true && newSong == false){
            
            clipTimePosition = music.getMicrosecondPosition();
            music.stop();
         //   pausebtn.setText("Resume");
         isPlaying = !isPlaying;
       //  isPlaying = false;
        }
        else if(isPlaying == true && newSong == true){
            music.stop();
            load(filepath);
            music.setMicrosecondPosition(0);
            music.start();
           // pausebtn.setText("Pause");
            newSong = false;
        }  
        else {
          //  load(filepath);
            music.setMicrosecondPosition(clipTimePosition);
            music.start();
           // pausebtn.setText("Pause");
           isPlaying = !isPlaying;
            
        }
        
        
        }
    }

    public static boolean isNewSong() {
        return newSong;
    }

    public static void setNewSong(boolean newSong) {
        Control.newSong = newSong;
    }
    
    
    
    
    public void PauseResumeMusic(){
        if(isPlaying){
            clipTimePosition = music.getMicrosecondPosition();
            music.stop();
          
        }
        else{
            music.setMicrosecondPosition(clipTimePosition);
            music.start();
        }
        isPlaying = !isPlaying;
    }
    
    public void NextMusic(){
        music.stop();
        
        if(musicindex == musiccount){
            musicindex = 0;
        }
        else{
            musicindex++;
        }
        
        filepath = musicss.get(musicindex).getFileID();
        load(filepath);
        music.setMicrosecondPosition(0);
        clipTimePosition = 0;
        music.start();
        filepath = musicss.get(musicindex).getFileID();
      
    }
    
    public void BackMusic(){
         music.stop();
        
        if(musicindex == 0){
            musicindex = musiccount;
        }
        else{
            musicindex--;
        }
        
        filepath = musicss.get(musicindex).getFileID();
        load(filepath);
        music.setMicrosecondPosition(0);
        clipTimePosition = 0;
        music.start();
        filepath = musicss.get(musicindex).getFileID();
    }

    //////////////////////////////////
    //// to get the lyrics
    
    public String getLyrics(){
        String lyricscontent = " ";
        txtfile = new File(musicss.get(musicindex).getLyrics()); 
    
            try{
            output = new Scanner(txtfile);
            while(output.hasNext()){
             lyricscontent = lyricscontent + output.nextLine() + "\n";
            }
        } catch (FileNotFoundException sad) {
            System.out.println("File not found.");
        }
      
        return lyricscontent;
    }
    // return file name of album picture
    public String PicFile(){
       String pics = musicss.get(musicindex).getPicFile() ;
        
        return pics;
    }
    ////////////////////////////////////
    
    // return title of the song
    public String Title(){
        
        return musicss.get(musicindex).getTitle();
    }
    //////////////////////////////////////
    
      // return title of the song
    public String Genre(){
        
        return musicss.get(musicindex).getGenre();
    }
    /////////////////////////////////////////////////
      // return title of the song
    public String Years(){
        
        return musicss.get(musicindex).getYear();
    }
    
    /////////////////////////////////////
     public String Artistss(){
        
        return musicss.get(musicindex).getArtist();
    }
    //////////////////// return lyrics
     
    ///////////////////////////////////
   /////////////////// getter and setter for musicindex and musiccount 
    ////////////////////// to get the value of the current music index and how many songs there are
    public static int getMusicindex() {
        return musicindex;
    }

    public static void setMusicindex(int musicindex) {
        Control.musicindex = musicindex;
    }

    public static int getMusiccount() {
        return musiccount;
    }

    public static void setMusiccount(int musiccount) {
        Control.musiccount = musiccount;
    }
    
    ////////////////////// Adjust Volume 
    public void changeVolume(float currentVolume){
        fc.setValue(currentVolume);
    }
    //////////////////////////////// 
    
    public void volumeUp(){
        currentVolume += 1.0f;
        if(currentVolume > 6.0f){
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }
    
    public void volumeDown(){
        currentVolume -= 1.0f;
        if(currentVolume < -80.0f){
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }
    
    public void volumeMute(){
        
    }

    public static boolean isStarting() {
        return starting;
    }

    public static void setStarting(boolean starting) {
        Control.starting = starting;
    }
}

