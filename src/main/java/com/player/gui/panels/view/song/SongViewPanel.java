package com.player.gui.panels.view.song;

import com.player.Song;
import com.player.gui.ContentPanel;
import com.player.gui.customs.TransparentButton;
import com.player.gui.dialogs.SettingsDialog;
import com.player.sound.AudioPlayer;
import com.player.utils.SettingsParser;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.player.utils.Constants.*;
import static com.player.utils.Constants.F_HEIGHT;

public class SongViewPanel extends JPanel {
    private JButton pausePlay;
    private JLabel songCurrentlyPlaying;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private int nextSongIndx;
    private int prevSongIndx;

    private Song nextSong;
    private Song prevSong;

    public SongViewPanel() {
        this.setName("song");
        this.setPreferredSize(new Dimension(SVP_WIDTH, F_HEIGHT));
        this.setBackground(SettingsParser.getColor(this.getName()));
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        SettingsDialog.addPanelToComboBox(this);

        this.init();
    }

    private void init() {
        GridBagConstraints gbc = new GridBagConstraints();
        TransparentButton prev = new TransparentButton("|<");
        this.pausePlay = new TransparentButton("Play");
        this.songCurrentlyPlaying = new JLabel();
        this.songCurrentlyPlaying.setForeground(new Color(255, 255, 255, 75));
        TransparentButton next = new TransparentButton(">|");
        JSlider volumeSlider = new JSlider();

        this.setupSlider(volumeSlider);

        // TODO: Refactor this mess

        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.gridy = 0;
        this.add(songCurrentlyPlaying, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(prev, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        this.add(this.pausePlay, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        this.add(next, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        this.add(volumeSlider, gbc);

        // TODO: Find a way to select prev and next songs

        this.pausePlay.addActionListener(evt -> {
            if(AudioPlayer.isPlaying()) {
                stop();
                return;
            }
            start();
        });

        next.addActionListener(evt -> {
            stop();
            setToPlay(nextSong, nextSongIndx);
            start();
        });

        prev.addActionListener(evt -> {
            stop();
            setToPlay(prevSong, prevSongIndx);
            start();
        });

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
                g2.fillRoundRect(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, 5, 5);
                g2.dispose();
            }
        });
    }

    public void setPrevious(Song prev, int indx) {
        this.prevSong = prev;
        this.prevSongIndx = indx;
        System.out.println("Previous song: " + prev.getName());
    }

    public void setToPlay(Song toPlay, int indx) {
        AudioPlayer.setFile(toPlay.getSongPath().toFile());
        ContentPanel.getPvp().getSongsToDisplay().setSelectedIndex(indx);
        this.songCurrentlyPlaying.setText(toPlay.getName());
    }

    public void setNext(Song next, int indx) {
        this.nextSong = next;
        this.nextSongIndx = indx;
        System.out.println("Next song: " + next.getName());
    }

    private void start() {
        executor.execute(AudioPlayer.getInstance());
        this.pausePlay.setText("Pause");
    }

    private void stop() {
        AudioPlayer.getInstance().kill();
        this.pausePlay.setText("Play");
    }
}
