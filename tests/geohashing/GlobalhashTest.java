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
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 20, 0, 0));
        assertEquals("latitude", tested.getHashLat(), -46.71388, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -135.48197, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 21, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 21, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 85.74626, 0.000005);
        assertEquals("longitude", tested.getHashLon(), 146.18662, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 22, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 22, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 61.62927, 0.000005);
        assertEquals("longitude", tested.getHashLon(), 69.96869, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 23, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 23, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 78.42559, 0.000005);
        assertEquals("longitude", tested.getHashLon(), 129.50128, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 24, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 24, 0, 0));
        assertEquals("latitude", tested.getHashLat(), -67.20336, 0.000005);
        assertEquals("longitude", tested.getHashLon(), 17.11192, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 25, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 25, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 79.51947, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -114.16550, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 26, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 26, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 31.16306, 0.000005);
        assertEquals("longitude", tested.getHashLon(), 38.63088, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 27, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 27, 0, 0));
        assertEquals("latitude", tested.getHashLat(), -67.43391, 0.000005);
        assertEquals("longitude", tested.getHashLon(), 27.75993, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 28, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 28, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 37.87947, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -139.41640, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 29, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 29, 0, 0));
        assertEquals("latitude", tested.getHashLat(), -39.90121, 0.000005);
        assertEquals("longitude", tested.getHashLon(), 86.81114, 0.000005);

        tested = new Globalhash(new DateTime(2008, 5, 30, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 30, 0, 0));
        assertEquals("latitude", tested.getHashLat(), -31.91030, 0.000005);
        assertEquals("longitude", tested.getHashLon(), 73.65004, 0.000005);

        tested = new Globalhash(new DateTime(2012, 2, 26, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2012, 2, 26, 0, 0));
        assertEquals("latitude", tested.getHashLat(), -89.99161, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -5.86128, 0.000005);
    }
}
