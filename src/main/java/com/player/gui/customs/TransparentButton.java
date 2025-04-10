package com.player.gui.customs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransparentButton extends JButton {

    private final Color hoverBackground = new Color(255, 255, 255, 30); // semi-transparent white
    private final Color normalBackground = new Color(0, 0, 0, 0); // fully transparent

    public TransparentButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);

        setBackground(normalBackground); // default background

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackground);
                setOpaque(true); // allow background to be visible
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalBackground);
                setOpaque(false); // go back to transparent
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // rounded hover shape
        }

        super.paintComponent(g);
    }
}
