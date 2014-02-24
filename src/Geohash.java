import net.exclaimindustries.tools.HexFraction;
import net.exclaimindustries.tools.MD5Tools;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Geohash {
    private boolean failed;
    private boolean generated = false;
    private int lat;
    private int lon;
    private double hashLat;
    private double hashLon;
    private Calendar geohash_date;

    /**
     * Fetches data about hashpoint in specified graticule, on specified date.
     * @param graticule_lat Latitude of graticule
     * @param graticule_lon Longitude of graticule
     * @param geohash_date_param Date of geohash.
     */
    public Geohash(int graticule_lat, int graticule_lon, Calendar geohash_date_param) {
        lat = graticule_lat;
        lon = graticule_lon;
        failed = false;
        geohash_date = geohash_date_param;
        SimpleDateFormat slashDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String url = "http://geo.crox.net/djia/"+slashDateFormat.format(geohash_date.getTime());
        String djia = null;
        try {
            djia = Downloader.fetchURL(url);
        } catch (IOException e) {
            failed = true;
        }

        SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatted_date = isoDateFormat.format(geohash_date.getTime());
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
        generated = true;
    }

    /**
     * @return Short description of hashpoint.
     */
    String getResult() {
        String result;
        if(!generated){
            return "fetching data";
        }
        if(failed){
            result = "something failed";
        } else {
            SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formatted_date = isoDateFormat.format(geohash_date.getTime());
            DecimalFormat decim = new DecimalFormat("0.0000");
            result = "graticule " + lat + ", " + lon + " on " + formatted_date + ":\n";
            result += decim.format(hashLat) + " " + decim.format(hashLon);
        }
        return result;
    }
}
