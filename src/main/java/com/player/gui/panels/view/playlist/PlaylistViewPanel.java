package com.player.gui.panels.view.playlist;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;

public class PlaylistViewPanel extends JPanel {
    public PlaylistViewPanel() {
        this.setPreferredSize(new Dimension(PVP_WIDTH, F_HEIGHT));
        this.setBackground(Color.BLUE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 1));
        this.add(new AlbumViewPanel());
    }
}
