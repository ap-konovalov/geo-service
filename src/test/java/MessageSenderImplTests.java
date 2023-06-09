import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderImplTests {

    private static final String RUSSIAN_IP = "172.123.12.19";
    private static final String AMERICAN_IP = "96.123.12.19";
    private static final String EXPECTED_RUSSIAN_RESPONSE = "Добро пожаловать";
    private static final String EXPECTED_USA_RESPONSE = "Welcome";
    private static Map<String, String> headers;
    private static MessageSender messageSender;

    @BeforeAll
    static void init() {
        headers = new HashMap<>();
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(EXPECTED_RUSSIAN_RESPONSE);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(EXPECTED_USA_RESPONSE);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void returnRussianMessageWhenRussianIp() {
        //arrange
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, RUSSIAN_IP);

        // act
        String response = messageSender.send(headers);

        // assert
        assertEquals(EXPECTED_RUSSIAN_RESPONSE, response);
    }

    @Test
    void returnEnglishMessageWhenEnglishIp() {
        //arrange
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, AMERICAN_IP);

        // act
        String response = messageSender.send(headers);

        // assert
        assertEquals(EXPECTED_USA_RESPONSE, response);
    }
}
