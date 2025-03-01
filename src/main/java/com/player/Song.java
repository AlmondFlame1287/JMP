package com.player;

import java.nio.file.Path;

public final class Song {
    private String name;
    // private Image songImage;
    private String songPath;

    public Song(String name, String path) {
        this.name = name;
        this.songPath = path; // TODO: Use paths instead of string in production
    }

    public Song(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSongPath() {
        return songPath;
    }

    //    public void setSongPath(Path songPath) {
//        this.songPath = songPath;
//    }
//
//    public Path getSongPath() {
//        return songPath;
//    }
}
