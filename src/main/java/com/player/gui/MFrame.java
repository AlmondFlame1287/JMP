package com.player.gui;

import com.player.gui.panels.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.player.utils.Constants.*;

public class MFrame extends JFrame {
    public MFrame() {
        this.setTitle(APP_NAME);
        this.setSize(new Dimension(F_WIDTH, F_HEIGHT));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupMenuBar();
        this.setContentPane(new LoginPanel(this));
        this.setVisible(true);
        this.setupCloseEvent();
    }

    private void setupCloseEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ContentPanel.getProfile().savePlaylistToFile();
                super.windowClosing(e);
            }
        });
    }

    private void setupMenuBar() {
        JMenuBar jmb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu view = new JMenu("View");
        JMenu preferences = new JMenu("Preferences");

        jmb.setBackground(Color.decode("#141414"));
//        jmb.setForeground(Color.WHITE);
        jmb.setBorderPainted(false);

        file.setForeground(Color.LIGHT_GRAY);
        edit.setForeground(Color.LIGHT_GRAY);
        view.setForeground(Color.LIGHT_GRAY);
        preferences.setForeground(Color.LIGHT_GRAY);

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
