package com.player.sound.extractors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WavExtractor implements HeaderExtractor {
    public static final int HEADER_SIZE = 48; // 44 bytes + 4 bytes of data size
    private static File file;
    private static final byte[] header = new byte[HEADER_SIZE];

    public WavExtractor(File f) {
        file = f;
        this.readHeader();
    }

    private void readHeader() {
        try(FileInputStream fis = new FileInputStream(file)) {
            System.out.println("[INFO] Read " + fis.read(header, 0, HEADER_SIZE) + " bytes from the song file");
        } catch (IOException ignored) {}
    }

    public int toInt(byte[] bytes) {
//        this.printByteArray(bytes);
        return ((bytes[3] & 0xFF) << 24)
                | ((bytes[2] & 0xFF) << 16)
                | ((bytes[1] & 0xFF) << 8)
                |  (bytes[0] & 0xFF);
    }

    private void printByteArray(byte[] array) {
        for(byte b : array) {
            String hex = String.format("%02X", b);
            System.out.println("Hexadecimal: " + hex);
            System.out.println("Decimal: " + b);
        }
        System.out.println("--------------");
    }

    private int getBytesPerSecond() {
        int bytesPerSecond = toInt(new byte[]{
                header[28], header[29], header[30], header[31]
        });

        System.out.println("Bytes per second: " + bytesPerSecond);

        return bytesPerSecond;
    }

    @Override
    public int getDataSize() {
        return toInt(new byte[] {
                header[42], header[43], header[44], header[45]
        });
    }

    @Override
    public int getDurationInSeconds() {
        return getDataSize() / getBytesPerSecond();
    }
}
