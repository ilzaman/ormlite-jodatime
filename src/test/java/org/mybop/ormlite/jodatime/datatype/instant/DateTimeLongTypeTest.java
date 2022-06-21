package org.mybop.ormlite.jodatime.datatype.instant;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeLongTypeTest {

    private final DateTimeLongType dateTimeLongType = DateTimeLongType.getSingleton();

    @Test
    public void parseString() {
        final DateTime dateTime = dateTimeLongType.parseString(null, "2010-06-30T01:20+02:00");
        assertThat(dateTime).isEqualTo(DateTime.parse("2010-06-30T01:20+02:00"));
    }

    @Test
    public void objToSql() {
        final Long sqlArg = dateTimeLongType.objToSql(null, DateTime.parse("2010-06-30T01:20+02:00"));
        assertThat(sqlArg).isEqualTo(DateTime.parse("2010-06-30T01:20+02:00").getMillis());
    }

    @Test
    public void sqlToObj() {
        final DateTime dateTime = dateTimeLongType.parseString(null, "2010-06-30T01:20+02:00");
        assertThat(dateTime).isEqualTo(DateTime.parse("2010-06-30T01:20+02:00"));
    }

    @Test
    public void isValidForVersion() {
        assertThat(dateTimeLongType.isValidForVersion()).isTrue();
    }
}
