package com.player.gui.panels.view.playlist;

import com.player.gui.ContentPanel;
import com.player.gui.dialogs.SettingsDialog;
import com.player.gui.panels.selection.PlaylistSelectionPanel;
import com.player.utils.SettingsParser;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.ALBUM_HEIGHT;
import static com.player.utils.Constants.PVP_WIDTH;

public class AlbumViewPanel extends JPanel {
    private final JLabel albumTitle;

    public AlbumViewPanel() {
        this.setName("album");
        this.setBackground(SettingsParser.getColor(this.getName()));
        this.setPreferredSize(new Dimension(PVP_WIDTH, ALBUM_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        albumTitle = new JLabel();
        SettingsDialog.addPanelToComboBox(this);
        this.add(albumTitle);
    }

    public void paintAlbumName(ImageIcon img) {
        this.albumTitle.setIcon(img);
        this.albumTitle.setForeground(Color.WHITE);
        this.albumTitle.setText(PlaylistSelectionPanel.getSelectedValue().getName());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getPvp().getWidth(), ALBUM_HEIGHT);
    }
}
