import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FormatLibrary {
    static public SimpleDateFormat simpleDate(){
        //ISO standard, this format is also needed by geohashing algorithm
        return new SimpleDateFormat("yyyy-MM-dd");
    }
    static public DecimalFormat geographicCoordinate(){
        //overprecision is not needed, this presents location with accuracy (<6m) comparable to GPS data
        //see http://www.wolframalpha.com/input/?i=equator+length+%2F+%28360+*+10000+*+2%29
        return new DecimalFormat("0.000");
    }
}
