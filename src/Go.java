import geohashing.GeohashDisplayPanel;
import utils.Coordinate;
import utils.FormatLibrary;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class Go extends JDialog {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Go();
            }
        });
    }
    Coordinate location;
    double maxDistance;
    JDialog f;

    /**
     * Displays warning dialog
     * @param text - message displayed to user
     */
    public void showWarning(String text){
        JOptionPane.showMessageDialog(this.f,
                text,
                "Ooops",
                JOptionPane.WARNING_MESSAGE);
    }
    public Go() {
        this.f = new JDialog();

        String settings_filename = "go_outside.settings";
        File file = new File(settings_filename);
        double lat = 0;
        double lon = 0;
        this.maxDistance = 40;
        try {
            if(!file.exists()) {
                Properties p = new Properties();
                p.setProperty("latitude", Double.toString(lat));
                p.setProperty("longitude", Double.toString(lon));
                p.setProperty("distance", Double.toString(this.maxDistance));
                Writer test = new FileWriter(settings_filename);
                p.store(test, "settings for go_outside program");
            } else {
                Properties q = new Properties();
                q.load(new FileReader(settings_filename));
                lat = Double.parseDouble(q.getProperty("latitude"));
                lon = Double.parseDouble(q.getProperty("longitude"));
                this.maxDistance = Double.parseDouble(q.getProperty("distance"));
                this.location = new Coordinate(lat, lon);
            }
        } catch (IOException|java.lang.NumberFormatException|java.lang.NullPointerException e) {
            e.printStackTrace();
            this.showWarning("Processing of options file failed.");
        } finally {
            this.location = new Coordinate(lat, lon);
        }

        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int availableScreenWidth = winSize.width;
        int availableScreenHeight = winSize.height;
        //f.setUndecorated(true);
        //f.setResizable(false);
        this.f.setAlwaysOnTop(true);
        JPanel locationSettings = new JPanel();
        JLabel latitudeLabel = new JLabel("latitude");
        JTextField latitudeInput = new JTextField(FormatLibrary.geographicCoordinate().format(this.location.lat));
        JLabel longitudeLabel = new JLabel("longitude");
        JTextField longitudeInput = new JTextField(FormatLibrary.geographicCoordinate().format(this.location.lon));
        latitudeInput.setColumns(5);
        longitudeInput.setColumns(5);
        locationSettings.add(latitudeLabel);
        locationSettings.add(latitudeInput);
        locationSettings.add(longitudeLabel);
        locationSettings.add(longitudeInput);
        JPanel rangeSettings = new JPanel();
        JLabel rangeLabel = new JLabel("range [km]");
        JTextField rangeInput = new JTextField(Double.toString(this.maxDistance));
        rangeInput.setColumns(5);
        rangeSettings.add(rangeLabel);
        rangeSettings.add(rangeInput);
        this.f.add(locationSettings);
        this.f.add(rangeSettings);
        this.f.add(new JSeparator());
        this.f.add(new GeohashDisplayPanel(this.location, this.maxDistance));
        this.f.getContentPane().setLayout(new BoxLayout(this.f.getContentPane(), BoxLayout.Y_AXIS));
        this.f.setMinimumSize(new Dimension(300, 100));
        this.f.pack();
        //TODO - assumes bottom or right position of system taskbar
        this.f.setLocation(availableScreenWidth - this.f.getWidth(), availableScreenHeight - this.f.getHeight());
        this.f.setVisible(true);
    }
}
