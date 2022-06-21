package org.mybop.ormlite.jodatime.datatype.period;

import org.joda.time.Weeks;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeeksTypeTest {

    private final WeeksType weeksType = WeeksType.getSingleton();

    @Test
    public void parseString() {
        final Weeks parsed = weeksType.parseString(null, "51");
        assertThat(parsed).isEqualTo(Weeks.weeks(51));
    }

    @Test
    public void parseString_iso() {
        final Weeks parsed = weeksType.parseString(null, "P51W");
        assertThat(parsed).isEqualTo(Weeks.weeks(51));
    }

    @Test
    public void objToSql() {
        final Integer sqlArg = weeksType.objToSql(null, Weeks.weeks(70));
        assertThat(sqlArg).isEqualTo(70);
    }

    @Test
    public void sqlToObj() {
        final Weeks obj = weeksType.sqlToObj(null, 1);
        assertThat(obj).isEqualTo(Weeks.weeks(1));
    }
}
