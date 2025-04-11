package com.player.main;

import com.player.gui.MFrame;
import com.player.utils.DirectoryInitializer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DirectoryInitializer.initializeDirectories();
        SwingUtilities.invokeLater(MFrame::new);
    }
}