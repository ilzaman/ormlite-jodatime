package org.mybop.ormlite.jodatime.datatype.instant;

import org.joda.time.Instant;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstantLongTypeTest {

    private final InstantLongType instantLongType = InstantLongType.getSingleton();

    @Test
    public void parseString() {
        final Instant now = Instant.now();
        final Instant parsed = instantLongType.parseString(null, now.toString());
        assertThat(parsed).isEqualTo(now);
    }

    @Test
    public void objToSql() {
        final Instant now = Instant.now();
        final Long sqlArg = instantLongType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.getMillis());
    }

    @Test
    public void sqlToObj() {
        final Instant now = Instant.now();
        final Instant obj = instantLongType.sqlToObj(null, now.getMillis());
        assertThat(obj).isEqualTo(now);
    }

    @Test
    public void isValidForVersion() {
        assertThat(instantLongType.isValidForVersion()).isTrue();
    }
}
