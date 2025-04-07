package com.player.gui.panels.selection;

import com.player.gui.ContentPanel;
import com.player.gui.dialogs.AddPlaylistDialog;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.*;

public class UtilityPanel extends JPanel {
    public UtilityPanel() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(PSP_WIDTH, UTILITY_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        this.init();
    }

    private void init() {
        JButton addPlaylist = new JButton("+");
        JButton removePlaylist = new JButton("-");

        this.add(addPlaylist);
        this.add(removePlaylist);
        addPlaylist.addActionListener(evt -> new AddPlaylistDialog());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getPsp().getWidth(), UTILITY_HEIGHT);
    }
}
