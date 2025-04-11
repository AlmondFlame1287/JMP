package com.player.gui.panels.selection;

import com.player.Profile;
import com.player.gui.ContentPanel;
import com.player.gui.customs.CircularButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.player.utils.Constants.*;

public class ProfilePanel extends JPanel {
    private CircularButton profilePfp;

    public ProfilePanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.init();
    }

    private void init() {
        Profile p = ContentPanel.getProfile();
        JLabel profileName = new JLabel(p.getName());
        profileName.setForeground(Color.WHITE);
        this.profilePfp = new CircularButton(p.getProfilePictureIcon().getImage()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, ProfilePanel.this.getHeight());
            }
        };

        this.add(this.profilePfp);
        this.add(profileName);

        this.profilePfp.addActionListener(evt -> this.chooseProfilePicture());
        profileName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked!");
            }
        });
    }

    private void chooseProfilePicture() {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png", "jpeg");
        jfc.setFileFilter(filter);

        final int result = jfc.showOpenDialog(this);

        if(result != JFileChooser.APPROVE_OPTION) return;

        Profile p = ContentPanel.getProfile();
        p.setProfilePictureFile(jfc.getSelectedFile());
        this.profilePfp.setImg(p.getProfilePictureIcon().getImage());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getPsp().getWidth(), PROFILE_HEIGHT);
    }
}
