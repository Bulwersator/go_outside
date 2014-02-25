package geohashing;

import net.exclaimindustries.tools.HexFraction;
import net.exclaimindustries.tools.MD5Tools;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import utils.Downloader;
import utils.FormatLibrary;

import java.io.IOException;

public class GenericGeohashLogic {
    private boolean valid = false;
    private int lat;
    private int lon;
    private double hashLat;
    private double hashLon;
    private DateTime geohashDate;

    /**
     * Returns djia opening value for given day obtained from website serving this data for geohashing purposes
     * @param date date of djia opening (valid for past dates within reasonable range)
     * @return djia opening data
     * @throws IOException for days with unavailable data and in case of connection problems
     */
    protected String fetchMarketData(DateTime date) throws IOException {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");
        String url = "http://geo.crox.net/djia/"+date.toString(fmt);
        return Downloader.fetchURL(url);
    }

    /**
     * Fetches data about hashpoint in specified graticule, on specified date.
     * @param graticuleLat Latitude of graticule
     * @param graticuleLon Longitude of graticule
     * @param geohashDateParam Date of geohash.
     */
    public GenericGeohashLogic(int graticuleLat, int graticuleLon, DateTime geohashDateParam) {
        lat = graticuleLat;
        lon = graticuleLon;
        geohashDate = new DateTime(geohashDateParam);
        String djia;
        DateTime introductionDateOfTimeZoneRule = new DateTime(2008, 5, 27, 0, 0);
        try {
            if(lon > -30 && introductionDateOfTimeZoneRule.isBefore(geohashDate)){ //30W Time Zone Rule
                DateTime dijaDate = new DateTime(geohashDate);
                dijaDate = dijaDate.minusDays(1);
                djia = fetchMarketData(dijaDate);
            } else {
                djia = fetchMarketData(geohashDate);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String formattedDate = geohashDate.toString(FormatLibrary.ISODate());
        String hashed = formattedDate + "-" + djia;
        String hash = MD5Tools.MD5hash(hashed);
        hashLat = HexFraction.calculate(hash.substring(0, 16));
        hashLon = HexFraction.calculate(hash.substring(16, 32));
        if(lat<0){
            hashLat = lat - hashLat;
        } else {
            hashLat = lat + hashLat;
        }
        if(lon<0){
            hashLon = lon - hashLon;
        } else {
            hashLon = lon + hashLon;
        }
        valid = true;
    }

    /**
     * Allows checking validity of object.
     * Invalid object may become valid, valid never will become invalid.
     * @return true for object that may be querried for location of hashpoint, false otherwise
     */
    boolean isValid() {
        return valid;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    public double getHashLat() {
        return hashLat;
    }

    public double getHashLon() {
        return hashLon;
    }

    public DateTime getGeohashDate() {
        return geohashDate;
    }
}
