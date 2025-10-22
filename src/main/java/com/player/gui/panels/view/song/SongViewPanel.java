package com.player.gui.panels.view.song;

import com.player.Song;
import com.player.gui.ContentPanel;
import com.player.gui.customs.ProgressBar;
import com.player.gui.customs.Slider;
import com.player.gui.customs.TransparentButton;
import com.player.gui.dialogs.SettingsDialog;
import com.player.sound.AudioPlayer;
import com.player.sound.SongStatus;
import com.player.sound.SongStatusObserver;
import com.player.utils.SettingsParser;

import javax.swing.*;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.player.utils.Constants.*;
import static com.player.utils.Constants.F_HEIGHT;

public class SongViewPanel extends JPanel implements SongStatusObserver {
    private final JButton pausePlay;
    private final JLabel songCurrentlyPlaying;
    private final ExecutorService executor;
    private final Slider volumeSlider;
    private final ProgressBar songPercentage;
    private final JLabel currentSongPosition;
    private final JLabel songDuration;

    private int nextSongIndx;
    private int prevSongIndx;

    private Song nextSong;
    private Song prevSong;

    public SongViewPanel() {
        this.executor = Executors.newSingleThreadExecutor();
        this.volumeSlider = new Slider();
        this.songPercentage = new ProgressBar();
        this.pausePlay = new TransparentButton("Play");
        this.currentSongPosition = new JLabel("0:00");
        this.songCurrentlyPlaying = new JLabel();
        this.songDuration = new JLabel();
        AudioPlayer.addObserver(this);

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
        TransparentButton next = new TransparentButton(">|");

        this.songCurrentlyPlaying.setForeground(new Color(255, 255, 255, 75));
        this.volumeSlider.setBackground(this.getBackground());

        this.songPercentage.setValue(0);

        this.addComponents(prev, next, gbc);
        this.addButtonListeners(prev, next);

        volumeSlider.addChangeListener(evt -> AudioPlayer.setVolume(volumeSlider.getValue()));
    }

    private void addComponents(TransparentButton prev, TransparentButton next, GridBagConstraints gbc) {
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

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        this.add(songPercentage, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        this.add(currentSongPosition, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        this.add(songDuration, gbc);
    }

    private void addButtonListeners(TransparentButton prev, TransparentButton next) {
        next.addActionListener(evt -> playNext());
        prev.addActionListener(evt -> playPrevious());

        this.pausePlay.addActionListener(evt -> {
            if(AudioPlayer.getStatus() == SongStatus.PLAYING) {
                pause();
                return;
            }
            start();
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
        this.updateSongDuration();
    }

    private void updateSongDuration() {
        final int mins = AudioPlayer.getSongDurationInSeconds() / 60;
        final int secs = AudioPlayer.getSongDurationInSeconds() % 60;

        String songDuration = mins + ":" + ((secs < 10) ? "0" + secs : secs);
        this.songDuration.setText(songDuration);
    }

    public void setNext(Song next, int indx) {
        this.nextSong = next;
        this.nextSongIndx = indx;
        System.out.println("Next song: " + next.getName());
        System.out.println("Song duration in seconds: " + AudioPlayer.getSongDurationInSeconds());
    }

    private void start() {
        if(!AudioPlayer.isFileSet()) return;

        executor.execute(AudioPlayer.getInstance());
        SwingWorker<Void, Void> progressBarWorker = getProgressBarWorker();

        progressBarWorker.execute();
        this.pausePlay.setText("Pause");
    }

    private void pause() { // kill(boolean shouldStop)
        AudioPlayer.getInstance().pauseSong();
        this.pausePlay.setText("Play");
    }

    private SwingWorker<Void, Void> getProgressBarWorker() {
        return new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // (read_size / total_song_size) * 100
                while(AudioPlayer.getStatus() == SongStatus.PLAYING) {
                    SwingUtilities.invokeLater(() -> updateSongPercentage());

                    if(songPercentage.getValue() == 100) return null;
                    Thread.sleep(200);
                }

                System.out.println("Done!");
                return null;
            }
        };
    }

    private void updateSongPercentage() {
        songPercentage.setValue(
                (int) ((AudioPlayer.getBytesWrittenToLine() * 100.0f) / AudioPlayer.getDataSize()));

        final long currentSongSecondsTotal = (AudioPlayer.getBytesWrittenToLine() / AudioPlayer.getBytesPerSecond());

        final int currentSongMinutes = (int) (currentSongSecondsTotal / 60);
        final int currentSongSeconds = (int) (currentSongSecondsTotal % 60);

        String songPercentagePosition = currentSongMinutes + ":" + ((currentSongSeconds < 10) ? "0" + currentSongSeconds : currentSongSeconds);

        currentSongPosition.setText(songPercentagePosition);
    }

    private void playNext() {
        pause();
        setToPlay(nextSong, nextSongIndx);
        start();
    }

    private void playPrevious() {
        pause();
        setToPlay(prevSong, prevSongIndx);
        start();
    }

    @Override
    public void statusChanged(SongStatus newStatus) {
        if(newStatus == SongStatus.ENDED)
            playNext();
    }
}
