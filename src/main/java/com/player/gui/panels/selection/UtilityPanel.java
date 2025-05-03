package com.player.gui.panels.selection;

import com.player.gui.ContentPanel;
import com.player.gui.customs.CircularButton;
import com.player.gui.dialogs.AddPlaylistDialog;
import com.player.utils.SettingsParser;

import javax.swing.*;
import java.awt.*;

import static com.player.utils.Constants.*;

public class UtilityPanel extends JPanel {
    public UtilityPanel() {
        this.setBackground(SettingsParser.getColor("utility") == null ? Color.BLACK : SettingsParser.getColor("utility"));
        this.setPreferredSize(new Dimension(PSP_WIDTH, UTILITY_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        this.setBorder(BorderFactory.createEmptyBorder(0,0,3,0));
        this.init();
    }

    private void init() {
        CircularButton addPlaylist = new CircularButton(Color.DARK_GRAY, Color.LIGHT_GRAY, "+");
        CircularButton removePlaylist = new CircularButton(Color.DARK_GRAY, Color.LIGHT_GRAY, "-");

        this.add(addPlaylist);
        this.add(removePlaylist);
        addPlaylist.addActionListener(evt -> new AddPlaylistDialog());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ContentPanel.getPsp().getWidth(), UTILITY_HEIGHT);
    }
}
