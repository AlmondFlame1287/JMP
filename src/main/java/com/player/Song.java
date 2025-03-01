package com.player;

import java.nio.file.Path;

public final class Song {
    private String name;
    // private Image songImage;
    private Path songPath;

    public String getName() {
        return name;
    }

    public void setSongPath(Path songPath) {
        this.songPath = songPath;
    }

    public Path getSongPath() {
        return songPath;
    }
}
