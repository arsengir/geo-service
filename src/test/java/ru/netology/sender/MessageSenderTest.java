package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;

class MessageSenderTest {

    public static final String RUSSIA_IP = "172.123.12.19";
    public static final String USA_IP = "96.44.183.149";

    @Test
    void test_language_send_in_russia_ip(){

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(RUSSIA_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String actual = messageSender.send(new HashMap<String, String>(){{
            put(MessageSenderImpl.IP_ADDRESS_HEADER, RUSSIA_IP);
        }});

        Assertions.assertTrue(actual.chars()
                .filter(Character::isAlphabetic)
                .mapToObj(Character.UnicodeBlock::of)
                .allMatch(b -> b.equals(Character.UnicodeBlock.CYRILLIC)));
    }

    @Test
    void test_language_send_in_usa_ip(){

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(USA_IP))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String actual = messageSender.send(new HashMap<String, String>(){{
            put(MessageSenderImpl.IP_ADDRESS_HEADER, USA_IP);
        }});

        Assertions.assertTrue(actual.chars()
                .filter(Character::isAlphabetic)
                .mapToObj(Character.UnicodeBlock::of)
                .allMatch(b -> b.equals(Character.UnicodeBlock.BASIC_LATIN)));
    }
}