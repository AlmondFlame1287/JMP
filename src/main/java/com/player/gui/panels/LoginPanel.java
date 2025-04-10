package com.player.gui.panels;

import com.player.Profile;
import com.player.gui.ContentPanel;
import com.player.gui.customs.CustomTextField;
import com.player.gui.customs.TransparentButton;
import com.player.utils.Constants;
import com.player.utils.GradiantGenerator;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private final JFrame parent;
    private final CustomTextField nameField;

    public LoginPanel(JFrame parent) {
        this.setLayout(new GridBagLayout());
        this.parent = parent;
        this.nameField = new CustomTextField(8);
        this.initComponents();
    }

    private void initComponents() {
        final JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        final TransparentButton done = new TransparentButton("Done");
        final TransparentButton exit = new TransparentButton("Exit");
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
        ContentPanel.setProfile(new Profile(nameField.getText()));
        this.changePanel();
        ContentPanel.getPsp().loadPlaylists();
        parent.revalidate();
    }

    private void changePanel() {
        parent.setContentPane(ContentPanel.getInstance());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        GradiantGenerator.setGradientAsBackground(
                (Graphics2D) g, new Color[] {
//                    Color.decode("#accbee"),
//                    Color.decode("#e7f0fd")
                        Color.decode("#3e403f"),
                        Color.decode("#232423")
                }, this.getWidth(), this.getHeight(),
                Constants.GradientStyle.TOP_TO_BOTTOM
        );
    }
}
