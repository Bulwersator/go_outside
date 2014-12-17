package geohashing;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GlobalhashTest {
    @Test
    public void testFetchMarketData() throws Exception {
        GenericGeohashLogicTest.fetchMarketDataTest(new GlobalhashFactory());
    }

    @Test
    public void testGeneratedCoordinates() throws Exception {
        //data for this test was obtained from http://wiki.xkcd.com/geohashing/W30#Testing_for_30W_compliance
        Globalhash tested;
        tested = new Globalhash(new DateTime(2008, 5, 20, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 20, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", -46.71388, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -135.48197, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 21, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 21, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 85.74626, tested.getHashLat(), 0.000005);
        assertEquals("longitude", 146.18662, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 22, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 22, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 61.62927, tested.getHashLat(), 0.000005);
        assertEquals("longitude", 69.96869, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 23, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 23, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 78.42559, tested.getHashLat(), 0.000005);
        assertEquals("longitude", 129.50128, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 24, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 24, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", -67.20336, tested.getHashLat(), 0.000005);
        assertEquals("longitude", 17.11192, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 25, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 25, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 79.51947, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -114.16550, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 26, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 26, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 31.16306, tested.getHashLat(), 0.000005);
        assertEquals("longitude", 38.63088, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 27, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 27, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", -67.43391, tested.getHashLat(), 0.000005);
        assertEquals("longitude", 27.75993, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 28, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 28, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 37.87947, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -139.41640, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 29, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 29, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", -39.90121, tested.getHashLat(), 0.000005);
        assertEquals("longitude", 86.81114, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 30, 0, 0));
        assertEquals("date getter",new DateTime(2008, 5, 30, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", -31.91030, tested.getHashLat(), 0.000005);
        assertEquals("longitude", 73.65004, tested.getHashLon(), 0.000005);

        tested = new Globalhash(new DateTime(2012, 2, 26, 0, 0));
        assertEquals("date getter",new DateTime(2012, 2, 26, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", -89.99161, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -5.86128, tested.getHashLon(), 0.000005);
    }
}
