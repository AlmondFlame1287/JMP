package com.player.sound.extractors;

import javax.sound.sampled.AudioFormat;

public interface HeaderExtractor {
    int getDataSize();
    int getDurationInSeconds();
    int getBytesPerSecond();
    AudioFormat.Encoding getAudioEncoding();
    int getNumberOfChannels();
    int getBitsPerSample();
}
