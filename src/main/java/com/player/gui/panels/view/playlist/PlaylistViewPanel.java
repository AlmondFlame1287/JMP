package com.player.gui.panels.view.playlist;

import com.player.Playlist;
import com.player.Song;
import com.player.gui.ContentPanel;
import com.player.gui.customs.renderers.SongCellRenderer;
import com.player.gui.dialogs.SettingsDialog;
import com.player.gui.panels.selection.PlaylistSelectionPanel;
import com.player.gui.panels.view.song.SongViewPanel;
import com.player.utils.SettingsParser;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;

public class PlaylistViewPanel extends JPanel {
    private DefaultListModel<Song> listModel;
    private final AlbumViewPanel avp;
    private JList<Song> songsToDisplay;

    public PlaylistViewPanel() {
        this.avp = new AlbumViewPanel();
        this.setPreferredSize(new Dimension(PVP_WIDTH, F_HEIGHT - avp.getHeight()));
        this.setBackground(SettingsParser.getColor("album"));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 1));
        SettingsDialog.addPanelToComboBox(this);
        this.add(this.avp);
        this.init();
    }

    private void init() {
        this.listModel = new DefaultListModel<>();
        this.songsToDisplay = getSongJList();

        songsToDisplay.addListSelectionListener(evt -> {
            if(evt.getValueIsAdjusting()) return;
            final SongViewPanel svp = ContentPanel.getSvp();

            setPreviousAndNextSongs(svp);
        });

        this.add(songsToDisplay);
    }

    private void setPreviousAndNextSongs(SongViewPanel svp) {
        final int selectedIndx = songsToDisplay.getSelectedIndex();
        final int firstIndx = 0;
        final int lastIndx = this.listModel.getSize();

        final int prevIndex = (selectedIndx - 1) >= 0 ? selectedIndx - 1 : selectedIndx;
        final int nextIndex = (selectedIndx + 1) < lastIndx ? selectedIndx + 1 : firstIndx;

        svp.setPrevious(this.listModel.get(prevIndex), prevIndex);
        svp.setToPlay(songsToDisplay.getSelectedValue(), songsToDisplay.getSelectedIndex());
        svp.setNext(this.listModel.get(nextIndex), nextIndex);
    }

    public JList<Song> getSongsToDisplay() {
        return songsToDisplay;
    }

    private JList<Song> getSongJList() {
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
        return songsToDisplay;
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
