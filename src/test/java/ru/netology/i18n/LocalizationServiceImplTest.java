package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource
    void locale(Country country) {
        String expected;
        if (country == Country.RUSSIA) {
            expected = "Добро пожаловать";
        } else {
            expected = "Welcome";
        }

        String actual = new LocalizationServiceImpl().locale(country);
        Assertions.assertEquals(expected, actual);
    }
}