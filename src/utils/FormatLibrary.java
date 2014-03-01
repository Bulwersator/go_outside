package utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;

public final class FormatLibrary {
    private FormatLibrary() {
    }

    public static DateTimeFormatter ISODate() {
        //ISO standard, this format is also needed by geohashing algorithm
        return DateTimeFormat.forPattern("yyyy-MM-dd");
    }

    public static DecimalFormat geographicCoordinate() {
        //overprecision is not needed, this presents location with accuracy (<6m) comparable to GPS data
        //see http://www.wolframalpha.com/input/?i=equator+length+%2F+%28360+*+10000+*+2%29
        return new DecimalFormat("0.000");
    }
}
