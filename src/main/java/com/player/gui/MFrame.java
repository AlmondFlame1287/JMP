package com.player.gui;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.*;

public class MFrame extends JFrame {
    public MFrame() {
        this.setTitle(APP_NAME);
        this.setSize(new Dimension(F_WIDTH, F_HEIGHT));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupMenuBar();
        this.setContentPane(ContentPanel.getInstance());
        this.setVisible(true);
    }

    private void setupMenuBar() {
        JMenuBar jmb = new JMenuBar(); // TODO: Get a better looking jmb
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu view = new JMenu("View");
        JMenu preferences = new JMenu("Preferences");

        // TODO: Implement sign-out
        JMenuItem signOut = new JMenuItem("Sign out");
        file.add(signOut);

        jmb.add(file);
        jmb.add(edit);
        jmb.add(view);
        jmb.add(preferences);

        this.setJMenuBar(jmb);
    }
}
