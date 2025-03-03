package com.player.gui.panels.selection;

import com.player.Profile;
import com.player.gui.customs.CircularButton;
import com.player.gui.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.player.utils.Constants.*;

public class ProfilePanel extends JPanel {
    public ProfilePanel() {
        this.setBackground(Color.CYAN);
        this.setPreferredSize(new Dimension(PSP_WIDTH, PROFILE_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setBorder(BorderFactory.createLoweredBevelBorder());

        this.init();
    }

    private void init() {
        Profile p = ContentPanel.getProfile();
        JLabel profileName = new JLabel(p.getName());
        CircularButton profilePfp = new CircularButton(p.getProfilePicture()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, ProfilePanel.this.getHeight());
            }
        };

        this.add(profilePfp);
        this.add(profileName);

        profilePfp.addActionListener(evt -> System.out.println("Clicked!"));
        profileName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked!");
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getPsp().getWidth(), PROFILE_HEIGHT);
    }
}
