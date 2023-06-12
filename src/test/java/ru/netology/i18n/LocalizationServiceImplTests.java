package ru.netology.i18n;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTests {

    private static LocalizationService localizationService;

    @BeforeAll
    static void init() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    void returnRussianMessageForRussia() {
//        //arrange
        String expectedMessage = "Добро пожаловать";

//        // act
        String actualMessage = localizationService.locale(Country.RUSSIA);

//        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void returnEnglishMessageForUSA() {
//        //arrange
        String expectedMessage = "Welcome";

//        // act
        String actualMessage = localizationService.locale(Country.USA);

//        // assert
        assertEquals(expectedMessage, actualMessage);
    }
}
