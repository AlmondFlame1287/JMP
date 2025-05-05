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
    private static final File defaultDirFile = new File(SETTINGS_PATH + "/dirs.txt");
    private static final File personalizationFile = new File(SETTINGS_PATH + "/colors.properties");
    private static final Map<String, Color> panelToColorMap = new HashMap<>();
    private static final List<String> defaultDirectories = new ArrayList<>();

    private SettingsParser() {}

    private static void parseDefaultDirs() {
        try(FileReader fr = new FileReader(defaultDirFile);
            BufferedReader br = new BufferedReader(fr)) {

            String line;
            while((line = br.readLine()) != null) {
                defaultDirectories.add(line);
            }

            addSongsToDefaultPlaylist();
        } catch (IOException ioe) {
            System.err.println("Something went wrong with parsing the default dirs: " + ioe.getMessage());
        }
    }

    private static void parseColors() {
        final Properties p = new Properties();

        try(FileReader fr = new FileReader(personalizationFile);
            BufferedReader br = new BufferedReader(fr)) {
            p.load(br);

            final Set<?> set = p.entrySet();

            for (Object o : set) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) o;
                final String panelName = (String) entry.getKey();
                final String color = "#" + entry.getValue();

                System.out.println("Color: " + color);
                panelToColorMap.put(panelName, Color.decode(color));
            }
        } catch (IOException ioe) {
            System.err.println("Something went wrong with parsing the settings file: " + ioe.getMessage());
        }
    }

    public static void addSongsToDefaultPlaylist() {
        File dir;
        List<File> songFiles;

        for(String path : defaultDirectories) {
            dir = new File(path);

            songFiles = Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                    .filter((file) -> file.getName().endsWith(".wav"))
                    .collect(Collectors.toList());

            songFiles.forEach(song -> defaultPlaylist.addSong(new Song(Song.stripNameOfExtension(song.getName()), song.getPath())));
        }
    }

    public static Playlist getDefaultPlaylist() {
        return defaultPlaylist;
    }

    private static void saveDefaultDirsToFile() {
        if(!defaultDirFile.exists()) {
            try { defaultDirFile.createNewFile(); }
            catch (IOException ioe) { System.err.println("Something went wrong with creating the settings file: " + ioe.getMessage()); }
        }

        try(FileWriter fw = new FileWriter(defaultDirFile, true);
            BufferedWriter bw = new BufferedWriter(fw)) {

            for(String dir : defaultDirectories)
                bw.write(dir + "\n");

        } catch (IOException ioe) {
            System.err.println("Something went wrong with writing to settings file: " + ioe.getMessage());
        }
    }

    private static void savePanelColorsToFile() {
        final Properties p = new Properties();

        try(FileWriter fw = new FileWriter(personalizationFile);
            BufferedWriter bw = new BufferedWriter(fw)) {

            if(panelToColorMap.isEmpty()) {
                setColors(p);
            } else {
                for(Map.Entry<String, Color> entry : panelToColorMap.entrySet()) {
                    p.setProperty(entry.getKey(), Integer.toHexString(entry.getValue().getRGB()));
                }
            }

            p.store(bw, "");
        } catch (IOException ioe) {
            System.err.println("Something went wrong saving the colors to file: " + ioe.getMessage());
        }
    }

    private static void setColors(Properties p) throws IOException {
        final String blackToHex = "000000";

        p.setProperty("profile", blackToHex);
        p.setProperty("utility", blackToHex);
        p.setProperty("album", "2b2929");
        p.setProperty("song", blackToHex);
    }

    public static void saveSettings() {
        saveDefaultDirsToFile();
        savePanelColorsToFile();
    }

    public static Color getColor(String panelName) {
        return panelToColorMap.get(panelName);
    }

    public static void addScanDir(String dir) {
        defaultDirectories.add(dir);
    }

    public static void parseSettings() {
        parseDefaultDirs();
        parseColors();
    }
}
