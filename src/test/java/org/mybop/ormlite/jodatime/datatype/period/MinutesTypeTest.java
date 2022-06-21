package org.mybop.ormlite.jodatime.datatype.period;

import org.joda.time.Minutes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MinutesTypeTest {

    private final MinutesType minutesType = MinutesType.getSingleton();

    @Test
    public void parseString() {
        final Minutes minutes = minutesType.parseString(null, "83");
        assertThat(minutes).isEqualTo(Minutes.minutes(83));
    }

    @Test
    public void parseString_iso() {
        final Minutes minutes = minutesType.parseString(null, "PT83M");
        assertThat(minutes).isEqualTo(Minutes.minutes(83));
    }

    @Test
    public void objToSql() {
        final Minutes minutes = Minutes.minutes(5);
        final Integer sqlArg = minutesType.objToSql(null, minutes);
        assertThat(sqlArg).isEqualTo(5);
    }

    @Test
    public void sqlToObj() {
        final Minutes minutes = Minutes.minutes(15);
        final Minutes obj = minutesType.sqlToObj(null, 15);
        assertThat(obj).isEqualTo(minutes);
    }
}
