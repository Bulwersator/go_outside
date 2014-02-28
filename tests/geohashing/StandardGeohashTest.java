package geohashing;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandardGeohashTest {
    @Test
    public void testFetchMarketData() throws Exception {
        GenericGeohashLogicTest.fetchMarketDataTest(new StandardGeohashFactory());
    }

    @Test
    public void testGeneratedCoordinates() throws Exception {
        //data for this test was obtained from http://wiki.xkcd.com/geohashing/W30#Testing_for_30W_compliance
        StandardGeohash tested;

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 20, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 20, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.63099, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.61895, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 21, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 21, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.17947, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.86154, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 22, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 22, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.97287, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.23870, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 23, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 23, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.40025, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.72277, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 24, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 24, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.12665, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.54753, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 25, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 25, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.94177, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.18287, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 26, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 26, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.67313, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.60731, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 27, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 27, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.20968, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.10144, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 28, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 28, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.68745, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.21221, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 29, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 29, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.46470, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.03412, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 30, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 30, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.85310, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.24460, 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2012, 2, 26, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2012, 2, 26, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.000047, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -30.483719, 0.000005);
        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 20, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 20, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.63099, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.61895, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 21, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 21, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.17947, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.86154, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 22, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 22, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.97287, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.23870, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 23, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 23, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.40025, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.72277, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 24, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 24, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.12665, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.54753, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 25, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 25, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.94177, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.18287, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 26, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 26, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.67313, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.60731, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 27, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 27, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.12537, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.57711, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 28, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 28, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.71044, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.11273, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 29, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 29, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.27833, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.74114, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 30, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2008, 5, 30, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.32272, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.70458, 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2012, 2, 26, 0, 0));
        assertEquals(tested.getGeohashDate(), new DateTime(2012, 2, 26, 0, 0));
        assertEquals("latitude", tested.getHashLat(), 68.000047, 0.000005);
        assertEquals("longitude", tested.getHashLon(), -29.483719, 0.000005);
    }
}
