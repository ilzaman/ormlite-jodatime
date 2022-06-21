package org.mybop.ormlite.jodatime.datatype.duration;

import org.joda.time.Duration;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DurationStringTypeTest {

    private final DurationStringType durationLongType = DurationStringType.getSingleton();

    @Test
    public void parseString() {
        final Duration duration = durationLongType.parseString(null, "PT4.567S");
        assertThat(duration).isEqualTo(Duration.millis(4567L));
    }

    @Test
    public void objToSql() {
        final String sqlArg = durationLongType.objToSql(null, Duration.millis(1234L));
        assertThat(sqlArg).isEqualTo("PT1.234S");
    }

    @Test
    public void sqlToObj() {
        final Duration duration = durationLongType.sqlToObj(null, "PT7.89S");
        assertThat(duration).isEqualTo(Duration.millis(7890L));
    }

    @Test
    public void comparable() {
        assertThat(durationLongType.isComparable()).isTrue();
    }
}
