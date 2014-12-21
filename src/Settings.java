import utils.Coordinate;
import utils.FormatLibrary;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.prefs.InvalidPreferencesFormatException;

class Settings {
    private JPanel window;
    static final String SETTINGS_FILENAME = "go_outside.settings";
    private Double latitude;
    private Double longitude;
    private Double distance;

    Settings() throws IOException, InvalidPreferencesFormatException {
        try {
            File file = new File(SETTINGS_FILENAME);
            if (file.exists()) {
                this.latitude = Settings.getSetting("latitude");
                this.longitude = Settings.getSetting("longitude");
                this.distance = Settings.getSetting("distance");
            } else {
                this.latitude = 0.0;
                this.longitude = 0.0;
                this.distance = 40.0;
                this.saveSettingsFile();
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new InvalidPreferencesFormatException(e);
        }
        this.window = this.panelMaker();
    }

    private static Double getSetting(String name) throws IOException, InvalidPreferencesFormatException {
        Properties q = new Properties();
        q.load(new FileReader(SETTINGS_FILENAME));
        String retrieved = q.getProperty(name);
        if (retrieved == null){
            throw new InvalidPreferencesFormatException("settings file cannot be parsed by Properties class");
        }
        return Double.parseDouble(retrieved);
    }

    private void saveSettingsFile() throws IOException {
        Properties p = new Properties();
        p.setProperty("latitude", String.valueOf(this.latitude));
        p.setProperty("longitude", String.valueOf(this.longitude));
        p.setProperty("distance", String.valueOf(this.distance));
        Writer test = new FileWriter(SETTINGS_FILENAME);
        p.store(test, "settings for go_outside program");
    }

    class Option{
        private JPanel segment;
        private Double value;
        public JPanel getSegment() {
            return this.segment;
        }
        Option(String labelName, Double settingValue, DecimalFormat format) throws IOException, InvalidPreferencesFormatException {
            this.segment = new JPanel();
            this.value = null;
            JLabel label = new JLabel(labelName);
            JTextField input = new JTextField(format.format(settingValue));
            input.setColumns(5);
            this.segment.add(label);
            this.segment.add(input);
        }
    }

    private JPanel panelMaker() throws IOException, InvalidPreferencesFormatException {
        JPanel produced = new JPanel();
        //JLabel rangeLabel = new JLabel();
        JPanel positionPanel = new JPanel();
        JPanel distancePanel = new JPanel();
        Option latitudePanelOption = new Option("latitude", this.latitude, FormatLibrary.geographicCoordinate());
        positionPanel.add(latitudePanelOption.getSegment());
        Option longitudePanelOption = new Option("longitude", this.longitude, FormatLibrary.geographicCoordinate());
        positionPanel.add(longitudePanelOption.getSegment());
        Option distanceOption = new Option("range [km]", this.distance, new DecimalFormat("0"));
        distancePanel.add(distanceOption.getSegment());
        produced.add(positionPanel);
        produced.add(distancePanel);
        produced.add(new JButton("save"));
        return produced;
    }

    public JPanel getJPanel() {
        return this.window;
    }

    public Coordinate getLocation() throws IOException, InvalidPreferencesFormatException {
        return new Coordinate(this.latitude, this.longitude);
    }

    public double getMaxDistance() throws IOException, InvalidPreferencesFormatException {
        return this.distance;
    }
}
