package com.player;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    // Initially loads the name of the playlist
    // then when clicked on, loads in the songs from disk

    private String name;
    // private Image playlistImage;
    private final List<Song> songs;
//    private int songCount;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.name + "=");
        for(Song s : songs) {
            str.append(s.getName())
                    .append(":")
                    .append(s.getSongPath())
                    .append("|");
        }

        return str.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void addSong(Song s) {
        this.songs.add(s);
    }


//    public List<Song> getSongs() {
//        return songs;
//    }

//    public int getSongCount() {
//        return songCount;
//    }
}
