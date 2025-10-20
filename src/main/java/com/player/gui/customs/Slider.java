package com.player.gui.customs;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class Slider extends JSlider {
    public Slider() {
        super();

        this.setUI(new BasicSliderUI(this) {
            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4, 4, 4);
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillRoundRect(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, 5, 5);
                g2.dispose();
            }
        });
    }

    @Override
    public boolean isFocusable() {
        return false;
    }

    @Override
    public boolean getPaintTicks() {
        return false;
    }

    @Override
    public boolean getPaintLabels() {
        return false;
    }
}
