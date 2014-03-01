import utils.Coordinate;
import utils.FormatLibrary;

import javax.swing.*;
import java.io.*;
import java.util.Properties;
import java.util.prefs.InvalidPreferencesFormatException;

class Settings {
    private JPanel window;
    private double lat = 0;
    private double lon = 0;
    private double maxDistance = 40;
    private Coordinate location;

    Settings() throws IOException, InvalidPreferencesFormatException {
        String settingsFilename = "go_outside.settings";
        File file = new File(settingsFilename);
        try {
            if (file.exists()) {
                Properties q = new Properties();
                q.load(new FileReader(settingsFilename));
                this.lat = Double.parseDouble(q.getProperty("latitude"));
                this.lon = Double.parseDouble(q.getProperty("longitude"));
                this.maxDistance = Double.parseDouble(q.getProperty("distance"));
            } else {
                Properties p = new Properties();
                p.setProperty("latitude", Double.toString(this.lat));
                p.setProperty("longitude", Double.toString(this.lon));
                p.setProperty("distance", Double.toString(this.maxDistance));
                Writer test = new FileWriter(settingsFilename);
                p.store(test, "settings for go_outside program");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        } catch (NumberFormatException | NullPointerException e){
            e.printStackTrace();
            throw new InvalidPreferencesFormatException(e);
        }
        finally {
            this.location = new Coordinate(this.lat, this.lon);
        }
        this.window = this.panelMaker();
    }

    private JPanel panelMaker(){
        JPanel produced = new JPanel();
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
        produced.add(locationSettings);
        produced.add(rangeSettings);
        return produced;
    }

    public JPanel getJPanel() {
        return this.window;
    }

    public Coordinate getLocation() {
        return new Coordinate(this.lat, this.lon);
    }

    public double getMaxDistance() {
        return this.maxDistance;
    }
}
