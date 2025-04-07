package com.player.utils;

import com.player.Playlist;
import com.player.Song;

import java.io.File;
import java.nio.file.Paths;

public final class PlaylistParser {
    private PlaylistParser() {}

    public static Playlist parseString(String str) {
        String[] toParse = str.split(",");

        String title = toParse[0];
        String imageFilePath = toParse[1];
        Playlist playlist = new Playlist(title);
        playlist.setImageFile(new File(imageFilePath));

        for(int i = 2; i < toParse.length; i++) {
            String songName = Paths.get(toParse[i]).getFileName().toString();
            playlist.addSong(new Song(songName, toParse[i]));
        }

        return playlist;
    }
}
