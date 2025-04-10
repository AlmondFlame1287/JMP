package com.player.gui;

import com.player.gui.panels.LoginPanel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuUI;
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
                if(ContentPanel.getProfile() == null) System.exit(0);

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

        // TODO: Implement sign-out
        JMenuItem signOut = new JMenuItem("Sign out");
        file.add(signOut);

        jmb.add(file);
        jmb.add(edit);
        jmb.add(view);
        jmb.add(preferences);

        this.setJMenuBar(jmb);
    }

    private JMenu createCustomJMenu(String txt) {
        JMenu menu = new JMenu(txt);

        menu.setUI(new BasicMenuUI() {
            @Override
            protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());
            }

            @Override
            protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
                g.setColor(Color.LIGHT_GRAY);
                g.setFont(menuItem.getFont());
                g.drawString(text, textRect.x, textRect.y + g.getFontMetrics().getAscent());
            }
        });

        return menu;
    }
}
