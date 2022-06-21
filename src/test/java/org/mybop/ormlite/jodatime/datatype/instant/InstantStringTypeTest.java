package org.mybop.ormlite.jodatime.datatype.instant;

import org.joda.time.Instant;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstantStringTypeTest {

    private final InstantStringType instantStringType = InstantStringType.getSingleton();

    @Test
    public void objToSql() {
        final Instant now = Instant.now();
        final String sqlArg = instantStringType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.toString());
    }

    @Test
    public void sqlToObj() {
        final Instant now = Instant.now();
        final Instant obj = instantStringType.sqlToObj(null, now.toString());
        assertThat(obj).isEqualTo(now);
    }

    @Test
    public void isComparable() {
        assertThat(instantStringType.isComparable()).isTrue();
    }

    @Test
    public void isValidForVersion() {
        assertThat(instantStringType.isValidForVersion()).isTrue();
    }
}
