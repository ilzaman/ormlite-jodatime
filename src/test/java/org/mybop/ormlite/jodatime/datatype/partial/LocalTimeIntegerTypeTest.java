package org.mybop.ormlite.jodatime.datatype.partial;

import org.joda.time.LocalTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalTimeIntegerTypeTest {

    private final LocalTimeIntegerType localTimeIntegerType = LocalTimeIntegerType.getSingleton();

    @Test
    public void parseString() {
        final LocalTime localTime = LocalTime.now();
        final LocalTime parsed = localTimeIntegerType.parseString(null, localTime.toString());
        assertThat(parsed).isEqualTo(localTime);
    }

    @Test
    public void objToSql() {
        final LocalTime now = LocalTime.now();
        final Integer sqlArg = localTimeIntegerType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.getMillisOfDay());
    }

    @Test
    public void sqlToObj() {
        final LocalTime now = LocalTime.now();
        final LocalTime obj = localTimeIntegerType.sqlToObj(null, now.getMillisOfDay());
        assertThat(obj).isEqualTo(now);
    }
}
