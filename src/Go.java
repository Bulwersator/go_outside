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

    private JDialog window;

    /**
     * Displays warning dialog
     *
     * @param text - message displayed to user
     */
    private void showWarning(String text) {
        JOptionPane.showMessageDialog(this.window,
                text,
                "Ooops",
                JOptionPane.WARNING_MESSAGE);
    }

    private Go() {
        this.window = new JDialog();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int availableScreenWidth = winSize.width;
        int availableScreenHeight = winSize.height;
        //window.setUndecorated(true);
        //window.setResizable(false);
        this.window.setAlwaysOnTop(true);
        try {
            Settings settings = new Settings();
            this.window.add(settings.getJPanel());
            this.window.add(new JSeparator());
            this.window.add(new GeohashDisplayPanel(settings.getLocation(), settings.getMaxDistance()));
        } catch (IOException ignored) {
            this.showWarning("Opening of options file failed.");
        } catch (InvalidPreferencesFormatException ignored) {
            this.showWarning("Processing of options file failed.");
        }
        this.window.getContentPane().setLayout(new BoxLayout(this.window.getContentPane(), BoxLayout.PAGE_AXIS));
        this.window.setMinimumSize(new Dimension(300, 100));
        this.window.pack();
        //TODO - assumes bottom and right position of system taskbar
        this.window.setLocation(availableScreenWidth - this.window.getWidth(), availableScreenHeight - this.window.getHeight());
        this.window.setVisible(true);
    }
}
