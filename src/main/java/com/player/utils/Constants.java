package com.player.utils;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Constants {
    public static final String APP_VER = "0.0.1a";
    public static final String APP_NAME = "JMP " + APP_VER;

    public static final int F_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    public static final int F_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 2;

    public static final Path PLAYLIST_PATH = Paths.get(System.getProperty("user.home") + "/JMPPlaylists").normalize();

    // TODO: Find a better way to store these values
    public static final int TOTAL_WIDTH_UNITS = 9;

    public static final int PSP_WIDTH_UNITS = 3;
    public static final int PVP_WIDTH_UNITS = 4;
    public static final int SVP_WIDTH_UNITS = 2;

    public static final int PSP_WIDTH = (F_WIDTH / TOTAL_WIDTH_UNITS) * (TOTAL_WIDTH_UNITS - PVP_WIDTH_UNITS - SVP_WIDTH_UNITS);
    public static final int PVP_WIDTH = (F_WIDTH / TOTAL_WIDTH_UNITS) * (TOTAL_WIDTH_UNITS - PSP_WIDTH_UNITS - SVP_WIDTH_UNITS);
    public static final int SVP_WIDTH = (F_WIDTH / TOTAL_WIDTH_UNITS) * (TOTAL_WIDTH_UNITS - PSP_WIDTH_UNITS - PVP_WIDTH_UNITS);
}
