package com.player;

import java.util.List;

public class Playlist {
    // Initially loads the name of the playlist
    // then when clicked on, loads in the songs from disk

    private String name;
    // private Image playlistImage;
    private List<Song> songs;
    private int songCount;

    public Playlist(String name) {
        this.name = name;
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


    public List<Song> getSongs() {
        return songs;
    }

    public int getSongCount() {
        return songCount;
    }
}
