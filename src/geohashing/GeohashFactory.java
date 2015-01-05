package geohashing;

import org.joda.time.DateTime;

public interface GeohashFactory {
    GenericGeohashLogic makeGeohash(int lat, int lon, DateTime date);
}
