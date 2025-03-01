package com.player.gui;

import com.player.gui.panels.PlaylistSelectionPanel;
import com.player.gui.panels.PlaylistViewPanel;
import com.player.gui.panels.SongViewPanel;

import javax.swing.*;

public class ContentPanel extends JPanel {
    public ContentPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(new PlaylistSelectionPanel());
        this.add(new PlaylistViewPanel());
        this.add(new SongViewPanel());
    }
}
