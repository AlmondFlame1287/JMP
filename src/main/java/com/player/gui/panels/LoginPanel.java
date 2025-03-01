package com.player.gui.panels;

import com.player.Profile;
import com.player.gui.ContentPanel;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private final JFrame parent;
    private final JTextField nameField = new JTextField();

    public LoginPanel(JFrame parent) {
        this.setBackground(Color.black);
        this.setLayout(new GridBagLayout());
        this.parent = parent;
        this.initComponents();
    }

    private void initComponents() {
        final JLabel usernameLabel = new JLabel("Username:");
        final JButton done = new JButton("Done");
        final JButton exit = new JButton("Exit");
        GridBagConstraints constraints = new GridBagConstraints();

        done.addActionListener(evt -> onDonePressed());
        exit.addActionListener(evt -> System.exit(0));

        /*
         *
         *   | Username:    |                  |                   |
         *   | TEXT_FIELD   | TEXT_FIELD       |                   |
         *   | Done         | Exit             | Light-weight mode |
         *
         * */

        constraints.insets = new Insets(0, 1, 0, 1);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = constraints.gridy = 0;
        this.add(usernameLabel, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 4;
        this.add(nameField, constraints);

        // TODO: Remove on production
        nameField.setText("Dio");

        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(done, constraints);

        constraints.gridx = 2;
        this.add(exit, constraints);
    }

    private void onDonePressed() {
        this.changePanel();
        ContentPanel.setProfile(new Profile(nameField.getText()));
        ContentPanel.getPsp().loadPlaylists();
        parent.revalidate();
    }

    private void changePanel() {
        parent.setContentPane(ContentPanel.getInstance());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setGradientAsBackground((Graphics2D) g);
    }

    private void setGradientAsBackground(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        Color color1 = Color.decode("#accbee");
        Color color2 = Color.decode("#e7f0fd");
        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
        g.setPaint(gp);
        g.fillRect(0, 0, w, h);
    }
}
