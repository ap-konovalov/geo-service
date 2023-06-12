package ru.netology.geo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static assertions.LocationAssert.assertThat;

public class GeoServiceImplTests {

    private static GeoService geoService;
    private static final String RUSSIAN_IP = "172.123.12.18";
    private static final String AMERICAN_IP = "96.123.12.19";

    @BeforeAll
    static void init() {
        geoService = new GeoServiceImpl();
    }

    @Test
    void returnUSALocationFromUSAIp() {
//        arrange
        Location expectedLocation = new Location("New York", Country.USA, null, 0);

        // act
        Location actualLocation = geoService.byIp(AMERICAN_IP);

        // assert
        assertThat(actualLocation).equals(expectedLocation);
    }

    @Test
    void returnRussianLocationFromRussianIp() {
//        arrange
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, null, 0);

        // act
        Location actualLocation = geoService.byIp(RUSSIAN_IP);

        // assert
        assertThat(actualLocation).equals(expectedLocation);
    }
}
