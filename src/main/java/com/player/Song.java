package com.player;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Song {
    private final String name;
    // private Image songImage;
    private Path songPath;

    public Song(String name, String path) {
        this.name = stripNameOfExtension(name);
        this.songPath = Paths.get(path);
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

    private String stripNameOfExtension(String name) {
        final int dotIndex = name.lastIndexOf(".");
        return (dotIndex == -1) ? name : name.substring(0, dotIndex);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
