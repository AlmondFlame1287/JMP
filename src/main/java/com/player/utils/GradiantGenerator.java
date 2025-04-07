package com.player.utils;

import java.awt.*;
import static com.player.utils.Constants.GradientStyle;

public class GradiantGenerator {
    private GradiantGenerator() {}

    public static void setGradientAsBackground(Graphics2D g, Color[] colors, int w, int h, GradientStyle style) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        GradientPaint gp;

        switch (style) {
            case TOP_TO_BOTTOM:
                gp = new GradientPaint(0, 0, colors[0], 0, h, colors[1]);
                break;
            case BOTTOM_TO_TOP:
                gp = new GradientPaint(0, h, colors[0], 0, 0, colors[1]);
                break;
            case RIGHT_TO_LEFT:
                gp = new GradientPaint(w, 0, colors[0], 0, 0, colors[1]);
                break;
            case LEFT_TO_RIGHT:
            default:
                gp = new GradientPaint(0, 0, colors[0], w, h, colors[1]);
                break;
        }

        g.setPaint(gp);
        g.fillRect(0, 0, w, h);
    }
}
