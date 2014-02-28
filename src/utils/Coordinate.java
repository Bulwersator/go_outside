package utils;

public class Coordinate {
    public double lat;
    public double lon;

    public Coordinate(double latPara, double lonPara) {
        this.lat = latPara;
        this.lon = lonPara;
    }

    public Coordinate() {
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "lat=" + this.lat +
                ", lon=" + this.lon +
                '}';
    }
}
