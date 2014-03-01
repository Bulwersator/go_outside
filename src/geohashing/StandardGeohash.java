package geohashing;

import org.joda.time.DateTime;
import utils.Coordinate;
import utils.FormatLibrary;

import java.text.DecimalFormat;

public class StandardGeohash extends GenericGeohashLogic {
    /**
     * Fetches data about hashpoint in specified graticule, on specified date.
     *
     * @param graticuleLat     Latitude of graticule
     * @param graticuleLon     Longitude of graticule
     * @param geohashDateParam Date of geohash.
     */
    public StandardGeohash(int graticuleLat, int graticuleLon, DateTime geohashDateParam) {
        super(graticuleLat, graticuleLon, geohashDateParam);
    }

    protected boolean useMarketDataFromPreviousDay() {
        return this.lon > -30 && isDateAfterTimeZoneRuleChange(this.geohashDate);
    }

    protected void processExistingData(Coordinate rawHashCoordinates) {
        if (this.lat < 0) {
            this.result.lat = this.lat - rawHashCoordinates.lat;
        } else {
            this.result.lat = this.lat + rawHashCoordinates.lat;
        }
        if (this.lon < 0) {
            this.result.lon = this.lon - rawHashCoordinates.lon;
        } else {
            this.result.lon = this.lon + rawHashCoordinates.lon;
        }
    }

    @Override
    public String generateDescription() {
        String formattedDate = this.getGeohashDate().toString(FormatLibrary.ISODate());
        String result;
        DecimalFormat decim = FormatLibrary.geographicCoordinate();
        result = "graticule " + this.getGraticuleLat() + ", " + this.getGraticuleLon() + " on " + formattedDate + ":\n";
        result += decim.format(this.getHashLat()) + " " + decim.format(this.getHashLon());
        return result;
    }
}
