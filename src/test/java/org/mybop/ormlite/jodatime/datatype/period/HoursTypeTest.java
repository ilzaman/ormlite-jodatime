package org.mybop.ormlite.jodatime.datatype.period;

import org.joda.time.Hours;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HoursTypeTest {

    private final HoursType hoursType = HoursType.getSingleton();

    @Test
    public void parseString() {
        final Hours parsed = hoursType.parseString(null, "8");
        assertThat(parsed).isEqualTo(Hours.hours(8));
    }

    @Test
    public void parseString_iso() {
        final Hours parsed = hoursType.parseString(null, "PT8H");
        assertThat(parsed).isEqualTo(Hours.hours(8));
    }

    @Test
    public void objToSql() {
        final Integer sqlArg = hoursType.objToSql(null, Hours.hours(4));
        assertThat(sqlArg).isEqualTo(4);
    }

    @Test
    public void sqlToObj() {
        final Hours obj = hoursType.sqlToObj(null, 89);
        assertThat(obj).isEqualTo(Hours.hours(89));
    }
}
