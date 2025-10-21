package com.player.sound.extractors;

import javax.sound.sampled.AudioFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WavExtractor implements HeaderExtractor {
    public static final int HEADER_SIZE = 60;
    private static File file;
    private static final byte[] header = new byte[HEADER_SIZE];
    private static int fmtIndx = -1; // Index points at the last byte of "fmt "
    private static int dataIndx = -1; // Index points at the last byte of "data"

    public WavExtractor(File f) {
        file = f;
        this.readHeader();
        this.findIDs();
    }

    private void readHeader() {
        try(FileInputStream fis = new FileInputStream(file)) {
            System.out.println("[INFO] Read " + fis.read(header, 0, HEADER_SIZE) + " bytes from the song file");
        } catch (IOException ignored) {}
    }

    /**
     * Find fmt and data identifiers in the header file
     */
    private void findIDs() {
        for(int i = 0; i < header.length - 1; i++) { // Need that -1
            if(header[i] == 'f' && header[i+1] == 'm' && header[i+2] == 't') {
                fmtIndx = i + 4;
            } else if(header[i] == 'd' && header[i+1] == 'a' && header[i+2] == 't' && header[i+3] == 'a') {
                dataIndx = i + 4;
            }

            if(fmtIndx != -1 && dataIndx != -1) break;
        }

        System.out.println("fmt_first_indx = " + fmtIndx);
        System.out.println("data_first_indx = " + dataIndx);
    }

    @Override
    public int getBytesPerSecond() {
        final int skippedBytesFromFmt = 12;
        final int bytesPerSecondIndx = fmtIndx + skippedBytesFromFmt;

        return toInt4Bytes(new byte[] {
                header[bytesPerSecondIndx],
                header[bytesPerSecondIndx + 1],
                header[bytesPerSecondIndx + 2],
                header[bytesPerSecondIndx + 3]
        });
    }

    @Override
    public AudioFormat.Encoding getAudioEncoding() {
        final int skippedBytesFromFmt = 4;
        final int audioEncodingIndx = fmtIndx + skippedBytesFromFmt;

        final int encoding = toInt2Bytes(
                new byte[] { header[audioEncodingIndx], header[audioEncodingIndx + 1] }
        );

        if(encoding == 1)
            return AudioFormat.Encoding.PCM_SIGNED;
        else if(encoding == 3)
            return AudioFormat.Encoding.PCM_FLOAT;

        return AudioFormat.Encoding.PCM_SIGNED;
    }

    @Override
    public int getNumberOfChannels() {
        final int skippedBytesFromFmt = 6;
        final int channelIndx = fmtIndx + skippedBytesFromFmt;

        return toInt2Bytes(
                new byte[] { header[channelIndx], header[channelIndx + 1] }
        );
    }

    @Override
    public int getBitsPerSample() {
        final int skippedBytesFromFmt = 18;
        final int bitsPerSampleIndx = fmtIndx + skippedBytesFromFmt;

        return toInt2Bytes(
                new byte[] { header[bitsPerSampleIndx], header[bitsPerSampleIndx + 1] }
        );
    }

    @Override
    public int getDataSize() {
        return toInt4Bytes(new byte[] {
                header[dataIndx], header[dataIndx + 1], header[dataIndx + 2], header[dataIndx + 3]
        });
    }

    @Override
    public int getDurationInSeconds() {
        return getDataSize() / getBytesPerSecond();
    }

    public int toInt4Bytes(byte[] bytes) {
//        this.printByteArray(bytes);
        return ((bytes[3] & 0xFF) << 24)
                | ((bytes[2] & 0xFF) << 16)
                | ((bytes[1] & 0xFF) << 8)
                |  (bytes[0] & 0xFF);
    }

    public int toInt2Bytes(byte[] bytes) {
        return ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
    }

    public static void printByteArray(byte[] array) {
        for(byte b : array) {
            String hex = String.format("%02X", b);
            System.out.println("Hexadecimal: " + hex);
            System.out.println("Decimal: " + b);
        }
        System.out.println("--------------");
    }
}
