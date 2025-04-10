package com.player.gui.customs;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomTextField extends JTextField {

    private final int arcWidth = 20;
    private final int arcHeight = 20;
    private final Color backgroundColor = new Color(255, 255, 255, 200); // translucent white
    private final Color borderColor = new Color(200, 200, 200);

    public CustomTextField(int columns) {
        super(columns);
        setOpaque(false); // We’ll handle the background ourselves
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // internal padding
        setForeground(Color.BLACK);
        setFont(getFont().deriveFont(Font.PLAIN, 14f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2.dispose();
    }
}
