package org.mybop.ormlite.jodatime.datatype.instant;

import org.joda.time.MutableDateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MutableDateTimeStringTypeTest {

    private final MutableDateTimeStringType mutableDateTimeStringType = MutableDateTimeStringType.getSingleton();

    @Test
    public void objToSql() {
        final MutableDateTime now = MutableDateTime.now();
        final String sqlArg = mutableDateTimeStringType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.toString());
    }

    @Test
    public void sqlToObj() {
        final MutableDateTime now = MutableDateTime.now();
        final MutableDateTime obj = mutableDateTimeStringType.sqlToObj(null, now.toString());
        assertThat(obj).isEqualByComparingTo(now);
    }

    @Test
    public void isComparable() {
        assertThat(mutableDateTimeStringType.isComparable()).isTrue();
    }

    @Test
    public void getDefaultWidth() {
        final MutableDateTime now = MutableDateTime.now();
        final String string = now.toString();
        assertThat(string.length()).isLessThanOrEqualTo(mutableDateTimeStringType.getDefaultWidth());
    }

    @Test
    public void isValidForVersion() {
        assertThat(mutableDateTimeStringType.isValidForVersion()).isTrue();
    }
}
