package geohashing;

import org.joda.time.DateTime;
import utils.Coordinate;
import utils.FormatLibrary;
import utils.GeoCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class GeohashDisplayPanel extends JPanel implements ActionListener {
    private final Coordinate observerLocation;
    private final double maxDistance;
    private int graticuleLat;
    private int graticuleLon;
    private DateTime date;
    private HashpointInfoArea hashpointInformationPanel;
    private JProgressBar progressBar;
    private HashpointInfoArea globalhashInformationPanel;

    private class HashpointInfoArea extends JTextArea {
        GenericGeohashLogic data;

        HashpointInfoArea() {
            this.setLineWrap(true);
            this.setWrapStyleWord(true);
            this.setRows(4);
            this.data = null;
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
            if (GeohashDisplayPanel.this.maxDistance > distance) {
                distanceDescription += System.lineSeparator();
                distanceDescription += "within range of " + GeohashDisplayPanel.this.maxDistance + " km";
            }
            this.setText(baseDescription + System.lineSeparator() + distanceDescription);
        }
    }

    public GeohashDisplayPanel(Coordinate observerLocationParam, double maxDistanceParam) {
        this.date = new DateTime();
        this.progressBar = new JProgressBar();
        this.observerLocation = observerLocationParam;
        this.graticuleLat = (int) Math.round(this.observerLocation.lat);
        this.graticuleLon = (int) Math.round(this.observerLocation.lon);
        this.maxDistance = maxDistanceParam;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel hashpointTitleLabel = new JPanel();
        hashpointTitleLabel.add(new JLabel("hashpoint info"));
        this.hashpointInformationPanel = new HashpointInfoArea();
        this.add(hashpointTitleLabel);
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
