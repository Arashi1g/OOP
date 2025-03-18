package spaceinvaders.menuBar;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
//Added Name to the music
public class MusicMenu {
    private final String[][] predefinedMusic = {
        {"Jungle Trip", "src/spaceinvaders/resources/Jungle Trip - Quincas Moreira.wav"},
        {"Me Gustas Tu", "src/spaceinvaders/resources/Me Gustas Tu - Quincas Moreira.wav"},
        {"Sertao Caboclo", "src/spaceinvaders/resources/Sertao Caboclo - Quincas Moreira.wav"}
    };

    private URL selectedMusicURL;
    private Clip currentClip;
    private JMenu menu;

    public MusicMenu() {
        menu = new JMenu("Music");

        for (String[] music : predefinedMusic) {
            JMenuItem menuItem = new JMenuItem(music[0]);
            menuItem.addActionListener(e -> handleSelection(music[1]));
            menu.add(menuItem);
        }

        JMenuItem customOption = new JMenuItem("Custom Music");
        customOption.addActionListener(e -> selectCustomMusic());
        menu.add(customOption);

        JMenuItem stopMusicOption = new JMenuItem("Stop Music");
        stopMusicOption.addActionListener(e -> stopMusic());
        menu.add(stopMusicOption);
    }

    public JMenu getMenu() {
        return menu;
    }

    public void handleSelection(String option) {
        stopMusic();
        try {
            File musicFile = new File(option);
            if (!musicFile.exists()) {
                JOptionPane.showMessageDialog(null, "Selected file does not exist.");
                return;
            }
            selectedMusicURL = musicFile.toURI().toURL();
            playLocalMusic(selectedMusicURL);
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Invalid music URL.");
        }
    }

    public void selectCustomMusic() {
        String[] options = {"Enter URL", "Upload File"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option for custom music", "Custom Music Selection",
                                                  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                                  null, options, options[0]);

        if (choice == 0) {
            String musicUrl = JOptionPane.showInputDialog("Enter music URL:");
            if (musicUrl != null && !musicUrl.isEmpty()) {
                try {
                    selectedMusicURL = new URL(musicUrl);
                    playStreamingMusic(selectedMusicURL);
                } catch (MalformedURLException e) {
                    JOptionPane.showMessageDialog(null, "Invalid URL format.");
                }
            }
        } else if (choice == 1) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Music Files", "wav"));
            int fileChoice = fileChooser.showOpenDialog(null);
            if (fileChoice == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file.exists()) {
                    handleSelection(file.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid file selection.");
                }
            }
        }
    }

    private void playLocalMusic(URL musicURL) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicURL)) {
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            currentClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Error playing music: " + e.getMessage());
        }
    }

    private void playStreamingMusic(URL musicURL) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicURL)) {
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Streaming error: " + e.getMessage());
        }
    }

    private void stopMusic() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
            currentClip = null;
        }
    }

    public URL getSelectedMusicURL() {
        return selectedMusicURL;
    }
}
