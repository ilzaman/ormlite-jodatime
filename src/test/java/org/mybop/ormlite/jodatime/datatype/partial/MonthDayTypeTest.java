package org.mybop.ormlite.jodatime.datatype.partial;

import org.joda.time.MonthDay;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MonthDayTypeTest {

    private final MonthDayType monthDayType = MonthDayType.getSingleton();

    @Test
    public void getDefaultWidth() {
        assertThat(monthDayType.getDefaultWidth()).isGreaterThanOrEqualTo(MonthDay.now().toString().length());
    }

    @Test
    public void isComparable() {
        assertThat(monthDayType.isComparable()).isTrue();
    }

    @Test
    public void objToSql() {
        final MonthDay now = MonthDay.now();
        final String sqlArg = monthDayType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.toString());
    }

    @Test
    public void sqlToObj() {
        final MonthDay now = MonthDay.now();
        final MonthDay obj = monthDayType.sqlToObj(null, now.toString());
        assertThat(obj).isEqualTo(now);
    }
}
