/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.musics;

/**
 *
 * @author miaag
 */
public class main extends JFrame{
    
    
    Musicplay plays;
    Lyrics lyricss;
    MusicLine lines;
    ArrayList<musics> mus;
    public main(){
    
        
         super("Spotiphy");
         this.setUndecorated(true);
         setLayout(new BorderLayout());
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title   
//getSupportActionBar().hide(); //hide the title bar  
         
         ImageIcon cokelabel = new ImageIcon("src/image/bg.png");
        Image img = cokelabel.getImage();
        Image scale = img.getScaledInstance(1000, 1200, Image.SCALE_SMOOTH);
        ImageIcon scaled = new ImageIcon(scale);
         
         
         
       // setContentPane(new JLabel(scaled, JLabel.CENTER));
          setLayout(new GridLayout(1, 2));
           lines = new MusicLine();
        lines.setVisible(true);
     //   add(lines);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    plays = new Musicplay();
        plays.setVisible(true);
        plays.setSize(750,540);
        add(plays);
        
      //  lyricss = new Lyrics();
        //lyricss.setVisible(true);
        //add(lyricss);
        
       
        
        setSize(750,540);
        setVisible(true);
        
           plays.setPlayListener(new PlayListener() {
             @Override
             public void formEventOccurred(MusicPlayerEvent e) {
               String toshow = e.lyricss;
             
               lyricss.setLyrics(toshow);
          //  mus =  e.getMusics();
             }
       
    });
        
    }    
    
    
    
 
    public static void main(String[] args) {

    main frame = new main();

    }
}
