package com.player.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircularButton extends JButton {
    private final ImageIcon imgIcon;
    private static final int padding = 10;

    public CircularButton(Image img) {
        this.imgIcon = new ImageIcon(img);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (imgIcon == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(this.getWidth(), this.getHeight()) - padding;
        int x = (this.getWidth() - diameter) / 2;
        int y = (this.getHeight() - diameter) / 2;

        // Create a circular clip
        Shape clip = new Ellipse2D.Float(x, y, diameter, diameter);
        g2.setClip(clip);

        // Draw the image scaled inside the circular area
        g2.drawImage(imgIcon.getImage(), x, y, diameter, diameter, this);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Optional: Draw a circular border
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight()) - padding;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        g2.setColor(Color.BLACK); // Border color
        g2.setStroke(new BasicStroke(2)); // Border thickness
        g2.drawOval(x, y, diameter, diameter);

        g2.dispose();
    }
}