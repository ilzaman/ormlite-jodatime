package org.mybop.ormlite.jodatime.datatype.instant;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeStringTypeTest {

    private final DateTimeStringType dateTimeStringType = DateTimeStringType.getSingleton();

    @Test
    public void objToSql() {
        final String sqlArg = dateTimeStringType.objToSql(null, DateTime.parse("2010-06-30T01:20+02:00"));
        assertThat(sqlArg).isEqualTo("2010-06-30T01:20:00.000+02:00");
    }

    @Test
    public void sqlToObj() {
        final DateTime dateTime = dateTimeStringType.sqlToObj(null, "2010-06-30T01:20:00.000+02:00");
        assertThat(dateTime).isEqualTo(DateTime.parse("2010-06-30T01:20+02:00"));
    }

    @Test
    public void isComparable() {
        assertThat(dateTimeStringType.isComparable()).isTrue();
    }

    @Test
    public void getDefaultWidth() {
        final String stringFormat = DateTime.now().toString();
        assertThat(stringFormat.length()).isLessThanOrEqualTo(dateTimeStringType.getDefaultWidth());
    }

    @Test
    public void isValidForVersion() {
        assertThat(dateTimeStringType.isValidForVersion()).isTrue();
    }
}
