package com.player.gui.dialogs;

import com.player.utils.SettingsParser;

import javax.swing.*;

import java.awt.*;

import static com.player.utils.Constants.F_HEIGHT;
import static com.player.utils.Constants.F_WIDTH;

public class SettingsDialog extends JDialog {
    private static Color selected;
    private static final JComboBox<JPanel> comboBox = new JComboBox<>();

    public SettingsDialog() {
        this.setTitle("Settings");
        this.setSize(F_WIDTH / 2, F_HEIGHT / 2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.getContentPane().setLayout(new FlowLayout());
        this.setVisible(true);
        this.init();
    }

    public static void addPanelToComboBox(JPanel panel) {
        comboBox.addItem(panel);
    }

    private void init() {
        comboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> new JLabel(value.getName()));

        final JLabel label = new JLabel("Panel to customize:");
        final JButton pickColor = new JButton("Pick a color");
        final JButton addDefaultDirs = new JButton("Add dirs to scan");
        final JButton done = new JButton("Done");
        final DefaultListModel<String> model = new DefaultListModel<>();
        final JList<String> defaultDirs = new JList<>(model);

        label.setForeground(Color.WHITE);
        pickColor.addActionListener(evt -> onColorPicking());
        defaultDirs.setEnabled(false);
        addDefaultDirs.addActionListener(evt -> onAddDefaultDirs(model));
        done.addActionListener(evt -> onDonePressed());

        this.getContentPane().add(label);
        this.getContentPane().add(comboBox);
        this.getContentPane().add(pickColor);
        this.getContentPane().add(done);
        this.getContentPane().add(addDefaultDirs);
        this.getContentPane().add(defaultDirs);
    }

    private void onColorPicking() {
        selected = JColorChooser.showDialog(this, "Pick a color", Color.WHITE);
    }

    private void onAddDefaultDirs(DefaultListModel<String> model) {
        final JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        final int res = jfc.showDialog(this, "OK");

        if(res != JFileChooser.APPROVE_OPTION) return;
        final String filePath = jfc.getSelectedFile().toString();

        SettingsParser.addScanDir(filePath);
        model.addElement(filePath);
    }

    private void onDonePressed() {
        if(selected == null) {
            this.dispose();
            return;
        }

        final JPanel p = (JPanel) comboBox.getSelectedItem();
        p.setBackground(selected);

        SettingsParser.saveSettings();
    }
}
