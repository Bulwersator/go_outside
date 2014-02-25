import javax.swing.*;
import java.awt.*;
import java.io.*;
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
        JPanel locationSettings = new JPanel();
        JLabel latitudeLabel = new JLabel("latitude");
        JTextField latitudeInput = new JTextField("");
        JLabel longitudeLabel = new JLabel("longitude");
        JTextField longitudeInput = new JTextField("");
        latitudeInput.setColumns(5);
        longitudeInput.setColumns(5);
        locationSettings.add(latitudeLabel);
        locationSettings.add(latitudeInput);
        locationSettings.add(longitudeLabel);
        locationSettings.add(longitudeInput);
        JPanel rangeSettings = new JPanel();
        JLabel rangeLabel = new JLabel("range [km]");
        JTextField rangeInput = new JTextField("");
        rangeInput.setColumns(5);
        rangeSettings.add(rangeLabel);
        rangeSettings.add(rangeInput);
        f.add(locationSettings);
        f.add(rangeSettings);
        f.add(new JSeparator());
        f.add(new GeohashDisplayPanel(lat, lon, this));
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setMinimumSize(new Dimension(300, 100));
        f.pack();
        //TODO - assumes bottom or right position of system taskbar
        f.setLocation(availableScreenWidth - f.getWidth(), availableScreenHeight - f.getHeight());
        f.setVisible(true);
    }
    public void updateDisplay(){
        f.revalidate();
        f.pack();
        f.repaint();
    }
}
