package org.mybop.ormlite.jodatime.datatype.timezone;

import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeZoneTypeTest {

    private final DateTimeZoneType dateTimeZoneType = DateTimeZoneType.getSingleton();

    @Test
    public void sqlToObj() {
        final DateTimeZone timeZone = DateTimeZone.forID("Europe/Paris");
        final DateTimeZone obj = dateTimeZoneType.sqlToObj(null, timeZone.getID());
        assertThat(obj).isEqualTo(timeZone);
    }

    @Test
    public void objToSql() {
        final DateTimeZone timeZone = DateTimeZone.forID("Europe/Paris");
        final String sqlArg = dateTimeZoneType.objToSql(null, timeZone);
        assertThat(sqlArg).isEqualTo("Europe/Paris");
    }

    @Test
    public void getDefaultWidth() {
        final Set<String> ids = DateTimeZone.getAvailableIDs();
        for (String id : ids) {
            assertThat(dateTimeZoneType.getDefaultWidth()).isGreaterThanOrEqualTo(id.length());
        }
    }

    @Test
    public void isComparable() {
        assertThat(dateTimeZoneType.isComparable()).isFalse();
    }
}
