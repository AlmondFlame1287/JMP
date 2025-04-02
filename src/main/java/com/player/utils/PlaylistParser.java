package com.player.utils;

import com.player.Playlist;
import com.player.Song;

import java.nio.file.Paths;

public final class PlaylistParser {
    private PlaylistParser() {}

    public static Playlist parseString(String str) {
        // TODO: Fix this
        String[] toParse = str.split(",");

        String title = toParse[0];
        Playlist playlist = new Playlist(title);

        for(int i = 1; i < toParse.length; i++) {
            String songName = Paths.get(toParse[i]).getFileName().toString();
            playlist.addSong(new Song(songName, toParse[i]));
        }

        return playlist;
    }
}
