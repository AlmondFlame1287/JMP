package com.player.gui.panels.selection;

import com.player.Playlist;
import com.player.Profile;
import com.player.gui.ContentPanel;
import com.player.utils.PlaylistCellRenderer;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;
import static com.player.utils.Constants.F_HEIGHT;

public class PlaylistSelectionPanel extends JPanel {
    private final DefaultListModel<Playlist> listModel = new DefaultListModel<>();

    public PlaylistSelectionPanel() {
        this.setPreferredSize(new Dimension(PSP_WIDTH, F_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 1));
        this.add(new ProfilePanel());
        this.add(new UtilityPanel());
    }

    public void loadPlaylists() {
        Profile profile = ContentPanel.getProfile();

        for(Playlist p : profile.getPlaylists()) {
            this.listModel.addElement(p);
        }

        JList<Playlist> list = new JList<Playlist>(this.listModel){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PlaylistSelectionPanel.this.getWidth(), PlaylistSelectionPanel.this.getHeight());
            }
        };

        list.setCellRenderer(new PlaylistCellRenderer());
        this.add(list);
    }

    public DefaultListModel<Playlist> getListModel() {
        return listModel;
    }
}
