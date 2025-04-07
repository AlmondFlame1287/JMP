package com.player.utils;

import com.player.Playlist;

import javax.swing.*;
import java.awt.*;

public class PlaylistCellRenderer extends DefaultListCellRenderer {
    public PlaylistCellRenderer() {}

    // TODO: Make it better looking

    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Playlist p = (Playlist) value;
        ImageIcon icon = new ImageIcon(ImageLoader.loadImage(p.getImageFile(), 50, 50));
        this.setIcon(icon);
        icon.getImage().flush();
        this.setText(p.getName());
        return this;
    }
}
