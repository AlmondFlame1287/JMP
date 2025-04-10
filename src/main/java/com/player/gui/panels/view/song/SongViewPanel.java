package com.player.gui.panels.view.song;

import com.player.Song;
import com.player.gui.customs.TransparentButton;
import com.player.sound.AudioPlayer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;

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
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        this.init();
    }

    private void init() {
        GridBagConstraints gbc = new GridBagConstraints();
        TransparentButton prev = new TransparentButton("|<");
        this.pausePlay = new TransparentButton("Play");
        TransparentButton next = new TransparentButton(">|");
        JSlider volumeSlider = new JSlider();

        this.setupSlider(volumeSlider);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(prev, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        this.add(this.pausePlay, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        this.add(next, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        this.add(volumeSlider, gbc);

        // TODO: Find a way to select prev and next songs

        this.pausePlay.addActionListener(evt -> startStop());
        volumeSlider.addChangeListener(evt -> AudioPlayer.setVolume(volumeSlider.getValue()));
    }

    private void setupSlider(JSlider slider) {
        slider.setFocusable(false);
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        slider.setBackground(this.getBackground());

        slider.setUI(new BasicSliderUI(slider) {
            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4, 4, 4);
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
                g2.dispose();
            }
        });
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
