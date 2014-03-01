package geohashing;

import org.joda.time.DateTime;

abstract class GeohashFactory {
    public abstract GenericGeohashLogic makeGeohash(int lat, int lon, DateTime date);
}
