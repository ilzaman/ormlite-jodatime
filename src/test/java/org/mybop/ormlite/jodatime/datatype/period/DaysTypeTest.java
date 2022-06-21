package org.mybop.ormlite.jodatime.datatype.period;

import org.joda.time.Days;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DaysTypeTest {

    private final DaysType daysType = DaysType.getSingleton();

    @Test
    public void parseString() {
        final Days days = daysType.parseString(null, "5");
        assertThat(days).isEqualTo(Days.days(5));
    }

    @Test
    public void parseString_iso() {
        final Days days = daysType.parseString(null, "P5D");
        assertThat(days).isEqualTo(Days.days(5));
    }

    @Test
    public void objToSql() {
        final Days days = Days.days(3);
        final Integer sqlArg = daysType.objToSql(null, days);
        assertThat(sqlArg).isEqualTo(3);
    }

    @Test
    public void sqlToObj() {
        final Days obj = daysType.sqlToObj(null, 7);
        assertThat(obj).isEqualTo(Days.days(7));
    }
}
