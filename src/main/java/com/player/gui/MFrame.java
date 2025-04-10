package com.player.gui;

import com.player.gui.panels.LoginPanel;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                if(ContentPanel.getProfile() == null) System.exit(0);

                ContentPanel.getProfile().savePlaylistToFile();
                super.windowClosing(e);
            }
        });
    }

    private void setupMenuBar() {
        JMenuBar jmb = new JMenuBar();
        JMenu file = this.getCustomJMenu("File");
        JMenu edit = this.getCustomJMenu("Edit");
        JMenu view = this.getCustomJMenu("View");
        JMenu preferences = this.getCustomJMenu("Preferences");

        jmb.setBackground(Color.decode("#141414"));
        jmb.setBorderPainted(false);

        // TODO: Implement sign-out
        JMenuItem signOut = new JMenuItem("Sign out");
        file.add(signOut);

        jmb.add(file);
        jmb.add(edit);
        jmb.add(view);
        jmb.add(preferences);

        this.setJMenuBar(jmb);
    }

    private JMenu getCustomJMenu(String txt) {
        JMenu menu = new JMenu(txt);
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menu.setForeground(new Color(255, 255, 255, 200));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menu.setForeground(Color.LIGHT_GRAY);
                repaint();
            }
        });

        menu.setForeground(Color.LIGHT_GRAY);
        return menu;
    }
}
