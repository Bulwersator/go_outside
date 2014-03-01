package geohashing;

import org.joda.time.DateTime;

interface GeohashFactory {
    GenericGeohashLogic makeGeohash(int lat, int lon, DateTime date);
}
