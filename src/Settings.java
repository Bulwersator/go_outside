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
    private Option latitude;
    private Option longitude;
    private Option distance;

    Settings() throws IOException, InvalidPreferencesFormatException {
        try {
            File file = new File(SETTINGS_FILENAME);
            if (!file.exists()) {
                Properties p = new Properties();
                p.setProperty("latitude", Double.toString(0));
                p.setProperty("longitude", Double.toString(0));
                p.setProperty("distance", Double.toString(40));
                Writer test = new FileWriter(SETTINGS_FILENAME);
                p.store(test, "settings for go_outside program");
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new InvalidPreferencesFormatException(e);
        }
        this.window = this.panelMaker();
    }

    class Option{
        private JPanel segment;
        private String name;
        private Double value;
        public JPanel getSegment() {
            return this.segment;
        }
        Option(String nameParam, String labelName, DecimalFormat format) throws IOException, InvalidPreferencesFormatException {
            this.name = nameParam;
            this.segment = new JPanel();
            this.value = null;
            JLabel label = new JLabel(labelName);
            JTextField input = new JTextField(format.format(this.getValue()));
            input.setColumns(5);
            this.segment.add(label);
            this.segment.add(input);
        }
        public double getValue() throws IOException, InvalidPreferencesFormatException {
            if(this.value != null){
                return this.value;
            }
            Properties q = new Properties();
            q.load(new FileReader(SETTINGS_FILENAME));
            String retrieved = q.getProperty(this.name);
            if (retrieved == null){
                throw new InvalidPreferencesFormatException("settings file cannot be parsed by Properties class");
            }
            this.value = Double.parseDouble(retrieved);
            return this.value;
        }
    }

    private JPanel panelMaker() throws IOException, InvalidPreferencesFormatException {
        JPanel produced = new JPanel();
        //JLabel rangeLabel = new JLabel();
        JPanel positionPanel = new JPanel();
        JPanel distancePanel = new JPanel();
        this.latitude = new Option("latitude", "latitude",FormatLibrary.geographicCoordinate());
        positionPanel.add(this.latitude.getSegment());
        this.longitude = new Option("longitude", "longitude",FormatLibrary.geographicCoordinate());
        positionPanel.add(this.longitude.getSegment());
        this.distance = new Option("distance", "range [km]", new DecimalFormat("0"));
        distancePanel.add(this.distance.getSegment());
        produced.add(positionPanel);
        produced.add(distancePanel);
        produced.add(new JButton("save"));
        return produced;
    }

    public JPanel getJPanel() {
        return this.window;
    }

    public Coordinate getLocation() throws IOException, InvalidPreferencesFormatException {
        return new Coordinate(this.latitude.getValue(), this.longitude.getValue());
    }

    public double getMaxDistance() throws IOException, InvalidPreferencesFormatException {
        return this.distance.getValue();
    }
}
