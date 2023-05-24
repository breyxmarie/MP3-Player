/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author miaag
 */
public class musics {
    private String MusicId;
    private String FileID;
    private String PicFile;
    private String Lyrics;
    private String Genre;
    private String Year;
    private String Title;
    private String Artist;

    public musics(String MusicId, String FileID, String PicFile, String Lyrics, String Genre, String Year, String Title, String Artist){
        this.MusicId = MusicId;
        this.FileID = FileID;
        this.PicFile =PicFile;
        this.Lyrics =Lyrics;
        this.Genre = Genre;
        this.Year = Year;
        this.Title = Title;
        this.Artist = Artist;
    }
    
    public String getMusicId() {
        return MusicId;
    }

    public void setMusicId(String MusicId) {
        this.MusicId = MusicId;
    }

    public String getFileID() {
        return FileID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setFileID(String FileID) {
        this.FileID = FileID;
    }

    public String getPicFile() {
        return PicFile;
    }

    public void setPicFile(String PicFile) {
        this.PicFile = PicFile;
    }

    public String getLyrics() {
        return Lyrics;
    }

    public void setLyrics(String Lyrics) {
        this.Lyrics = Lyrics;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String Artist) {
        this.Artist = Artist;
    }
    
    
    
}
