package geohashing;

import org.joda.time.DateTime;

public class GlobalhashFactory implements GeohashFactory {
    @Override
    public Globalhash makeGeohash(int lat, int lon, DateTime date) {
        return new Globalhash(lat, lon, date);
    }
}
