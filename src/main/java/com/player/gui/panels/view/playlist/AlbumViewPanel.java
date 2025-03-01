package com.player.gui.panels.view.playlist;

import com.player.gui.ContentPanel;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.ALBUM_HEIGHT;

public class AlbumViewPanel extends JPanel {
    public AlbumViewPanel() {
        this.setBackground(Color.MAGENTA);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getInstance().getPvp().getWidth(), ALBUM_HEIGHT);
    }
}
