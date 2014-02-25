package geohashing;

import org.joda.time.DateTime;
import utils.FormatLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GeohashDisplayPanel extends JPanel implements ActionListener {
    int lat;
    int lon;
    DateTime date;
    HashpointInfoArea hashpointInformationPanel;
    JProgressBar progressBar;
    HashpointInfoArea globalhashInformationPanel;

    private abstract class GenericHashPoint extends JTextArea {
        GenericGeohashLogic data;
        private GenericHashPoint() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setRows(2);
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

    }
    private class HashpointInfoArea extends GenericHashPoint{
        public void updateGeohashData(GenericGeohashLogic newData) {
            if(!this.setNewData(newData)) {
                return;
            }
            String formattedDate = this.data.getGeohashDate().toString(FormatLibrary.ISODate());
            String result;
            DecimalFormat decim = FormatLibrary.geographicCoordinate();
            result = "graticule " + this.data.getLat() + ", " + this.data.getLon() + " on " + formattedDate + ":\n";
            result += decim.format(this.data.getHashLat()) + " " + decim.format(this.data.getHashLon());
            this.setText(result);
        }
    }
    public GeohashDisplayPanel(int lat, int lon) {
        this.date = new DateTime();
        this.progressBar = new JProgressBar();
        this.lat = lat;
        this.lon = lon;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel hashpoint_title_label = new JPanel();
        hashpoint_title_label.add(new JLabel("hashpoint info"));
        this.hashpointInformationPanel = new HashpointInfoArea();
        this.add(hashpoint_title_label);
        this.add(hashpointInformationPanel);

        JPanel globalhashTitleLabel = new JPanel();
        globalhashTitleLabel.add(new JLabel("globalhash info"));
        this.globalhashInformationPanel = new HashpointInfoArea();
        this.add(globalhashTitleLabel);
        this.add(globalhashInformationPanel);

        JPanel buttons = new JPanel();
        JButton previous = new JButton("<");
        previous.addActionListener(this);
        buttons.add(previous);
        JButton next = new JButton(">");
        next.addActionListener(this);
        buttons.add(next);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        this.add(buttons);
        (new GeohashLoader()).execute();
        this.add(progressBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("<")) {
            date = date.minusDays(1);
            (new GeohashLoader()).execute();
        } else if(e.getActionCommand().equals(">")) {
            date = date.plusDays(1);
            (new GeohashLoader()).execute();
        }

    }

    class GeohashLoader extends SwingWorker<GenericGeohashLogic, Void> {
        @Override
        public GenericGeohashLogic doInBackground() {
            progressBar.setIndeterminate(true);
            return new GenericGeohashLogic(lat, lon, date);
        }
        @Override
        protected void done() {
            try {
                GenericGeohashLogic test = get();
                hashpointInformationPanel.updateGeohashData(test);
                progressBar.setIndeterminate(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
