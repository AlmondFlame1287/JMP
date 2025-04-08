package com.player.gui.customs.renderers;

import javax.swing.*;
import java.awt.*;

public class SongCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list,
                                         Object value, int index, boolean isSelected,
                                         boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(isSelected) {
            label.setBackground(Color.DARK_GRAY);
            label.setForeground(Color.WHITE);
            label.setBorder(BorderFactory.createEtchedBorder());
        }

        return label;
    }
}
