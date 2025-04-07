package com.player.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    /**
     * Load an image from a file, efficiently handling memory usage.
     * This method will scale the image if it's too large to fit in memory.
     *
     * @param imagePath Path to the image file
     * @param maxWidth Maximum width for scaling (optional)
     * @param maxHeight Maximum height for scaling (optional)
     * @return BufferedImage object representing the image
     */
    public static BufferedImage loadImage(File imageFile, int maxWidth, int maxHeight) {
        // Read image using ImageIO (handles both PNG and JPG)
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);

            if (originalImage == null) {
                throw new IOException("Failed to load image: " + imageFile.getName());
            }

            // Check if scaling is required
            if (originalImage.getWidth() > maxWidth || originalImage.getHeight() > maxHeight) {
                return scaleImage(originalImage, maxWidth, maxHeight);
            }

            return originalImage;
        } catch (IOException ioe) {
            System.err.println("Something went wrong: " + ioe.getMessage());
        }

        return null;
    }

    /**
     * Scale the image to fit within the specified width and height.
     * This scales the image proportionally.
     *
     * @param image The original image
     * @param maxWidth Maximum width to scale to
     * @param maxHeight Maximum height to scale to
     * @return Scaled BufferedImage
     */
    private static BufferedImage scaleImage(BufferedImage image, int maxWidth, int maxHeight) {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();

        // Calculate scaling factor to maintain aspect ratio
        double widthRatio = (double) maxWidth / originalWidth;
        double heightRatio = (double) maxHeight / originalHeight;

        // Use the smaller ratio to maintain the aspect ratio
        double scaleRatio = Math.min(widthRatio, heightRatio);

        // Calculate new dimensions
        int newWidth = (int) (originalWidth * scaleRatio);
        int newHeight = (int) (originalHeight * scaleRatio);

        // Create a scaled image
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Convert the Image to a BufferedImage for better control over memory
        BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return bufferedImage;
    }
}