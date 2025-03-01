package com.player.gui.panels.view.song;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;
import static com.player.utils.Constants.F_HEIGHT;

public class SongViewPanel extends JPanel {
    public SongViewPanel() {
        this.setPreferredSize(new Dimension(SVP_WIDTH, F_HEIGHT));
        this.setBackground(Color.RED);
    }
}
