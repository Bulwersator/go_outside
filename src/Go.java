import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Go {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Go();
            }
        });
    }
    public Go() {
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int availableScreenWidth = winSize.width;
        int availableScreenHeight = winSize.height;
        JDialog f = new JDialog();
        //f.setUndecorated(true);
        f.setResizable(false);
        f.setAlwaysOnTop(true);
        final JTextField textField = new JTextField("loading...");
        f.add(textField);
        f.setMinimumSize(new Dimension(300, 10));
        f.pack();
        //TODO - assumes bottom or right position of system taskbar
        f.setLocation(availableScreenWidth - f.getWidth(), availableScreenHeight - f.getHeight());
        f.setVisible(true);

        class GeohashLoader extends SwingWorker<Geohash, Void> {
            @Override
            public Geohash doInBackground() {
                final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                final Calendar c = Calendar.getInstance();
                try {
                    c.setTime(df.parse("2005-05-26"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return new Geohash(50, 19, c);
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

        (new GeohashLoader()).execute();
    }
}
