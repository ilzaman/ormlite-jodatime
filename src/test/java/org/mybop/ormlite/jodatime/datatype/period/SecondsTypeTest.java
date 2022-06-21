package org.mybop.ormlite.jodatime.datatype.period;

import org.joda.time.Seconds;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SecondsTypeTest {

    private final SecondsType secondsType = SecondsType.getSingleton();

    @Test
    public void parseString() {
        final Seconds seconds = secondsType.parseString(null, "876");
        assertThat(seconds).isEqualTo(Seconds.seconds(876));
    }

    @Test
    public void parseString_iso() {
        final Seconds seconds = secondsType.parseString(null, "PT876S");
        assertThat(seconds).isEqualTo(Seconds.seconds(876));
    }

    @Test
    public void objToSql() {
        final Integer sqlArg = secondsType.objToSql(null, Seconds.seconds(9));
        assertThat(sqlArg).isEqualTo(9);
    }

    @Test
    public void sqlToObj() {
        final Seconds obj = secondsType.sqlToObj(null, 6);
        assertThat(obj).isEqualTo(Seconds.seconds(6));
    }
}
