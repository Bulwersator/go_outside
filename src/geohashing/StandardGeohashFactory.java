package geohashing;

import org.joda.time.DateTime;

class StandardGeohashFactory extends GeohashFactory {
    public StandardGeohash makeGeohash(int lat, int lon, DateTime date) {
        return new StandardGeohash(lat, lon, date);
    }
}

