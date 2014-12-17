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
        assertEquals("date getter", new DateTime(2008, 5, 20, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.63099, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.61895, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 21, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 21, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.17947, tested.getHashLat(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 22, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 22, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.97287, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.23870, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 23, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 23, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.40025, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.72277, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 24, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 24, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.12665, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.54753, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 25, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 25, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.94177, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.18287, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 26, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 26, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.67313, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.60731, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 27, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 27, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.20968, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.10144, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 28, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 28, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.68745, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.21221, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 29, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 29, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.46470, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.03412, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2008, 5, 30, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 30, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.85310, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.24460, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -30, new DateTime(2012, 2, 26, 0, 0));
        assertEquals("date getter", new DateTime(2012, 2, 26, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.000047, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -30.483719, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 20, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 20, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.63099, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.61895, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 21, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 21, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.17947, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.86154, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 22, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 22, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.97287, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.23870, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 23, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 23, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.40025, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.72277, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 24, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 24, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.12665, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.54753, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 25, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 25, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.94177, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.18287, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 26, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 26, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.67313, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.60731, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 27, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 27, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.12537, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.57711, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 28, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 28, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.71044, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.11273, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 29, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 29, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.27833, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.74114, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2008, 5, 30, 0, 0));
        assertEquals("date getter", new DateTime(2008, 5, 30, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.32272, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.70458, tested.getHashLon(), 0.000005);

        tested = new StandardGeohash(68, -29, new DateTime(2012, 2, 26, 0, 0));
        assertEquals("date getter", new DateTime(2012, 2, 26, 0, 0), tested.getGeohashDate());
        assertEquals("latitude", 68.000047, tested.getHashLat(), 0.000005);
        assertEquals("longitude", -29.483719, tested.getHashLon(), 0.000005);
    }
}
