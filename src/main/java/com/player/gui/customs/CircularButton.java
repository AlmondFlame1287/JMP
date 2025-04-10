package com.player.gui.customs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircularButton extends JButton {
    private Image img;
    private Color borderColor;
    private Color backgroundColor;
    private final boolean isImageButton;

    private static final int padding = 3;

    // Constructor for image-based button
    public CircularButton(Image img) {
        this.img = img;
        this.isImageButton = true;

        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    // Constructor for color + text-based button
    public CircularButton(Color borderColor, Color backgroundColor, String text) {
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        this.setText(text);
        this.isImageButton = false;

        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight()) - padding;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        Shape circle = new Ellipse2D.Float(x, y, diameter, diameter);
        g2.setClip(circle);

        if (isImageButton && img != null) {
            g2.drawImage(img, x, y, diameter, diameter, this);
            img.flush();
            g2.dispose();
            return;
        }

        g2.setPaint(backgroundColor != null ? backgroundColor : getBackground());
        g2.fillOval(x, y, diameter, diameter);

        // Draw centered text
        String text = getText();
        if (text != null && !text.isEmpty()) {
            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            Rectangle textBounds = fm.getStringBounds(text, g2).getBounds();

            int textX = getWidth() / 2 - textBounds.width / 2;
            int textY = getHeight() / 2 + textBounds.height / 2 - fm.getDescent();

            g2.setColor(getForeground());
            g2.drawString(text, textX, textY);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (isImageButton || borderColor == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight()) - padding;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(x, y, diameter, diameter);

        g2.dispose();
    }
}