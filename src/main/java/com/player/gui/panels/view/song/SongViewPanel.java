package com.player.gui.panels.view.song;

import com.player.Song;
import com.player.sound.AudioPlayer;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.*;
import static com.player.utils.Constants.F_HEIGHT;

public class SongViewPanel extends JPanel {
    private Song toPlay;
    private JButton pausePlay;


    public SongViewPanel() {
        this.setPreferredSize(new Dimension(SVP_WIDTH, F_HEIGHT));
        this.setBackground(Color.RED);
        this.setLayout(new GridBagLayout());
        this.init();
    }

    private void init() {
        GridBagConstraints gbc = new GridBagConstraints();
        JButton prev = new JButton("<");
        this.pausePlay = new JButton("Play");
        JButton next = new JButton(">");
        JSlider volumeSlider = new JSlider();

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(prev, gbc);

        gbc.gridx = 1;
        this.add(this.pausePlay, gbc);

        gbc.gridx = 2;
        this.add(next, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 10, 15 ,10);
        this.add(volumeSlider, gbc);

        // TODO: Find a way to select prev and next songs

        this.pausePlay.addActionListener(evt -> startStop());
        volumeSlider.addChangeListener(evt -> AudioPlayer.setVolume(volumeSlider.getValue()));
    }

    public void setToPlay(Song toPlay) {
        this.toPlay = toPlay;
    }

    private void startStop() {
        AudioPlayer.setFile(toPlay.getSongPath().toFile());
        AudioPlayer audioPlayer = AudioPlayer.getInstance();
        Thread t = new Thread(audioPlayer);

        if(!AudioPlayer.isPlaying()) {
            t.start();
            this.pausePlay.setText("Stop");
        } else {
            audioPlayer.kill();
            this.pausePlay.setText("Play");
        }
    }
}
