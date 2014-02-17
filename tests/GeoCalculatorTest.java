import static org.junit.Assert.*;

public class GeoCalculatorTest {
    @org.junit.Test
    public void testGetDistanceBetweenCoordinates() throws Exception {
        double almostZero = 1/1000; //1 m error is perfectly acceptable

        String distanceToItself = "point has 0 distance to itself";
        for(int i = 0; i<1000; i++){
            double lat = Math.random() * 180 - 90;
            double lon = Math.random() * 180 - 90;
            assertEquals(distanceToItself, 0, GeoCalculator.GetDistanceBetweenCoordinates(lat, lon, lat, lon), almostZero);
        }

        double distance;
        //expected values were acquired from http://www.movable-type.co.uk/scripts/latlong.html
        distance = GeoCalculator.GetDistanceBetweenCoordinates(50.03, 5.42, 58.38, 3.04);
        assertEquals("distance check", distance, 941.1, 0.05);
        distance = GeoCalculator.GetDistanceBetweenCoordinates(0, 0, 90, 0);
        assertEquals("distance check", distance, 10010, 5);
        distance = GeoCalculator.GetDistanceBetweenCoordinates(0, 0, 0, 180);
        assertEquals("distance check", distance, 20020, 5);
        distance = GeoCalculator.GetDistanceBetweenCoordinates(-50.03, 5.42, 58.38, 3.04);
        assertEquals("distance check", distance, 12060, 5);
        distance = GeoCalculator.GetDistanceBetweenCoordinates(50.0764, 19.9658, 19.9658, 3);
        assertEquals("distance check", distance, 3668, 0.5);
        distance = GeoCalculator.GetDistanceBetweenCoordinates(50.0764, 19.9658, 51.0764, 20.9658);
        assertEquals("distance check", distance, 131.7, 0.05);
    }
}
