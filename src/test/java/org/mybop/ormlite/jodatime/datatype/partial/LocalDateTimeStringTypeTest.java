package org.mybop.ormlite.jodatime.datatype.partial;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTimeStringTypeTest {

    private final LocalDateTimeStringType localDateTimeStringType = LocalDateTimeStringType.getSingleton();

    @Test
    public void objToSql() {
        final LocalDateTime now = LocalDateTime.now();
        final String sqlArg = localDateTimeStringType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.toString());
    }

    @Test
    public void sqlToObj() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime obj = localDateTimeStringType.sqlToObj(null, now.toString());
        assertThat(obj).isEqualTo(now);
    }

    @Test
    public void getDefaultWidth() {
        final String string = LocalDateTime.now().toString();
        assertThat(localDateTimeStringType.getDefaultWidth()).isGreaterThanOrEqualTo(string.length());
    }

    @Test
    public void isComparable() {
        assertThat(localDateTimeStringType.isComparable()).isTrue();
    }

}
