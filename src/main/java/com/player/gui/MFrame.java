package com.player.gui;

import com.player.gui.dialogs.SettingsDialog;
import com.player.gui.panels.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.player.utils.Constants.*;

public class MFrame extends JFrame {
    private final LoginPanel loginPanel;

    public MFrame() {
        loginPanel = new LoginPanel(this);
        this.setTitle(APP_NAME);
        this.setSize(new Dimension(F_WIDTH, F_HEIGHT));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupMenuBar();
        this.setContentPane(loginPanel);
        this.setVisible(true);
        this.setupCloseEvent();
    }

    private void setupCloseEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(ContentPanel.getProfile() == null) System.exit(0);

                ContentPanel.getProfile().savePfpToFile();
                ContentPanel.getProfile().savePlaylistToFile();
                super.windowClosing(e);
            }
        });
    }

    private void setupMenuBar() {
        JMenuBar jmb = new JMenuBar();
        JMenu file = this.createCustomJMenu("File");
        JMenu edit = this.createCustomJMenu("Edit");
        JMenu view = this.createCustomJMenu("View");
        JMenu preferences = this.createCustomJMenu("Preferences");

        jmb.setBackground(Color.decode("#141414"));
        jmb.setBorderPainted(false);

        JMenuItem signOut = new JMenuItem("Sign out");
        file.add(signOut);
        signOut.addActionListener(evt -> this.setContentPane(loginPanel));

        JMenuItem settings = new JMenuItem("Settings");
        preferences.add(settings);
        settings.addActionListener(evt -> {
            if(!this.getContentPane().getName().equals("ContentPanel")) return;
            new SettingsDialog();
        });

        jmb.add(file);
        jmb.add(edit);
        jmb.add(view);
        jmb.add(preferences);

        this.setJMenuBar(jmb);
    }

    private JMenu createCustomJMenu(String txt) {
        JMenu menu = new JMenu(txt) {
            @Override
            public void paintBorder(Graphics g) {

            }
        };

        menu.setOpaque(false);
        menu.setForeground(Color.WHITE);

        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menu.setForeground(new Color(255, 255, 255, 200));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menu.setForeground(Color.WHITE);
                repaint();
            }
        });

        return menu;
    }
}
