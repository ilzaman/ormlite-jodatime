package org.mybop.ormlite.jodatime.datatype.partial;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTypeTest {

    private final LocalDateType localDateType = LocalDateType.getSingleton();

    @Test
    public void getDefaultWidth() {
        final LocalDate now = LocalDate.now();
        assertThat(localDateType.getDefaultWidth()).isGreaterThanOrEqualTo(now.toString().length());
    }

    @Test
    public void isComparable() {
        assertThat(localDateType.isComparable()).isTrue();
    }

    @Test
    public void sqlToObj() {
        final LocalDate now = LocalDate.now();
        final LocalDate obj = localDateType.sqlToObj(null, now.toString());
        assertThat(obj).isEqualTo(now);
    }

    @Test
    public void objToSql() {
        final LocalDate now = LocalDate.now();
        final String sqlArg = localDateType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.toString());
    }
}
