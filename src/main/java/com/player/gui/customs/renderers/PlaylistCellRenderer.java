package com.player.gui.customs.renderers;


import com.player.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlaylistCellRenderer extends DefaultListCellRenderer {
    // Cache loaded images to avoid reloading and re-triggering SwingWorker
    private final Map<File, ImageIcon> imageCache = new ConcurrentHashMap<>();
    private final ImageIcon placeholderIcon = new ImageIcon(new BufferedImage(50, 50, BufferedImage.TYPE_USHORT_555_RGB));

    @Override
    public Component getListCellRendererComponent(JList<?> list,
                                                  Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus)
    {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Playlist playlist = (Playlist) value;

        File imageFile = playlist.getImageFile();
        label.setText(playlist.getName());
        label.setOpaque(true);

        if(isSelected) {
            label.setBackground(Color.DARK_GRAY);
            label.setForeground(Color.WHITE);
            label.setBorder(BorderFactory.createEtchedBorder());
        }

        if(imageFile == null) {
            label.setIcon(placeholderIcon);
            return label;
        }

        ImageIcon cachedIcon = imageCache.get(imageFile);
        if (cachedIcon != null) {
            label.setIcon(cachedIcon);
        } else {
            label.setIcon(placeholderIcon); // Temporary icon
            loadImageAsync(imageFile, list, index);
        }



        return label;
    }

    private void loadImageAsync(File imageFile, JList<?> list, int index) {
        new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                // Load and scale image
                ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
                Image scaled = icon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST);
                return new ImageIcon(scaled);
            }

            @Override
            protected void done() {
                try {
                    ImageIcon icon = get();
                    imageCache.put(imageFile, icon);
                    // Only repaint this specific cell
                    Rectangle rect = list.getCellBounds(index, index);
                    if (rect != null) {
                        list.repaint(rect);
                    }
                } catch (Exception e) {
                    System.err.println("Something went wrong in the worker thread: " + e.getMessage());
                }
            }
        }.execute();
    }
}