package com.player.gui.panels.view.playlist;

import com.player.Playlist;
import com.player.Song;
import com.player.gui.panels.selection.PlaylistSelectionPanel;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;

public class PlaylistViewPanel extends JPanel {
    private DefaultListModel<Song> listModel;
    private AlbumViewPanel avp;

    public PlaylistViewPanel() {
        this.setPreferredSize(new Dimension(PVP_WIDTH, F_HEIGHT));
        this.setBackground(Color.BLUE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 1));

        this.avp = new AlbumViewPanel();
        this.add(this.avp);
        this.init();
    }

    private void init() {
        this.listModel = new DefaultListModel<>();

        JList<Song> songsToDisplay = new JList<>(this.listModel);

        this.add(songsToDisplay);
    }

    public void addSongsToModel() {
        Playlist selected = PlaylistSelectionPanel.getSelectedValue();
        System.out.println("Selected: " + selected);

        if(selected == null) return;

        for(Song s : selected.getSongs()) {
            System.out.println("Added: " + s);
            this.listModel.addElement(s);
        }
    }

    public AlbumViewPanel getAvp() {
        return avp;
    }
}
