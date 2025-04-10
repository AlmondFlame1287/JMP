package com.player.gui.panels.selection;

import com.player.Playlist;
import com.player.Profile;
import com.player.gui.ContentPanel;
import com.player.gui.panels.view.playlist.PlaylistViewPanel;
import com.player.gui.customs.renderers.PlaylistCellRenderer;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;
import static com.player.utils.Constants.F_HEIGHT;

public class PlaylistSelectionPanel extends JPanel {
    private final DefaultListModel<Playlist> listModel = new DefaultListModel<>();
    private JList<Playlist> list;
    private static Playlist selectedValue;

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

        list = new JList<Playlist>(this.listModel){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                this.setBackground(Color.BLACK);
                this.setForeground(Color.WHITE);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PlaylistSelectionPanel.this.getWidth(), PlaylistSelectionPanel.this.getHeight());
            }
        };

        list.addListSelectionListener(evt -> {
            if(evt.getValueIsAdjusting()) return;

            selectedValue = this.list.getSelectedValue();
            PlaylistViewPanel pvp = ContentPanel.getPvp();
            pvp.addSongsToModel();
            pvp.getAvp().paintAlbumName(PlaylistCellRenderer.getCachedImage(list.getSelectedValue().getImageFile()));
        });
        list.setCellRenderer(new PlaylistCellRenderer());
        this.add(list);
    }

    public DefaultListModel<Playlist> getListModel() {
        return listModel;
    }

    public static Playlist getSelectedValue() {
        return selectedValue;
    }
}
