package com.player.gui.dialogs;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.F_HEIGHT;
import static com.player.utils.Constants.F_WIDTH;

public class SettingsDialog extends JDialog {

    public SettingsDialog() {
        this.setTitle("Settings");
        this.setSize(F_WIDTH / 2, F_HEIGHT / 2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.getContentPane()
                .setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setVisible(true);
    }
}
