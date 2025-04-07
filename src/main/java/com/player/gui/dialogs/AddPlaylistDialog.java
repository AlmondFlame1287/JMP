package com.player.gui.dialogs;

import com.player.Playlist;
import com.player.Profile;
import com.player.gui.ContentPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

import static com.player.utils.Constants.F_HEIGHT;
import static com.player.utils.Constants.F_WIDTH;

public class AddPlaylistDialog extends JDialog {
    private File img;

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
        JButton playlistImageButton = new JButton("Set Image");

        playlistNameField.setPreferredSize(new Dimension(150, 30));

        this.add(playlistName);
        this.add(playlistNameField);
        this.add(addPlaylist);
        this.add(playlistImageButton);

        Profile p = ContentPanel.getProfile();

        addPlaylist.addActionListener(evt -> {
            Playlist playlist = new Playlist(playlistNameField.getText());
            playlist.setImageFile(img);
            p.addPlaylist(playlist);
        });

        playlistImageButton.addActionListener(evt -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures",
                    "jpg", "png");
            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(this);
            if(returnVal != JFileChooser.APPROVE_OPTION) return;

            this.img = chooser.getSelectedFile();
        });
    }
}