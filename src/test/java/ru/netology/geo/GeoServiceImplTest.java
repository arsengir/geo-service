package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class GeoServiceImplTest {

    public static final String RUSSIA_IP = GeoServiceImpl.MOSCOW_IP;
    public static final String USA_IP = GeoServiceImpl.NEW_YORK_IP;
    public static final String LOCALHOST = GeoServiceImpl.LOCALHOST;

    @Test
    void byIp_russia() {
        Country expected = Country.RUSSIA;
        Country actual = new GeoServiceImpl().byIp(RUSSIA_IP).getCountry();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void byIp_usa() {
        Country expected = Country.USA;
        Country actual = new GeoServiceImpl().byIp(USA_IP).getCountry();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void byIp_Localhost() {
        Assertions.assertNull(new GeoServiceImpl().byIp(LOCALHOST).getCountry());
    }
}