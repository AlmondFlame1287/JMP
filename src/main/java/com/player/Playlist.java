package com.player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    // Initially loads the name of the playlist
    // then when clicked on, loads in the songs from disk

    private String name;
    private File playlistImageFile;
    private final List<Song> songs;
//    private int songCount;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.name + ",");

        str.append(this.playlistImageFile)
                .append(",");

        for(Song s : songs) {
            // playlistName,imageFilePath,songxPath,songyPath,songzPath...
            str.append(s.getSongPath())
                    .append(",");
        }

        return str.toString();
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getName() {
        return name;
    }


    public void addSong(Song s) {
        this.songs.add(s);
    }

    public Song getSongAt(int index) {
        return songs.get(index);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setImageFile(File imgFile) {
        this.playlistImageFile = imgFile;
    }

    public File getImageFile() {
        return this.playlistImageFile;
    }

//    public List<Song> getSongs() {
//        return songs;
//    }

//    public int getSongCount() {
//        return songCount;
//    }
}
