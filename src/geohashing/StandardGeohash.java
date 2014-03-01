package geohashing;

import org.joda.time.DateTime;
import utils.Coordinate;
import utils.FormatLibrary;

import java.text.DecimalFormat;

public class StandardGeohash extends GenericGeohashLogic {
    /**
     * Fetches data about hashpoint in specified graticule, on specified date.
     *
     * @param graticuleLatParam Latitude of graticule
     * @param graticuleLonParam Longitude of graticule
     * @param geohashDateParam  Date of geohash.
     */
    public StandardGeohash(int graticuleLatParam, int graticuleLonParam, DateTime geohashDateParam) {
        super(graticuleLatParam, graticuleLonParam, geohashDateParam);
    }

    @Override
    protected boolean shouldUseDowFromPreviousDay() {
        return this.graticuleLon > -30 && isDateAfterTimeZoneRuleChange(this.geohashDate);
    }

    @Override
    protected void processExistingData(Coordinate rawHashCoordinates) {
        double lat, lon;
        if (this.graticuleLat < 0) {
            lat = this.graticuleLat - rawHashCoordinates.lat;
        } else {
            lat = this.graticuleLat + rawHashCoordinates.lat;
        }
        if (this.graticuleLon < 0) {
            lon = this.graticuleLon - rawHashCoordinates.lon;
        } else {
            lon = this.graticuleLon + rawHashCoordinates.lon;
        }
        this.generatedLocation = new Coordinate(lat, lon);
    }

    @Override
    public String generateDescription() {
        String formattedDate = this.getGeohashDate().toString(FormatLibrary.ISODate());
        String result;
        DecimalFormat decim = FormatLibrary.geographicCoordinate();
        result = "graticule " + this.getGraticuleLat() + ", " + this.getGraticuleLon() + " on " + formattedDate + ":";
        result += System.lineSeparator();
        result += decim.format(this.getHashLat()) + " " + decim.format(this.getHashLon());
        return result;
    }
}
