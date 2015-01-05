import utils.Coordinate;
import utils.FormatLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.prefs.InvalidPreferencesFormatException;

class SettingsPanel extends JPanel implements ActionListener {
    OptionSegment latitudePanelOption;
    OptionSegment longitudePanelOption;
    OptionSegment minorDistancePanelOption;
    OptionSegment majorDistancePanelOption;
    SettingsModel config;

    SettingsPanel(SettingsModel model) {
        config = model;
        this.panelMaker();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("save".equals(e.getActionCommand())) {
            double latitude = this.latitudePanelOption.getValue();
            double longitude = this.longitudePanelOption.getValue();
            double minorDistance = this.minorDistancePanelOption.getValue();
            double majorDistance = this.majorDistancePanelOption.getValue();
            config.setLocation(new Coordinate(latitude, longitude));
            config.setMinorDistance(minorDistance);
            config.setMajorDistance(majorDistance);
        }
    }

    class OptionSegment implements Serializable{
        private JPanel segment;
        private JTextField input;
        public JPanel getSegment() {
            return this.segment;
        }
        OptionSegment(String labelName, Double settingValue, DecimalFormat format) {
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

    private void panelMaker() {
        JPanel positionPanel = new JPanel();
        JPanel distancePanel = new JPanel();
        double latitude = config.getLocation().latitude;
        double longitude = config.getLocation().longitude;
        double minorDistance = config.getMinorDistance();
        double majorDistance = config.getMajorDistance();
        this.latitudePanelOption = new OptionSegment("latitude", latitude, FormatLibrary.geographicCoordinate());
        positionPanel.add(this.latitudePanelOption.getSegment());
        this.longitudePanelOption = new OptionSegment("longitude", longitude, FormatLibrary.geographicCoordinate());
        positionPanel.add(this.longitudePanelOption.getSegment());
        this.minorDistancePanelOption = new OptionSegment("range [km]", minorDistance, new DecimalFormat("0"));
        this.majorDistancePanelOption = new OptionSegment("range for special events [km]", majorDistance, new DecimalFormat("0"));
        distancePanel.add(this.minorDistancePanelOption.getSegment());
        distancePanel.add(this.majorDistancePanelOption.getSegment());
        this.add(positionPanel);
        this.add(distancePanel);
        JButton save = new JButton("save");
        save.addActionListener(this);
        this.add(save);
    }
}
