package com.player.gui.panels;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;
import static com.player.utils.Constants.F_HEIGHT;

public class PlaylistSelectionPanel extends JPanel {
    public PlaylistSelectionPanel() {
        this.setPreferredSize(new Dimension(PSP_WIDTH, F_HEIGHT));
        this.setBackground(Color.BLACK);
    }
}
