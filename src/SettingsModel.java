import utils.Coordinate;

import java.io.Serializable;
import java.util.Observable;
import java.util.prefs.Preferences;

class SettingsModel extends Observable implements Serializable {
    private static class SettingsPersister {
        private static Preferences preferencesNode = Preferences.userRoot().node("MateuszKonieczny").node("geohashing");

        private static final String LATITUDE_NAME = "latitude";
        private static final double DEFAULT_LATITUDE = 0;
        private static final String LONGITUDE_NAME = "longitude";
        private static final double DEFAULT_LONGITUDE = 0;
        private static final String MINOR_DISTANCE_NAME = "minor_distance";
        private static final double DEFAULT_MINOR_DISTANCE = 30;
        private static final String MAJOR_DISTANCE_NAME = "major_distance";
        private static final double DEFAULT_MAJOR_DISTANCE = 100;

        public static Coordinate getLocation() {
            double latitude = preferencesNode.getDouble(LATITUDE_NAME, DEFAULT_LATITUDE);
            double longitude = preferencesNode.getDouble(LONGITUDE_NAME, DEFAULT_LONGITUDE);
            return new Coordinate(latitude, longitude);
        }
        public static void putLocation(Coordinate data) {
            preferencesNode.putDouble(LATITUDE_NAME, data.latitude);
            preferencesNode.putDouble(LONGITUDE_NAME, data.longitude);
        }

        public static double getMinorDistace() {
            return preferencesNode.getDouble(MINOR_DISTANCE_NAME, DEFAULT_MINOR_DISTANCE);
        }
        public static void putMinorDistace(double data) {
            preferencesNode.putDouble(MINOR_DISTANCE_NAME, data);
        }

        public static double getMajorDistace() {
            return preferencesNode.getDouble(MAJOR_DISTANCE_NAME, DEFAULT_MAJOR_DISTANCE);
        }
        public static void putMajorDistace(double data) {
            preferencesNode.putDouble(MAJOR_DISTANCE_NAME, data);
        }

    }
    private Coordinate location;
    private double minorDistance;
    private double majorDistance;

    SettingsModel(){
        this.location = SettingsPersister.getLocation();
        this.minorDistance = SettingsPersister.getMinorDistace();
        this.majorDistance = SettingsPersister.getMajorDistace();
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        if(this.location.equals(location)){
            return; //TODO equals is not working properly
        }
        this.location = location;
        SettingsPersister.putLocation(location);
        setChanged();
        notifyObservers();
    }

    public double getMinorDistance() {
        return minorDistance;
    }

    public void setMinorDistance(double minorDistance) {
        if(this.minorDistance == minorDistance){
            return;
        }
        this.minorDistance = minorDistance;
        SettingsPersister.putMinorDistace(minorDistance);
        setChanged();
        notifyObservers();
    }

    public double getMajorDistance() {
        return majorDistance;
    }

    public void setMajorDistance(double majorDistance) {
        if(this.majorDistance == majorDistance){
            return;
        }
        this.majorDistance = majorDistance;
        SettingsPersister.putMajorDistace(majorDistance);
        setChanged();
        notifyObservers();
    }
}
