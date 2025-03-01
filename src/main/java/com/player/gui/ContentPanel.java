package com.player.gui;

import com.player.gui.panels.selection.PlaylistSelectionPanel;
import com.player.gui.panels.view.playlist.PlaylistViewPanel;
import com.player.gui.panels.view.song.SongViewPanel;

import javax.swing.*;

public class ContentPanel extends JPanel {
    private static ContentPanel instance = null;

    private final PlaylistSelectionPanel psp;
    private final PlaylistViewPanel pvp;
    private final SongViewPanel svp;

    public static ContentPanel getInstance() {
        if(instance == null)
            instance = new ContentPanel();
        return instance;
    }

    private ContentPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        this.psp = new PlaylistSelectionPanel();
        this.pvp = new PlaylistViewPanel();
        this.svp = new SongViewPanel();

        this.add(psp);
        this.add(pvp);
        this.add(svp);
    }

    public PlaylistSelectionPanel getPsp() {
        return psp;
    }

    public PlaylistViewPanel getPvp() {
        return pvp;
    }

    public SongViewPanel getSvp() {
        return svp;
    }
}
