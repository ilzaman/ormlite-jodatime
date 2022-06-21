package org.mybop.ormlite.jodatime.datatype.period;

import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MutablePeriodTypeTest {

    private final MutablePeriodType mutablePeriodType = MutablePeriodType.getSingleton();

    private static final String PERIOD_STRING = "P3Y9M442W28DT13H47M32S";

    private static final MutablePeriod PERIOD = Period.years(3).plusMonths(9).plusWeeks(442).plusDays(28).plusHours(13).plusMinutes(47).plusSeconds(32).toMutablePeriod();


    @Test
    public void isComparable() {
        assertThat(mutablePeriodType.isComparable()).isFalse();
    }

    @Test
    public void getDefaultWidth() {
        assertThat(mutablePeriodType.getDefaultWidth()).isGreaterThanOrEqualTo(PERIOD_STRING.length());
    }

    @Test
    public void sqlToObj() {
        final MutablePeriod obj = mutablePeriodType.sqlToObj(null, PERIOD_STRING);
        assertThat(obj).isEqualTo(PERIOD);
    }

    @Test
    public void objToSql() {
        final String sql = mutablePeriodType.objToSql(null, PERIOD);
        assertThat(sql).isEqualTo(PERIOD_STRING);
    }
}
