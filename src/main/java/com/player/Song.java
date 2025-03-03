package com.player;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Song {
    private String name;
    // private Image songImage;
    private Path songPath;

    public Song(String name, String path) {
        this.name = name;
        this.songPath = Paths.get(path); // TODO: Use paths instead of string in production
    }

    public Song(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Path getSongPath() {
        return songPath;
    }
}
