package com.player.utils;

import com.player.Playlist;
import com.player.Song;

import java.io.*;
import java.util.*;
import java.awt.Color;
import java.util.stream.Collectors;

import static com.player.utils.Constants.SETTINGS_PATH;

/*
 * Settings available as of version 1.0.0
 * - Background color for almost every panel
 * - Add default directories where to scan for new songs automatically
 */
public class SettingsParser {
    private static final Playlist defaultPlaylist = new Playlist("Default");
    private static final List<Color> colorList = new ArrayList<>(5);

    private SettingsParser() {}

    public static void parseSettingsFile() {
        final File settingsFile = new File(SETTINGS_PATH + "/settings.txt");

        try(FileReader fr = new FileReader(settingsFile);
            BufferedReader br = new BufferedReader(fr)) {

            String line;
            String[] settings;
            while((line = br.readLine()) != null) {
                settings = line.split(",");

                switch (settings[0]) {
                    case "colors":
                        parseBackgroundColors(settings);
                        break;
                    case "default-dirs":
                        addDefaultSongScanDirs(settings);
                        break;
                    default:
                        break;
                }
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println("Settings file wasn't found:" + fnfe.getMessage() + ". Unable to load settings");
        } catch (IOException ioe) {
            System.err.println("Something went wrong: " + ioe.getMessage());
        }
    }

    private static void addDefaultSongScanDirs(String[] dirs) {
        // Songs found in these directories will be added to a playlist
        // named Default
        if(dirs.length == 1) return; // 1 because first element of dirs is "default-dirs"

        File dir;
        List<File> songFiles;

        for(int i = 1; i < dirs.length; i++) {
            dir = new File(dirs[i]);

            songFiles = Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                    .filter((file) -> file.getName().endsWith(".wav"))
                    .collect(Collectors.toList());

            songFiles.forEach(song -> defaultPlaylist.addSong(new Song(Song.stripNameOfExtension(song.getName()), song.getPath())));
        }
    }

    private static void parseBackgroundColors(String[] colorsHex) {
        // Colors will be in this order:
        // PSP, PVP, AVP, SVP
        for (int i = 1; i < colorsHex.length; i++) {
            colorList.add(Color.decode(colorsHex[i]));
        }
    }

    public static Color getColor(String panelName) {
        if (colorList.isEmpty()) return null;

        switch (panelName) {
            case "psp":
            case "profile":
            case "utility":
                return colorList.get(0);
            case "pvp":
                return colorList.get(1);
            case "avp":
                return colorList.get(2);
            case "svp":
                return colorList.get(3);
            default:
                return null;
        }
    }

    public static Playlist getDefaultPlaylist() {
        return defaultPlaylist;
    }
}
