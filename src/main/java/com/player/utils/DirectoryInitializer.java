package com.player.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;

public class DirectoryInitializer {
    private DirectoryInitializer() {}

    // Use reflection to get fields
    public static void initializeDirectories() {
        final Field[] classFields = Constants.class.getDeclaredFields();

        try {
            for(Field field : classFields) {
                if(!(field.get(field.getName()) instanceof Path)) continue;

                final Path pathToInit = (Path) field.get(field.getName());
                new File(pathToInit.toString()).mkdirs();
                System.out.println("Path initialized: " + pathToInit);
            }
        } catch (IllegalAccessException iae) {
            System.err.println("There was a problem with the directory initialization: " + iae.getMessage());
        }
    }
}
