package com.player.gui.customs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class AbstractCircularButton extends JButton {
    private ImageIcon imgIcon;
    private static final int padding = 10;
    private Color borderColor;
    private Color paintColor;

    public AbstractCircularButton(Image img) {
        this.imgIcon = new ImageIcon(img);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    public AbstractCircularButton(Color borderColor, Color paintColor) {
        this.borderColor = borderColor;
        this.paintColor = paintColor;
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(this.getWidth(), this.getHeight()) - padding;
        int x = (this.getWidth() - diameter) / 2;
        int y = (this.getHeight() - diameter) / 2;

        // Create a circular clip
        Shape clip = new Ellipse2D.Float(x, y, diameter, diameter);
        g2.setClip(clip);

        // Draw the image scaled inside the circular area
        if(imgIcon == null) {
            g2.setPaint(this.paintColor);
            g2.fillOval(x, y, diameter, diameter);
            g2.dispose();
            return;
        }

        g2.drawImage(imgIcon.getImage(), x, y, diameter, diameter, this);
        imgIcon.getImage().flush();

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight()) - padding;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        g2.setColor(this.borderColor); // Border color
        g2.setStroke(new BasicStroke(2)); // Border thickness
        g2.drawOval(x, y, diameter, diameter);

        g2.dispose();
    }
}
