package geohashing;

import net.exclaimindustries.tools.HexFraction;
import net.exclaimindustries.tools.MD5Tools;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import utils.Coordinate;
import utils.Downloader;
import utils.FormatLibrary;

import java.io.IOException;
import java.io.Serializable;

public abstract class GenericGeohashLogic implements Serializable {
    private boolean valid;
    int graticuleLat;
    int graticuleLon;
    Coordinate generatedLocation;
    DateTime geohashDate;

    /**
     * Returns djia opening value for given day obtained from website serving this data for geohashing purposes
     *
     * @param date date of djia opening (valid for past dates within reasonable range)
     * @return djia opening data
     * @throws IOException for days with unavailable data and in case of connection problems
     */
    protected static String fetchMarketData(DateTime date) throws IOException {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");
        String url = "http://geo.crox.net/djia/" + date.toString(fmt);
        return Downloader.fetchURL(url);
    }

    static boolean isDateAfterTimeZoneRuleChange(ReadableInstant checkedDate) {
        ReadableInstant timeZoneRuleIntroduction = new DateTime(2008, 5, 27, 0, 0);
        return timeZoneRuleIntroduction.isEqual(checkedDate) || timeZoneRuleIntroduction.isBefore(checkedDate);
    }

    protected abstract boolean shouldUseDowFromPreviousDay();

    private Coordinate obtainRawHashesForLatLon() throws IOException {
        String djia;
        if (this.shouldUseDowFromPreviousDay()) {
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
     *
     * @param graticuleLatParam Latitude of graticule
     * @param graticuleLonParam Longitude of graticule
     * @param geohashDateParam  Date of geohash.
     */
    GenericGeohashLogic(int graticuleLatParam, int graticuleLonParam, DateTime geohashDateParam) {
        this.graticuleLat = graticuleLatParam;
        this.graticuleLon = graticuleLonParam;
        this.generatedLocation = null;
        this.geohashDate = new DateTime(geohashDateParam);
        Coordinate rawHashCoordinates;
        try {
            rawHashCoordinates = this.obtainRawHashesForLatLon();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        this.processExistingData(rawHashCoordinates);
        this.valid = true;
    }

    protected abstract void processExistingData(Coordinate rawHashCoordinates);

    public abstract String generateDescription();

    /**
     * Allows checking validity of object.
     * Invalid object may become valid, valid never will become invalid.
     *
     * @return true for object that may be querried for location of hashpoint, false otherwise
     */
    public final boolean isValid() {
        return this.valid;
    }

    int getGraticuleLat() {
        return this.graticuleLat;
    }

    int getGraticuleLon() {
        return this.graticuleLon;
    }

    public final double getHashLat() {
        return this.generatedLocation.latitude;
    }

    public final double getHashLon() {
        return this.generatedLocation.longitude;
    }

    public final Coordinate getHashCoordinate() {
        return this.generatedLocation;
    }

    public final DateTime getGeohashDate() {
        return this.geohashDate;
    }
}
