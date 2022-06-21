package org.mybop.ormlite.jodatime.datatype.instant;

import org.joda.time.MutableDateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MutableDateTimeLongTypeTest {

    private final MutableDateTimeLongType mutableDateTimeLongType = MutableDateTimeLongType.getSingleton();

    @Test
    public void parseString() {
        final MutableDateTime now = MutableDateTime.now();
        final MutableDateTime parsed = mutableDateTimeLongType.parseString(null, now.toString());
        assertThat(parsed).isEqualByComparingTo(now);
    }

    @Test
    public void objToSql() {
        final MutableDateTime now = MutableDateTime.now();
        final Long sqlArg = mutableDateTimeLongType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.getMillis());
    }

    @Test
    public void sqlToObj() {
        final MutableDateTime now = MutableDateTime.now();
        final MutableDateTime obj = mutableDateTimeLongType.sqlToObj(null, now.getMillis());
        assertThat(obj).isEqualTo(now);
    }

    @Test
    public void isValidForVersion() {
        assertThat(mutableDateTimeLongType.isValidForVersion()).isTrue();
    }
}
