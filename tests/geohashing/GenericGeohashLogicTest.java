package geohashing;

import org.joda.time.DateTime;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GenericGeohashLogicTest {
    public static void fetchMarketDataTest(GeohashFactory factory) throws IOException {
        //data for this test was obtained from http://wiki.xkcd.com/geohashing/W30#Testing_for_30W_compliance
        GenericGeohashLogic object = factory.makeGeohash(0, 0, DateTime.now());
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 20, 0, 0)), "13026.04");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 21, 0, 0)), "12824.94");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 22, 0, 0)), "12597.69");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 23, 0, 0)), "12620.90");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 24, 0, 0)), "12620.90");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 25, 0, 0)), "12620.90");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 26, 0, 0)), "12620.90");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 27, 0, 0)), "12479.63");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 28, 0, 0)), "12542.90");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 29, 0, 0)), "12593.87");
        assertEquals(object.fetchMarketData(new DateTime(2008, 5, 30, 0, 0)), "12647.36");
        assertEquals(object.fetchMarketData(new DateTime(2012, 2, 26, 0, 0)), "12981.20");
    }
}
