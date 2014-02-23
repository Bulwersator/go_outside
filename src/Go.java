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
        final JTextField textField = new JTextField("loading...");
        f.add(textField);
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setMinimumSize(new Dimension(300, 10));
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
                    textField.setText(test.getResult());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        (new GeohashLoader(lat, lon)).execute();
    }
}
