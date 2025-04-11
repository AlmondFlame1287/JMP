package com.player;

//import java.awt.Image;
import com.player.gui.ContentPanel;
import com.player.utils.Constants;
import com.player.utils.PlaylistParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.player.utils.Constants.PLAYLIST_PATH;

public class Profile {
    private String name;
    private File profileFile;
    private File playlistFile;
    private File profilePictureFile;
    private final List<Playlist> playlists;

    public Profile(String name) {
        this.name = name;
        this.playlists = new ArrayList<>();
        this.createFiles();
    }

    private void createFiles() {
        this.playlistFile = new File(PLAYLIST_PATH.toString() + File.separatorChar + this.name + ".txt");
        this.profileFile = new File(Constants.PROFILE_PATH.toString() + File.separatorChar + this.name + ".txt");

        if (profileFile.exists()) {
            this.loadProfile();
            return;
        }

        try {
            profileFile.createNewFile();
            playlistFile.createNewFile();
            System.out.println("Creating files at " + playlistFile.getPath() + "|||" + profileFile.getPath());
        } catch (IOException ioe) {
            System.err.println("Couldn't create file " + ioe.getMessage());
        }
    }

    private void loadProfile() {
        // Only has to read the profile picture path
        try(FileReader fr = new FileReader(this.profileFile);
            BufferedReader br = new BufferedReader(fr)) {
            String pfpPath = br.readLine();

            if(pfpPath != null)
                this.setProfilePictureFile(new File(pfpPath));

        } catch (IOException ioe) {
            System.err.println("Something went wrong with file reading: " + ioe.getMessage());
        }

        try(FileReader fr = new FileReader(this.playlistFile);
            BufferedReader br = new BufferedReader(fr)) {
            String line;
            while((line = br.readLine()) != null) {
                this.playlists.add(PlaylistParser.parseString(line));
            }
        } catch (IOException ioe) {
            System.err.println("Something went wrong: " + ioe.getMessage());
        }
    }

    public void savePfpToFile() {
        try(FileWriter fw = new FileWriter(this.profileFile);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.profilePictureFile.toString());
        } catch (IOException ioe) {
            System.err.println("Something went wrong with saving pfp to file: " + ioe.getMessage());
        }
    }

    public void savePlaylistToFile() {
        // No need to check if file exists, as it is already created beforehand
        try(FileWriter fw = new FileWriter(this.playlistFile);
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

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
        ContentPanel.getPsp().getListModel().addElement(playlist);
        System.out.println("Added playlist " + playlist.getName());
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setProfilePictureFile(File f) {
        this.profilePictureFile = f;
    }

    public ImageIcon getProfilePictureIcon() {
        if(this.profilePictureFile != null) return new ImageIcon(this.profilePictureFile.toString());

        BufferedImage substituteImage = new BufferedImage(50, 50, BufferedImage.TYPE_USHORT_555_RGB);
        Graphics2D g2 = substituteImage.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, substituteImage.getWidth(), substituteImage.getHeight());
        g2.dispose();

        return new ImageIcon(substituteImage);
    }
}
