package org.mybop.ormlite.jodatime.datatype.partial;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTimeLongTypeTest {

    private final LocalDateTimeLongType localDateTimeLongType = LocalDateTimeLongType.getSingleton();

    @Test
    public void parseString() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime parsed = localDateTimeLongType.parseString(null, now.toString());
        assertThat(parsed).isEqualTo(now);
    }

    @Test
    public void objToSql() {
        final LocalDateTime now = LocalDateTime.now();
        final Long sqlArg = localDateTimeLongType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.toDateTime(DateTimeZone.UTC).getMillis());
    }

    @Test
    public void sqlToObj() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime obj = localDateTimeLongType.sqlToObj(null, now.toDateTime(DateTimeZone.UTC).getMillis());
        assertThat(obj).isEqualTo(now);
    }
}
