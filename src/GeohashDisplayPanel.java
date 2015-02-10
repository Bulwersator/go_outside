import org.joda.time.DateTime;
import utils.Coordinate;
import utils.FormatLibrary;
import utils.GeoCalculator;
import geohashing.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;


public class GeohashDisplayPanel extends JPanel implements ActionListener, Observer{
    private Coordinate observerLocation;
    private int graticuleLat;
    private int graticuleLon;
    private DateTime date;
    private HashpointInfoArea hashpointInformationPanel;
    private JProgressBar progressBar;
    private HashpointInfoArea globalhashInformationPanel;
    private SettingsModel model;

    @Override
    public void update(Observable o, Object arg) {
        this.observerLocation = model.getLocation();
        this.graticuleLat = (int) Math.round(this.observerLocation.latitude);
        this.graticuleLon = (int) Math.round(this.observerLocation.longitude);
        this.hashpointInformationPanel.distanceAllowed = model.getMinorDistance();
        this.globalhashInformationPanel.distanceAllowed = model.getMajorDistance();
        (new GeohashLoader(new StandardGeohashFactory(), this.hashpointInformationPanel)).execute();
        (new GeohashLoader(new GlobalhashFactory(), this.globalhashInformationPanel)).execute();
    }

    private class HashpointInfoArea extends JTextArea {
        GenericGeohashLogic data;
        double distanceAllowed;

        HashpointInfoArea(double distance) {
            this.setLineWrap(true);
            this.setWrapStyleWord(true);
            this.setRows(4);
            this.data = null;
            this.distanceAllowed = distance;
        }

        boolean setNewData(GenericGeohashLogic newData) {
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
            if (this.distanceAllowed > distance) {
                distanceDescription += System.lineSeparator();
                distanceDescription += "within range of " + this.distanceAllowed + " km";
                model.setActivityState(true);
            }
            this.setText(baseDescription + System.lineSeparator() + distanceDescription);
        }
    }

    public GeohashDisplayPanel(SettingsModel model) {
        this.model = model;
        this.model.addObserver(this);
        this.date = new DateTime();
        this.progressBar = new JProgressBar();
        this.observerLocation = model.getLocation();
        this.graticuleLat = (int) Math.round(this.observerLocation.latitude);
        this.graticuleLon = (int) Math.round(this.observerLocation.longitude);
        buildPanel();
    }

    public void buildPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel hashpointTitleLabel = new JPanel();
        hashpointTitleLabel.add(new JLabel("hashpoint info"));
        this.hashpointInformationPanel = new HashpointInfoArea(this.model.getMinorDistance());
        this.add(hashpointTitleLabel);
        this.add(this.hashpointInformationPanel);

        JPanel globalhashTitleLabel = new JPanel();
        globalhashTitleLabel.add(new JLabel("globalhash info"));
        this.globalhashInformationPanel = new HashpointInfoArea(this.model.getMajorDistance());
        this.add(globalhashTitleLabel);
        this.add(this.globalhashInformationPanel);

        JPanel buttons = new JPanel();
        JButton previous = new JButton("<");
        previous.addActionListener(this);
        buttons.add(previous);
        JButton next = new JButton(">");
        next.addActionListener(this);
        buttons.add(next);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        this.add(buttons);
        (new GeohashLoader(new StandardGeohashFactory(), this.hashpointInformationPanel)).execute();
        (new GeohashLoader(new GlobalhashFactory(), this.globalhashInformationPanel)).execute();
        this.add(this.progressBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("<".equals(e.getActionCommand())) {
            this.date = this.date.minusDays(1);
        } else if (">".equals(e.getActionCommand())) {
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
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
