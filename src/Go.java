import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class Go extends JDialog implements Observer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Go();
            }
        });
    }

    /**
     * Displays warning dialog
     *
     * @param text - message displayed to user
     */
    private void showWarning(String text) {
        JOptionPane.showMessageDialog(this,
                text,
                "Ooops",
                JOptionPane.WARNING_MESSAGE);
    }

    SettingsModel model;

    private Go() {
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int availableScreenWidth = winSize.width;
        int availableScreenHeight = winSize.height;
        //window.setUndecorated(true);
        //window.setResizable(false);
        this.setAlwaysOnTop(true);
        model = new SettingsModel();
        SettingsPanel settings = new SettingsPanel(model);
        this.add(settings);
        this.add(new JSeparator());
        this.add(new GeohashDisplayPanel(model));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setMinimumSize(new Dimension(300, 100));
        this.pack();
        //TODO - assumes bottom and right position of system taskbar
        this.setLocation(availableScreenWidth - this.getWidth(), availableScreenHeight - this.getHeight());
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(model.getActivityState()){
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }
}
