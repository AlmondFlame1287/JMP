package com.player.gui.customs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ProgressBar extends JProgressBar {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = height;
        int progressWidth = (int) (((double) getValue() / getMaximum()) * width);

        // Track
        g2.setColor(new Color(40, 40, 40));
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        // Progress gradient
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(30, 215, 96),
                progressWidth, 0, new Color(25, 180, 80));
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, progressWidth, height, arc, arc);

        // Soft glow
        g2.setColor(new Color(30, 215, 96, 80));
        g2.fillRoundRect(0, 0, progressWidth, height, arc, arc);

        g2.dispose();
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public Border getBorder() {
        return BorderFactory.createEmptyBorder();
    }
}
