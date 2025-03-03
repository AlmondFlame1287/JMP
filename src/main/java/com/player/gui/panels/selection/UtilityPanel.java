package com.player.gui.panels.selection;

import com.player.Profile;
import com.player.gui.ContentPanel;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.*;

public class UtilityPanel extends JPanel {
    public UtilityPanel() {
        this.setBackground(Color.GREEN);
        this.setPreferredSize(new Dimension(PSP_WIDTH, UTILITY_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 2));
        this.init();
    }

    private void init() {
        JButton addPlaylist = new JButton("Add Playlist");
        JButton removePlaylist = new JButton("Remove Playlist");

        this.add(addPlaylist);
        this.add(removePlaylist);
        addPlaylist.addActionListener(evt -> new AddPlaylistDialog());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getPsp().getWidth(), UTILITY_HEIGHT);
    }

    private static class AddPlaylistDialog extends JDialog {
        public AddPlaylistDialog() {
            this.setTitle("Add Playlist");
            this.setSize(F_WIDTH / 2, F_HEIGHT / 2);
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setLayout(new FlowLayout());
            this.init();
            this.setVisible(true);
        }

        private void init() {
            JLabel playlistName = new JLabel("Playlist Name: ");
            JTextField playlistNameField = new JTextField();
            JButton addPlaylist = new JButton("Add Playlist");

            playlistNameField.setPreferredSize(new Dimension(150, 30));

            this.add(playlistName);
            this.add(playlistNameField);
            this.add(addPlaylist);

            Profile p = ContentPanel.getProfile();
            addPlaylist.addActionListener(evt -> p.addPlaylist(playlistNameField.getText()));
        }
    }

}
