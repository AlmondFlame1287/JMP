package com.player.main;

import com.player.gui.MFrame;
import com.player.utils.DirectoryInitializer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        DirectoryInitializer.initializeDirectories();
        SwingUtilities.invokeLater(MFrame::new);
    }

    public static File[] getSelectedFilesFromJFC(String filterDesc, String... extensions) {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(filterDesc, extensions);
        jfc.setFileFilter(filter);
        jfc.setMultiSelectionEnabled(true);

        final int result = jfc.showOpenDialog(null);
        if(result != JFileChooser.APPROVE_OPTION) return null;

        return jfc.getSelectedFiles();
    }
}