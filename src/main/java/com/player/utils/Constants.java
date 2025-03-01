package com.player.utils;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Constants {
    public static final String APP_VER = "0.0.1a";
    public static final String APP_NAME = "JMP " + APP_VER;

    public static final int F_WIDTH = 1920; // Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    public static final int F_HEIGHT = 1080; // Toolkit.getDefaultToolkit().getScreenSize().height / 2;

    public static final Path PLAYLIST_PATH = Paths.get(System.getProperty("user.home") + "/JMPPlaylists").normalize();

    public static final int TOTAL_WIDTH_UNITS = 12;
    public static final int TOTAL_HEIGHT_UNITS = 10;

    public static final int SINGLE_WIDTH_UNIT = (F_WIDTH / TOTAL_WIDTH_UNITS);
    public static final int SINGLE_HEIGHT_UNIT = (F_HEIGHT / TOTAL_HEIGHT_UNITS);

    public static final int PSP_WIDTH_UNITS = 3;
    public static final int PVP_WIDTH_UNITS = 6;
    public static final int SVP_WIDTH_UNITS = 3;

    public static final int PSP_WIDTH = SINGLE_WIDTH_UNIT * PSP_WIDTH_UNITS;
    public static final int PVP_WIDTH = SINGLE_WIDTH_UNIT * PVP_WIDTH_UNITS;
    public static final int SVP_WIDTH = SINGLE_WIDTH_UNIT * SVP_WIDTH_UNITS;

    // PSP
    public static final int PROFILE_HEIGHT_UNITS = 2;

    public static final int PROFILE_HEIGHT = SINGLE_HEIGHT_UNIT * PROFILE_HEIGHT_UNITS;
    public static final int UTILITY_HEIGHT = SINGLE_HEIGHT_UNIT;

    // PVP
    public static final int ALBUM_HEIGHT_UNITS = 3;

    public static final int ALBUM_HEIGHT = SINGLE_HEIGHT_UNIT * ALBUM_HEIGHT_UNITS;
}
