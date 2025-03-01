package com.player.gui.panels.selection;

import com.player.gui.ContentPanel;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.PROFILE_HEIGHT;

public class ProfilePanel extends JPanel {
    public ProfilePanel() {
        this.setBackground(Color.CYAN);
        this.setBorder(BorderFactory.createLoweredBevelBorder());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getInstance().getPsp().getWidth(), PROFILE_HEIGHT);
    }
}
