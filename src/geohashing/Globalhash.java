package geohashing;

import org.joda.time.DateTime;
import utils.Coordinate;
import utils.FormatLibrary;

import java.text.DecimalFormat;

public class Globalhash extends GenericGeohashLogic {
    /**
     * Fetches data about globalhash on specified date.
     *
     * @param graticuleLatParam ignored parameter as there is a single globalhash on a given day
     * @param graticuleLonParam ignored parameter as there is a single globalhash on a given day
     * @param globalhashDate    Date of globalhash.
     */
    @SuppressWarnings("UnusedParameters")
    public Globalhash(int graticuleLatParam, int graticuleLonParam, DateTime globalhashDate) {
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

    @Override
    protected boolean shouldUseDowFromPreviousDay() {
        return true;
    }

    @Override
    protected void processExistingData(Coordinate rawHashCoordinates) {
        double lat, lon;
        lat = 180 * rawHashCoordinates.lat - 90;
        lon = 360 * rawHashCoordinates.lon - 180;
        this.generatedLocation = new Coordinate(lat, lon);
    }

    @Override
    public String generateDescription() {
        String formattedDate = this.getGeohashDate().toString(FormatLibrary.ISODate());
        DecimalFormat decim = FormatLibrary.geographicCoordinate();
        String result;
        result = "globalhash on " + formattedDate + ":";
        result += System.lineSeparator();
        result += decim.format(this.getHashLat()) + " " + decim.format(this.getHashLon());
        return result;
    }

    @Override
    public int getGraticuleLat() {
        throw new UnsupportedOperationException("Globalhash has no specific graticule");
    }

    @Override
    public int getGraticuleLon() {
        throw new UnsupportedOperationException("Globalhash has no specific graticule");
    }
}
