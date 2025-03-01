package com.player;

//import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.player.utils.Constants.PLAYLIST_PATH;

public class Profile {
    private String name;
//    private Image profilePic;
    private List<Playlist> playlists;

    public Profile(String name) {
        this.name = name;
        this.createProfileFile();
        playlists = new ArrayList<>();
    }

    private void createProfileFile() {
        File f = new File(PLAYLIST_PATH.toString() + File.separatorChar + this.name + ".txt");

        if (f.exists()) {
            System.err.println("Load profile in development");
            return;
        }

        try {
            f.createNewFile();
            System.out.println("Creating file at " + f.getPath());
        } catch (IOException ioe) {
            System.err.println("Couldn't create file " + ioe.getMessage());
        }
    }

//    private void loadProfile() {
//    }

    public String getName() {
        return name;
    }

    public void addPlaylist(String name) {
        this.playlists.add(new Playlist(name));
        System.out.println("Added playlist " + name);
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    //    public Image getProfilePic() {
//        return profilePic;
//    }
}
