package com.player.gui.panels.view.playlist;

import com.player.Playlist;
import com.player.Song;
import com.player.gui.ContentPanel;
import com.player.gui.customs.renderers.SongCellRenderer;
import com.player.gui.panels.selection.PlaylistSelectionPanel;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;

public class PlaylistViewPanel extends JPanel {
    private DefaultListModel<Song> listModel;
    private final AlbumViewPanel avp;

    public PlaylistViewPanel() {
        this.avp = new AlbumViewPanel();
        this.setPreferredSize(new Dimension(PVP_WIDTH, F_HEIGHT - avp.getHeight()));
        this.setBackground(Color.decode("#141414"));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 1));

        this.add(this.avp);
        this.init();
    }

    private void init() {
        this.listModel = new DefaultListModel<>();

        JList<Song> songsToDisplay = new JList<Song>(this.listModel) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                this.setBackground(Color.decode("#141414"));
                this.setForeground(Color.WHITE);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PlaylistViewPanel.this.getWidth(), PlaylistViewPanel.this.getHeight());
            }
        };

        songsToDisplay.setCellRenderer(new SongCellRenderer());
        songsToDisplay.addListSelectionListener(evt -> ContentPanel.getSvp().setToPlay(songsToDisplay.getSelectedValue()));

        this.add(songsToDisplay);
    }

    public void addSongsToModel() {
        Playlist selected = PlaylistSelectionPanel.getSelectedValue();
        if(selected == null) return;

        System.out.println("Selected playlist " + selected.getName() + " with songs:");
        selected.getSongs().forEach(song -> System.out.println("| " + song.getName()));

        if(!this.listModel.isEmpty())
            this.listModel.removeAllElements();

        for(Song s : selected.getSongs()) {
            this.listModel.addElement(s);
        }
    }

    public AlbumViewPanel getAvp() {
        return avp;
    }
}
