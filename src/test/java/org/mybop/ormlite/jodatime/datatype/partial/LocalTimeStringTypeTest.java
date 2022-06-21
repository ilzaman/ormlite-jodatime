package org.mybop.ormlite.jodatime.datatype.partial;

import org.joda.time.LocalTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalTimeStringTypeTest {

    private final LocalTimeStringType localTimeStringType = LocalTimeStringType.getSingleton();

    @Test
    public void objToSql() {
        final LocalTime now = LocalTime.now();
        final String sqlArg = localTimeStringType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.toString());
    }

    @Test
    public void sqlToObj() {
        final LocalTime now = LocalTime.now();
        final LocalTime obj = localTimeStringType.sqlToObj(null, now.toString());
        assertThat(obj).isEqualTo(now);
    }

    @Test
    public void getDefaultWidth() {
        final String string = LocalTime.now().toString();
        assertThat(localTimeStringType.getDefaultWidth()).isGreaterThanOrEqualTo(string.length());
    }

    @Test
    public void isComparable() {
        assertThat(localTimeStringType.isComparable()).isTrue();
    }

}
