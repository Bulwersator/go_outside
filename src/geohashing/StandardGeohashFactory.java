package geohashing;

import org.joda.time.DateTime;

public class StandardGeohashFactory implements GeohashFactory {
    @Override
    public StandardGeohash makeGeohash(int lat, int lon, DateTime date) {
        return new StandardGeohash(lat, lon, date);
    }
}

