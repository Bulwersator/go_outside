import geohashing.GeohashDisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.prefs.InvalidPreferencesFormatException;

class Go extends JDialog {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Go();
            }
        });
    }

    /**
     * Displays warning dialog
     *
     * @param text - message displayed to user
     */
    private void showWarning(String text) {
        JOptionPane.showMessageDialog(this,
                text,
                "Ooops",
                JOptionPane.WARNING_MESSAGE);
    }

    private Go() {
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int availableScreenWidth = winSize.width;
        int availableScreenHeight = winSize.height;
        //window.setUndecorated(true);
        //window.setResizable(false);
        this.setAlwaysOnTop(true);
        try {
            Settings settings = new Settings();
            this.add(settings);
            this.add(new JSeparator());
            this.add(new GeohashDisplayPanel(settings.getGeographicCoordinate(), settings.getMaxDistance()));
        } catch (IOException ignored) {
            this.showWarning("Opening of options file failed.");
        } catch (InvalidPreferencesFormatException ignored) {
            this.showWarning("Processing of options file failed.");
        }
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setMinimumSize(new Dimension(300, 100));
        this.pack();
        //TODO - assumes bottom and right position of system taskbar
        this.setLocation(availableScreenWidth - this.getWidth(), availableScreenHeight - this.getHeight());
        this.setVisible(true);
    }
}
