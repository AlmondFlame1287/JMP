package com.player.gui.dialogs;

import com.player.Playlist;
import com.player.Song;
import com.player.gui.ContentPanel;
import com.player.gui.customs.CustomTextField;
import com.player.gui.customs.TransparentButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

import static com.player.main.Main.getSelectedFilesFromJFC;
import static com.player.utils.Constants.F_HEIGHT;
import static com.player.utils.Constants.F_WIDTH;

public class AddPlaylistDialog extends JDialog {
    private File img;
    private CustomTextField playlistNameField;
    private DefaultListModel<File> model;

    public AddPlaylistDialog() {
        this.setTitle("Add Playlist");
        this.setSize(F_WIDTH / 2, F_HEIGHT / 2);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new FlowLayout());
        this.init();
        this.setVisible(true);
    }

    private void init() {
        JLabel playlistName = new JLabel("Playlist Name: ");
        playlistName.setForeground(Color.WHITE);
        this.playlistNameField = new CustomTextField(20);
        this.model = new DefaultListModel<>();
        JList<File> songsToAdd = new JList<>();

        TransparentButton addPlaylist = new TransparentButton("Add Playlist");
        TransparentButton playlistImageButton = new TransparentButton("Set Image");
        TransparentButton addSongs = new TransparentButton("Add songs");


        songsToAdd.setModel(this.model);

        this.playlistNameField.setPreferredSize(new Dimension(150, 30));

        this.add(playlistName);
        this.add(playlistNameField);
        this.add(addPlaylist);
        this.add(addSongs);
        this.add(playlistImageButton);
        this.add(songsToAdd);

        addPlaylist.addActionListener(evt -> this.onAddPlaylistClicked());
        playlistImageButton.addActionListener(evt -> this.onPlaylistImageClicked());
        addSongs.addActionListener(evt -> this.onAddSongsClicked());
    }

    private void onAddPlaylistClicked() {
        Playlist playlist = new Playlist(this.playlistNameField.getText());
        playlist.setImageFile(img);

        for (int i = 0; i < this.model.getSize(); i++) {
            File f = this.model.getElementAt(i);
            playlist.addSong(new Song(Song.stripNameOfExtension(f.getName()), f.getPath()));
        }

        ContentPanel.getProfile().addPlaylist(playlist);
    }

    private void onPlaylistImageClicked() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures",
                "jpg", "png");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(this);
        if(returnVal != JFileChooser.APPROVE_OPTION) return;

        this.img = chooser.getSelectedFile();
    }

    private void onAddSongsClicked() {
        final File[] files = getSelectedFilesFromJFC("Wav files", "wav");
        if(files == null) return;

        for (File file : files) {
            this.model.addElement(file);
        }
    }
}