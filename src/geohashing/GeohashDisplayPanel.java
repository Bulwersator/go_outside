package geohashing;

import org.joda.time.DateTime;
import utils.Coordinate;
import utils.FormatLibrary;
import utils.GeoCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeohashDisplayPanel extends JPanel implements ActionListener {
    private final Coordinate observerLocation;
    private final double maxDistance;
    int graticuleLat;
    int graticuleLon;
    DateTime date;
    HashpointInfoArea hashpointInformationPanel;
    JProgressBar progressBar;
    HashpointInfoArea globalhashInformationPanel;

    private class HashpointInfoArea extends JTextArea {
        GenericGeohashLogic data;

        public HashpointInfoArea() {
            this.setLineWrap(true);
            this.setWrapStyleWord(true);
            this.setRows(4);
        }

        protected boolean setNewData(GenericGeohashLogic newData) {
            this.data = newData;
            String formattedDate = this.data.getGeohashDate().toString(FormatLibrary.ISODate());
            if (!newData.isValid()) {
                this.setText("acquiring Dow Jones Industrial Average data for " + formattedDate + " failed");
                return false;
            }
            return true;
        }

        public void updateGeohashData(GenericGeohashLogic newData) {
            if (!this.setNewData(newData)) {
                return;
            }
            String baseDescription = this.data.generateDescription();
            String distanceDescription = "distance: ";
            Double distance = GeoCalculator.getDistanceBetweenCoordinates(this.data.getHashCoordinate(), GeohashDisplayPanel.this.observerLocation);
            distanceDescription += Math.round(distance);
            distanceDescription += " km";
            if (GeohashDisplayPanel.this.maxDistance > distance) {
                distanceDescription += "\nwithin range of " + GeohashDisplayPanel.this.maxDistance + " km";
            }
            this.setText(baseDescription + "\n" + distanceDescription);
        }
    }

    public GeohashDisplayPanel(Coordinate observerLocationParam, double maxDistanceParam) {
        this.date = new DateTime();
        this.progressBar = new JProgressBar();
        this.observerLocation = observerLocationParam;
        this.graticuleLat = (int) Math.round(this.observerLocation.lat);
        this.graticuleLon = (int) Math.round(this.observerLocation.lon);
        this.maxDistance = maxDistanceParam;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel hashpoint_title_label = new JPanel();
        hashpoint_title_label.add(new JLabel("hashpoint info"));
        this.hashpointInformationPanel = new HashpointInfoArea();
        this.add(hashpoint_title_label);
        this.add(this.hashpointInformationPanel);

        JPanel globalhashTitleLabel = new JPanel();
        globalhashTitleLabel.add(new JLabel("globalhash info"));
        this.globalhashInformationPanel = new HashpointInfoArea();
        this.add(globalhashTitleLabel);
        this.add(this.globalhashInformationPanel);

        JPanel buttons = new JPanel();
        JButton previous = new JButton("<");
        previous.addActionListener(this);
        buttons.add(previous);
        JButton next = new JButton(">");
        next.addActionListener(this);
        buttons.add(next);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        this.add(buttons);
        (new GeohashLoader(new StandardGeohashFactory(), this.hashpointInformationPanel)).execute();
        (new GeohashLoader(new GlobalhashFactory(), this.globalhashInformationPanel)).execute();
        this.add(this.progressBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("<")) {
            this.date = this.date.minusDays(1);
        } else if (e.getActionCommand().equals(">")) {
            this.date = this.date.plusDays(1);
        }
        (new GeohashLoader(new StandardGeohashFactory(), this.hashpointInformationPanel)).execute();
        (new GeohashLoader(new GlobalhashFactory(), this.globalhashInformationPanel)).execute();

    }

    class GeohashLoader extends SwingWorker<GenericGeohashLogic, Void> {
        GeohashFactory generator;
        HashpointInfoArea target;

        GeohashLoader(GeohashFactory generatorPara, HashpointInfoArea targetPara) {
            this.generator = generatorPara;
            this.target = targetPara;
        }

        @Override
        public GenericGeohashLogic doInBackground() {
            GeohashDisplayPanel.this.progressBar.setIndeterminate(true);
            int graticuleLatitude = GeohashDisplayPanel.this.graticuleLat;
            int graticuleLongitude = GeohashDisplayPanel.this.graticuleLon;
            return this.generator.makeGeohash(graticuleLatitude, graticuleLongitude, GeohashDisplayPanel.this.date);
        }

        @Override
        protected void done() {
            try {
                GenericGeohashLogic test = this.get();
                this.target.updateGeohashData(test);
                GeohashDisplayPanel.this.progressBar.setIndeterminate(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
