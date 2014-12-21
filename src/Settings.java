import utils.Coordinate;
import utils.FormatLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.prefs.InvalidPreferencesFormatException;

class Settings implements ActionListener {
    private JPanel window;
    static final String SETTINGS_FILENAME = "go_outside.settings";
    private Double latitude;
    private Double longitude;
    private Double distance;
    OptionSegment latitudePanelOption;
    OptionSegment longitudePanelOption;
    OptionSegment distancePanelOption;
    boolean dirty;

    Settings() throws IOException, InvalidPreferencesFormatException {
        this.dirty = false;
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
                this.saveSettingsToFile();
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

    private void saveSettingsToFile() throws IOException {
        Properties p = new Properties();
        p.setProperty("latitude", String.valueOf(this.latitude));
        p.setProperty("longitude", String.valueOf(this.longitude));
        p.setProperty("distance", String.valueOf(this.distance));
        Writer test = new FileWriter(SETTINGS_FILENAME);
        p.store(test, "settings for go_outside program");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("save".equals(e.getActionCommand())) {
            try {
                this.latitude = this.latitudePanelOption.getValue();
                this.longitude = this.longitudePanelOption.getValue();
                this.distance = this.distancePanelOption.getValue();
                this.saveSettingsToFile();
                this.dirty = true; //TODO - redraw stuff
            } catch (IOException e1) {
                e1.printStackTrace();
                //TODDO handle this properly
            }
        }
    }

    class OptionSegment{
        private JPanel segment;
        private JTextField input;
        public JPanel getSegment() {
            return this.segment;
        }
        OptionSegment(String labelName, Double settingValue, DecimalFormat format) throws IOException, InvalidPreferencesFormatException {
            this.segment = new JPanel();
            JLabel label = new JLabel(labelName);
            this.input = new JTextField(format.format(settingValue));
            this.input.setColumns(5);
            this.segment.add(label);
            this.segment.add(this.input);
        }

        public Double getValue() {
            return new Double(this.input.getText());
        }
    }

    private JPanel panelMaker() throws IOException, InvalidPreferencesFormatException {
        JPanel produced = new JPanel();
        //JLabel rangeLabel = new JLabel();
        JPanel positionPanel = new JPanel();
        JPanel distancePanel = new JPanel();
        this.latitudePanelOption = new OptionSegment("latitude", this.latitude, FormatLibrary.geographicCoordinate());
        positionPanel.add(this.latitudePanelOption.getSegment());
        this.longitudePanelOption = new OptionSegment("longitude", this.longitude, FormatLibrary.geographicCoordinate());
        positionPanel.add(this.longitudePanelOption.getSegment());
        this.distancePanelOption = new OptionSegment("range [km]", this.distance, new DecimalFormat("0"));
        distancePanel.add(this.distancePanelOption.getSegment());
        produced.add(positionPanel);
        produced.add(distancePanel);
        JButton save = new JButton("save");
        save.addActionListener(this);
        produced.add(save);
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
