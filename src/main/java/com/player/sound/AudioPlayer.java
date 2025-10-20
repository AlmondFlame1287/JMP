package com.player.sound;

import com.player.sound.extractors.HeaderExtractor;
import com.player.sound.extractors.WavExtractor;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class AudioPlayer implements Runnable {
    private static AudioPlayer instance;
    private static File file;
    private SourceDataLine line;
    private static boolean playing;
    private static FloatControl volumeControl;
    private static long bytesWrittenToLine;
    private static HeaderExtractor extractor;
    private static int oldPercent = 50;

    private AudioPlayer() {
        bytesWrittenToLine = 0;
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
        bytesWrittenToLine = 0; // Reset the number of bytesWritten so that when selecting a new song, we don't skip said bytes
        AudioPlayer.file = file;
        extractor = selectExtractor(file);
    }

    public static boolean isFileSet() {
        return !(file == null);
    }

    private static HeaderExtractor selectExtractor(File file) {
//        if(file.getName().endsWith(".wav"))
//            return new WavExtractor(file);
        return new WavExtractor(file);
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
            AudioInputStream in = getAudioInputStream(file);
            AudioFormat outFormat = getOutFormat(in.getFormat());
            Info info = new Info(SourceDataLine.class, outFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);

            line.open(outFormat);
            line.start();

            volumeControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(oldPercent);

            stream(getAudioInputStream(outFormat, in), line);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException unlioe) {
            throw new IllegalStateException(unlioe);
        }
    }

    public static long getBytesWrittenToLine() {
        return bytesWrittenToLine;
    }

    public static void setVolume(int percent) {
        if(volumeControl == null) return;

        oldPercent = percent;

        System.out.println("Current:" + volumeControl.getValue());
        final float min = volumeControl.getMinimum();
        final float max = 0;

        final float newValue = min + (float) ((max - min) * (Math.log10(percent) / 2));
        volumeControl.setValue(newValue);
    }

    /**
     * Gets the format form the AudioInputStream format
     * @param inFormat the input stream format
     * @return a new format, same as the one from the input
     */
    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    /**
     * Stream bytes from the input stream to the data line for audio playing.
     * @param in the input stream
     * @param line the output data line
     * @throws IOException when the line can't be written to
     */
    private void stream(AudioInputStream in, SourceDataLine line) throws IOException {
        long bytesSkipped = in.skip(bytesWrittenToLine);
        System.out.println("Bytes skipped: " + bytesSkipped);

        final byte[] buffer = new byte[extractor.getBytesPerSecond()]; // A middle-buffer that's the same size as the line buffer
        for (int i = 0; i != -1; i = in.read(buffer, 0, buffer.length)) { // Read buffer.length bytes into buffer, with an offset of 0
            bytesWrittenToLine += line.write(buffer, 0, i); // Write the data i bytes from buffer to the line with an offset of 0
                                                                // Once we have the number of bytes actually written,
                                                                // we can skip that same amount of bytes next time we
                                                                // start the stream
        }
    }

    public static int getSongDurationInSeconds() {
        return extractor.getDurationInSeconds();
    }

    public static long getDataSize() {
        return extractor.getDataSize();
    }

    public static int getBytesPerSecond() {
        return extractor.getBytesPerSecond();
    }
}