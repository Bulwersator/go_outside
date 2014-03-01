package geohashing;

import org.joda.time.DateTime;

class GlobalhashFactory implements GeohashFactory {
    @Override
    public Globalhash makeGeohash(int lat, int lon, DateTime date) {
        return new Globalhash(lat, lon, date);
    }
}
