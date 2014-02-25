package geohashing;

import net.exclaimindustries.tools.HexFraction;
import net.exclaimindustries.tools.MD5Tools;
import utils.Downloader;
import utils.FormatLibrary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenericGeohashLogic {
    private boolean valid = false;
    private int lat;
    private int lon;
    private double hashLat;
    private double hashLon;
    private Calendar geohashDate;

    protected String fetchMarketData(Calendar date) throws IOException {
        SimpleDateFormat slashDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String url = "http://geo.crox.net/djia/"+slashDateFormat.format(date.getTime());
        return Downloader.fetchURL(url);
    }
    /**
     * Fetches data about hashpoint in specified graticule, on specified date.
     * @param graticuleLat Latitude of graticule
     * @param graticuleLon Longitude of graticule
     * @param geohashDateParam Date of geohash.
     */
    public GenericGeohashLogic(int graticuleLat, int graticuleLon, Calendar geohashDateParam) {
        lat = graticuleLat;
        lon = graticuleLon;
        geohashDate = geohashDateParam;
        String djia;
        try {
            djia = fetchMarketData(geohashDate);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String formatted_date = FormatLibrary.ISODate().format(geohashDate.getTime());
        String hash = MD5Tools.MD5hash(formatted_date + "-" + djia);
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

    public Calendar getGeohashDate() {
        return geohashDate;
    }
}
