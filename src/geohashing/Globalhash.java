package geohashing;

import org.joda.time.DateTime;
import utils.Coordinate;
import utils.FormatLibrary;

import java.text.DecimalFormat;

public class Globalhash extends GenericGeohashLogic {
    /**
     * Fetches data about globalhash on specified date.
     *
     * @param graticuleLat   ignored parameter as there is a single globalhash on a given day
     * @param graticuleLon   ignored parameter as there is a single globalhash on a given day
     * @param globalhashDate Date of globalhash.
     */
    @SuppressWarnings("UnusedParameters")
    public Globalhash(int graticuleLat, int graticuleLon, DateTime globalhashDate) {
        this(globalhashDate);
    }

    /**
     * Fetches data about globalhash on specified date.
     *
     * @param globalhashDate Date of globalhash.
     */
    public Globalhash(DateTime globalhashDate) {
        super(0, 0, globalhashDate);
    }

    protected boolean useMarketDataFromPreviousDay() {
        return true;
    }

    protected void processExistingData(Coordinate rawHashCoordinates) {
        this.result.lat = 180 * rawHashCoordinates.lat - 90;
        this.result.lon = 360 * rawHashCoordinates.lon - 180;
    }

    @Override
    public String generateDescription() {
        String formattedDate = this.getGeohashDate().toString(FormatLibrary.ISODate());
        String result;
        DecimalFormat decim = FormatLibrary.geographicCoordinate();
        result = "globalhash on " + formattedDate + ":\n";
        result += decim.format(this.getHashLat()) + " " + decim.format(this.getHashLon());
        return result;
    }

    @Override
    public int getGraticuleLat() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getGraticuleLon() {
        throw new UnsupportedOperationException();
    }
}
