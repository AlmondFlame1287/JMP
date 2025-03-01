package com.player.gui.panels.selection;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;
import static com.player.utils.Constants.F_HEIGHT;

public class PlaylistSelectionPanel extends JPanel {
    public PlaylistSelectionPanel() {
        this.setPreferredSize(new Dimension(PSP_WIDTH, F_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 1));
        this.add(new ProfilePanel());
        this.add(new UtilityPanel());
        this.testAdd();
    }

    private void testAdd() {
        // TODO: Replace JList with custom implementation
        this.add(new JList<String>(new String[]{"A", "B", "C"}){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PlaylistSelectionPanel.this.getWidth(), PlaylistSelectionPanel.this.getHeight());
            }
        });
    }
}
