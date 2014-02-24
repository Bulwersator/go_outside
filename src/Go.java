import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class Go {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Go();
            }
        });
    }
    int lat;
    int lon;
    JDialog f;

    /**
     * Displays warning dialog
     * @param text - message displayed to user
     */
    public void showWarning(String text){
        JOptionPane.showMessageDialog(f,
                text,
                "Ooops",
                JOptionPane.WARNING_MESSAGE);
    }
    public Go() {
        f = new JDialog();

        String settings_filename = "go_outside.settings";
        File file = new File(settings_filename);
        try {
            if(!file.exists()) {
                Properties p = new Properties();
                p.setProperty("latitude", "50");
                p.setProperty("longitude", "19");
                Writer test = new FileWriter(settings_filename);
                p.store(test, "settings for go_outside program");
            } else {
                Properties q = new Properties();
                q.load(new FileReader(settings_filename));
                lat = Integer.parseInt(q.getProperty("latitude"));
                lon = Integer.parseInt(q.getProperty("longitude"));
            }
        } catch (IOException|java.lang.NumberFormatException e) {
            e.printStackTrace();
            showWarning("Processing of options file failed.");
            lat = 50;
            lon = 19;
        }

        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int availableScreenWidth = winSize.width;
        int availableScreenHeight = winSize.height;
        //f.setUndecorated(true);
        //f.setResizable(false);
        f.setAlwaysOnTop(true);
        JPanel settings = new JPanel();
        JLabel latitudeLabel = new JLabel("latitude");
        JTextField latitudeInput = new JTextField("");
        JLabel longitudeLabel = new JLabel("longitude");
        JTextField longitudeInput = new JTextField("");
        latitudeInput.setColumns(5);
        longitudeInput.setColumns(5);
        settings.add(latitudeLabel);
        settings.add(latitudeInput);
        settings.add(longitudeLabel);
        settings.add(longitudeInput);
        f.add(settings);
        JPanel hashpoint = new JPanel();
        hashpoint.setLayout(new BoxLayout(hashpoint, BoxLayout.Y_AXIS));
        JPanel hashpoint_title_label = new JPanel();
        hashpoint_title_label.add(new JLabel("hashpoint info"));
        hashpoint.add(hashpoint_title_label);
        final JTextArea hashpointInformationPanel = new JTextArea("loading...");
        hashpointInformationPanel.setLineWrap(true);
        hashpointInformationPanel.setWrapStyleWord(true);
        hashpoint.add(hashpointInformationPanel);
        JPanel buttons = new JPanel();
        buttons.add(new Button("<"));
        buttons.add(new Button(">"));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        hashpoint.add(buttons);
        f.add(hashpoint);
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        //f.setMinimumSize(new Dimension(300, 100));
        f.pack();
        //TODO - assumes bottom or right position of system taskbar
        f.setLocation(availableScreenWidth - f.getWidth(), availableScreenHeight - f.getHeight());
        f.setVisible(true);

        class GeohashLoader extends SwingWorker<Geohash, Void> {
            int lat;
            int lon;
            public GeohashLoader(int lat, int lon) {
                this.lat = lat;
                this.lon = lon;
            }

            @Override
            public Geohash doInBackground() {
                final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                final Calendar c = Calendar.getInstance();
                try {
                    c.setTime(df.parse("2005-05-26"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return new Geohash(lat, lon, c);
            }

            @Override
            protected void done() {
                try {
                    final Geohash test = get();
                    hashpointInformationPanel.setText(test.getResult());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                f.validate();
                f.repaint();
                f.pack();
            }
        }

        (new GeohashLoader(lat, lon)).execute();
    }
}
