package geohashing;

import org.joda.time.DateTime;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GenericGeohashLogicTest {
    private GenericGeohashLogicTest() {
    }

    public static void fetchMarketDataTest(GeohashFactory factory) throws IOException {
        //data for this test was obtained from http://wiki.xkcd.com/geohashing/W30#Testing_for_30W_compliance
        assertEquals("market data fetching", "13026.04", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 20, 0, 0)));
        assertEquals("market data fetching", "12824.94", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 21, 0, 0)));
        assertEquals("market data fetching", "12597.69", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 22, 0, 0)));
        assertEquals("market data fetching", "12620.90", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 23, 0, 0)));
        assertEquals("market data fetching", "12620.90", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 24, 0, 0)));
        assertEquals("market data fetching", "12620.90", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 25, 0, 0)));
        assertEquals("market data fetching", "12620.90", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 26, 0, 0)));
        assertEquals("market data fetching", "12479.63", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 27, 0, 0)));
        assertEquals("market data fetching", "12542.90", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 28, 0, 0)));
        assertEquals("market data fetching", "12593.87", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 29, 0, 0)));
        assertEquals("market data fetching", "12647.36", GenericGeohashLogic.fetchMarketData(new DateTime(2008, 5, 30, 0, 0)));
        assertEquals("market data fetching", "12981.20", GenericGeohashLogic.fetchMarketData(new DateTime(2012, 2, 26, 0, 0)));
    }
}
