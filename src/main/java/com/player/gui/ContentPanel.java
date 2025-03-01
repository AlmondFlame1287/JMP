package com.player.gui;

import com.player.Profile;
import com.player.gui.panels.selection.PlaylistSelectionPanel;
import com.player.gui.panels.view.playlist.PlaylistViewPanel;
import com.player.gui.panels.view.song.SongViewPanel;

import javax.swing.*;

public class ContentPanel extends JPanel {
    private static ContentPanel instance = null;
    private static Profile profile;

    private static PlaylistSelectionPanel psp;
    private static PlaylistViewPanel pvp;
    private static SongViewPanel svp;

    public static ContentPanel getInstance() {
        if(instance == null)
            instance = new ContentPanel();
        return instance;
    }

    private ContentPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        psp = new PlaylistSelectionPanel();
        pvp = new PlaylistViewPanel();
        svp = new SongViewPanel();

        this.add(psp);
        this.add(pvp);
        this.add(svp);
    }

    public static void setProfile(Profile profile) {
        ContentPanel.profile = profile;
    }

    public static PlaylistSelectionPanel getPsp() {
        return psp;
    }

    public static PlaylistViewPanel getPvp() {
        return pvp;
    }

    public static SongViewPanel getSvp() {
        return svp;
    }

    public static Profile getProfile() {
        return profile;
    }
}
