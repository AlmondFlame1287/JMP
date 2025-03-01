package com.player.main;

import com.player.gui.MFrame;

import javax.swing.*;

import java.io.File;

import static com.player.utils.Constants.PLAYLIST_PATH;

public class Main {
    public static void main(String[] args) {
        final File playlistPath = PLAYLIST_PATH.toFile();

        if(!playlistPath.exists()) {
            playlistPath.mkdirs();
        }

        SwingUtilities.invokeLater(MFrame::new);
    }
}