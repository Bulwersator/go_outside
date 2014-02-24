import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class GeohashDisplayPanel extends JPanel implements ActionListener {
    final int lat;
    final int lon;
    final Go parent;
    final Calendar date;
    final JTextArea hashpointInformationPanel;
    final JProgressBar progressBar;
    public GeohashDisplayPanel(final int lat, final int lon, final Go parent) {
        this.date = Calendar.getInstance();
        this.progressBar = new JProgressBar();
        this.parent = parent;//passing parent here is probably terrible hack and should be fixed (TODO)
        //note that it is passed only to trigger repainting window
        this.lat = lat;
        this.lon = lon;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel hashpoint_title_label = new JPanel();
        hashpoint_title_label.add(new JLabel("hashpoint info"));
        this.add(hashpoint_title_label);
        this.hashpointInformationPanel = new JTextArea("");
        this.hashpointInformationPanel.setLineWrap(true);
        this.hashpointInformationPanel.setWrapStyleWord(true);
        this.hashpointInformationPanel.setRows(2);
        this.add(hashpointInformationPanel);
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
            date.add(Calendar.DAY_OF_YEAR, -1);
            (new GeohashLoader()).execute();
        } else if(e.getActionCommand().equals(">")) {
            date.add(Calendar.DAY_OF_YEAR, 1);
            (new GeohashLoader()).execute();
        }

    }

    class GeohashLoader extends SwingWorker<GeohashLogic, Void> {
        @Override
        public GeohashLogic doInBackground() {
            progressBar.setIndeterminate(true);
            return new GeohashLogic(lat, lon, date);
        }
        @Override
        protected void done() {
            try {
                final GeohashLogic test = get();
                hashpointInformationPanel.setText(test.getResult());
                progressBar.setIndeterminate(false);
                parent.updateDisplay();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
