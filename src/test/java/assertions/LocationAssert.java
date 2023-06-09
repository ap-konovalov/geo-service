package assertions;

import ru.netology.entity.Location;
import org.assertj.core.api.AbstractAssert;

public class LocationAssert extends AbstractAssert<LocationAssert, Location> {
    protected LocationAssert(Location actual) {
        super(actual, LocationAssert.class);
    }

    public static LocationAssert assertThat(Location actual) {
        return new LocationAssert(actual);
    }

    public LocationAssert equals(Location expected) {
        isNotNull();
        if (!actual.getCity().equals(expected.getCity())) {
            failWithMessage("City not equals");
        }
        if (!actual.getCountry().equals(expected.getCountry())) {
            failWithMessage("Country not equals");
        }
        if (actual.getBuiling() != expected.getBuiling()) {
            failWithMessage("Building not equals");
        }
        if (actual.getStreet() == null && expected.getStreet() == null) {
            return this;
        } else if (!actual.getStreet().equals(expected.getStreet())) {
            failWithMessage("Street not equals");
        }
        return this;
    }
}
