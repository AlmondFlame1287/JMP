package com.player.utils;

import com.player.gui.ContentPanel;
import com.player.gui.panels.selection.PlaylistSelectionPanel;

import java.io.*;
import java.util.*;
import java.awt.Color;

import static com.player.utils.Constants.SETTINGS_PATH;

/*
 * Settings available as of version 1.0.0
 * - Background color for every panel
 * - Add default directories where to scan for new songs automatically
 */
public class SettingsParser {
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

    }

    private static void parseBackgroundColors(String[] colorsHex) {
        final List<Color> colorList = new ArrayList<>(5);

        // Colors will be in this order:
        // PSP, PVP, AVP, SVP
        for (int i = 1; i < colorsHex.length; i++) {
            colorList.add(Color.decode(colorsHex[i]));
        }

        ContentPanel.getPsp().setBackground(colorList.get(0));
        PlaylistSelectionPanel.getPfpPanel().setBackground(colorList.get(0));
        PlaylistSelectionPanel.getUtilityPanel().setBackground(colorList.get(0));
        ContentPanel.getPvp().setBackground(colorList.get(1));
        ContentPanel.getPvp().getAvp().setBackground(colorList.get(2));
        ContentPanel.getSvp().setBackground(colorList.get(3));
    }
}
