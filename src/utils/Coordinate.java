package utils;

import java.io.Serializable;

public class Coordinate implements Serializable {
    public double lat;
    public double lon;

    public Coordinate(double latPara, double lonPara) {
        this.lat = latPara;
        this.lon = lonPara;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "lat=" + this.lat +
                ", lon=" + this.lon +
                '}';
    }
}
