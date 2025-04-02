package com.player.gui.panels.view.playlist;

import com.player.Playlist;
import com.player.gui.ContentPanel;
import com.player.gui.panels.selection.PlaylistSelectionPanel;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.ALBUM_HEIGHT;
import static com.player.utils.Constants.PVP_WIDTH;

public class AlbumViewPanel extends JPanel {
    public AlbumViewPanel() {
        this.setBackground(Color.MAGENTA);
        this.setPreferredSize(new Dimension(PVP_WIDTH, ALBUM_HEIGHT));
    }

    public void paintAlbumName() {
        Playlist selected = PlaylistSelectionPanel.getSelectedValue();

        this.getGraphics().drawString(selected.getName(), 0, this.getHeight() / 2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getPvp().getWidth(), ALBUM_HEIGHT);
    }
}
