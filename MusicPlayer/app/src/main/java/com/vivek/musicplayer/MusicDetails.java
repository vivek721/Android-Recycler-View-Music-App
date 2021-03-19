package com.vivek.musicplayer;

public class MusicDetails {
    String songName;                //song name
    String artistName;              //artist name
    String artistWiki;              //artist wikipedia page url
    String songWiki;                //song wikipedia page url
    Integer image;                  //image location
    String songURL;                 //song url

    //constructor
    public MusicDetails(String songName, String artistName, String artistWiki, String songWiki, Integer image, String songURL) {
        this.songName = songName;
        this.artistName = artistName;
        this.artistWiki = artistWiki;
        this.songWiki = songWiki;
        this.image = image;
        this.songURL = songURL;
    }

    // Getters for song details
    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistWiki() {
        return artistWiki;
    }

    public String getSongWiki() {
        return songWiki;
    }

    public String getSongURL() {
        return songURL;
    }

    public Integer getImageId() {
        return image;
    }
}
