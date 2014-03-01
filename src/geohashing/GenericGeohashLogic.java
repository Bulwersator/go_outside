package geohashing;

import net.exclaimindustries.tools.HexFraction;
import net.exclaimindustries.tools.MD5Tools;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import utils.Coordinate;
import utils.Downloader;
import utils.FormatLibrary;

import java.io.IOException;

public abstract class GenericGeohashLogic {
    protected boolean valid = false;
    protected int lat;
    protected int lon;
    protected Coordinate result;
    protected DateTime geohashDate;

    /**
     * Returns djia opening value for given day obtained from website serving this data for geohashing purposes
     * @param date date of djia opening (valid for past dates within reasonable range)
     * @return djia opening data
     * @throws IOException for days with unavailable data and in case of connection problems
     */
    protected static String fetchMarketData(DateTime date) throws IOException {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");
        String url = "http://geo.crox.net/djia/"+date.toString(fmt);
        return Downloader.fetchURL(url);
    }

    protected static boolean isDateAfterTimeZoneRuleChange(DateTime checkedDate){
        DateTime introductionDateOfTimeZoneRule = new DateTime(2008, 5, 27, 0, 0);
        return introductionDateOfTimeZoneRule.isEqual(checkedDate) || introductionDateOfTimeZoneRule.isBefore(checkedDate);
    }
    protected abstract boolean useMarketDataFromPreviousDay();

    protected final Coordinate obtainRawHashesForLatLon() throws IOException {
        String djia;
        if(this.useMarketDataFromPreviousDay()){
            DateTime dijaDate = new DateTime(this.geohashDate);
            dijaDate = dijaDate.minusDays(1);
            djia = fetchMarketData(dijaDate);
        } else {
            djia = fetchMarketData(this.geohashDate);
        }
        String formattedDate = this.geohashDate.toString(FormatLibrary.ISODate());
        String hashed = formattedDate + "-" + djia;
        String hash = MD5Tools.MD5hash(hashed);
        double hashLat = HexFraction.calculate(hash.substring(0, 16));
        double hashLon = HexFraction.calculate(hash.substring(16, 32));
        return new Coordinate(hashLat, hashLon);
    }
    /**
     * Fetches data about hashpoint in specified graticule, on specified date.
     * @param graticuleLat Latitude of graticule
     * @param graticuleLon Longitude of graticule
     * @param geohashDateParam Date of geohash.
     */
    public GenericGeohashLogic(int graticuleLat, int graticuleLon, DateTime geohashDateParam) {
        this.lat = graticuleLat;
        this.lon = graticuleLon;
        this.geohashDate = new DateTime(geohashDateParam);
        Coordinate rawHashCoordinates;
        try {
            rawHashCoordinates = this.obtainRawHashesForLatLon();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        this.result = new Coordinate();
        this.processExistingData(rawHashCoordinates);
        this.valid = true;
    }

    protected abstract void processExistingData(Coordinate rawHashCoordinates);

    public abstract String generateDescription();

    /**
     * Allows checking validity of object.
     * Invalid object may become valid, valid never will become invalid.
     * @return true for object that may be querried for location of hashpoint, false otherwise
     */
    public final boolean isValid() {
        return this.valid;
    }

    public int getGraticuleLat() {
        return this.lat;
    }

    public int getGraticuleLon() {
        return this.lon;
    }

    public final double getHashLat() {
        return this.result.lat;
    }

    public final double getHashLon() {
        return this.result.lon;
    }

    public final Coordinate getHashCoordinate() {
        return this.result;
    }

    public final DateTime getGeohashDate() {
        return this.geohashDate;
    }
}
