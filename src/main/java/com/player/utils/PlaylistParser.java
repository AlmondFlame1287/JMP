package com.player.utils;

import com.player.Playlist;
import com.player.Song;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PlaylistParser {
    private PlaylistParser() {}

    public static Playlist parseString(String str) {
        Pattern songPattern = Pattern.compile("([A-Za-z]+):([^|]+)");
        Pattern playlistNamePattern = Pattern.compile("([^=]+)=");

        Matcher songMatcher = songPattern.matcher(str);
        Matcher playlistNameMatcher = playlistNamePattern.matcher(str);

        String playlistName = null;
        if(playlistNameMatcher.find()) {
            playlistName = playlistNameMatcher.group(1);
        }

        Playlist p = new Playlist();
        p.setName(playlistName);

        while(songMatcher.find()) {
            String songName = songMatcher.group(1);
            String songPath = songMatcher.group(2);

            p.addSong(new Song(songName, songPath));
        }

        return p;
    }
}
