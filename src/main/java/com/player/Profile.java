package com.player;

//import java.awt.Image;
import com.player.gui.ContentPanel;
import com.player.utils.PlaylistParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.player.utils.Constants.PLAYLIST_PATH;

public class Profile {
    private String name;
    private File profileFile;
//    private Image profilePic;
    private final List<Playlist> playlists;

    public Profile(String name) {
        this.name = name;
        this.playlists = new ArrayList<>();
        this.createProfileFile();
    }

    private void createProfileFile() {
        this.profileFile = new File(PLAYLIST_PATH.toString() + File.separatorChar + this.name + ".txt");

        if (profileFile.exists()) {
            this.loadProfile();
            return;
        }

        try {
            profileFile.createNewFile();
            System.out.println("Creating file at " + profileFile.getPath());
        } catch (IOException ioe) {
            System.err.println("Couldn't create file " + ioe.getMessage());
        }
    }

    private void loadProfile() {
        try(FileReader fr = new FileReader(this.profileFile);
            BufferedReader br = new BufferedReader(fr)) {
            String line;
            while((line = br.readLine()) != null) {
                this.playlists.add(PlaylistParser.parseString(line));
            }
        } catch (IOException ioe) {
            System.err.println("Something went wrong: " + ioe.getMessage());
        }
    }

    public void savePlaylistToFile() {
        // No need to check if file exists, as it is already created beforehand

        try(FileWriter fw = new FileWriter(this.profileFile);
            BufferedWriter bw = new BufferedWriter(fw)) {

            for(Playlist pl : this.playlists) {
                bw.write(pl.toString() + "\n");
            }

        } catch (IOException ioe) {
            System.err.println("Something went wrong: " + ioe.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void addPlaylist(String name) {
        Playlist toAdd = new Playlist(name);
        this.playlists.add(toAdd);
        ContentPanel.getPsp().getListModel().addElement(toAdd);
        System.out.println("Added playlist " + name);
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    //    public Image getProfilePic() {
//        return profilePic;
//    }
}
