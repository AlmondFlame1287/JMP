package com.player.gui.panels.selection;

import com.player.gui.ContentPanel;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.*;

public class UtilityPanel extends JPanel {
    public UtilityPanel() {
        this.setBackground(Color.GREEN);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getInstance().getPsp().getWidth(), UTILITY_HEIGHT);
    }
}
