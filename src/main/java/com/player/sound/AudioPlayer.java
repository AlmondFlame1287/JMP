package com.player.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class AudioPlayer implements Runnable {
    private static AudioPlayer instance;
    private AudioInputStream in;
    private AudioFormat outFormat;
    private static File file;
    private SourceDataLine line;
    private static boolean playing;
    private static FloatControl volumeControl;

    private AudioPlayer() {
        try {
            in = getAudioInputStream(file);
            outFormat = getOutFormat(in.getFormat());
            Info info = new Info(SourceDataLine.class, outFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException | UnsupportedAudioFileException lue) {
            System.err.println("Something went wrong: " + lue.getMessage());
        } catch (IOException ioe) {
            System.err.println("File exception: " + ioe.getMessage());
        }
    }

    public static AudioPlayer getInstance() {
        if(instance == null) {
            instance = new AudioPlayer();
        }
        return instance;
    }

    public static boolean isPlaying() {
        return playing;
    }

    @Override
    public void run() {
        playing = true;
        this.play();
    }

    public static void setFile(File file) {
        AudioPlayer.file = file;
    }

    public void kill() {
        line.stop();
        line.drain();
        line.close();
        playing = false;
        Thread.currentThread().interrupt();
    }

    private void play() {
        try {
            if (line != null) {
                in = getAudioInputStream(file);
                line.open(outFormat);
                line.start();

                volumeControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(50);

                stream(getAudioInputStream(outFormat, in), line);
            }
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException unlioe) {
            throw new IllegalStateException(unlioe);
        }
    }

    public static void setVolume(int percent) {
        if(volumeControl == null) return;

        System.out.println("Current:" + volumeControl.getValue());
        final float min = volumeControl.getMinimum();
        final float max = 0;

        final float newValue = min + (float) ((max - min) * (Math.log10(percent) / 2));
        volumeControl.setValue(newValue);
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line) throws IOException {
        final byte[] buffer = new byte[line.getBufferSize()];
        for (int i = 0; i != -1; i = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, i);
        }
    }
}