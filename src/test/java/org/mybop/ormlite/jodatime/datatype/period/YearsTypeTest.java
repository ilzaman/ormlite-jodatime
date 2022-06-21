package org.mybop.ormlite.jodatime.datatype.period;

import org.joda.time.Years;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class YearsTypeTest {

    private final YearsType yearsType = YearsType.getSingleton();

    @Test
    public void parseString() {
        final Years years = yearsType.parseString(null, "32");
        assertThat(years).isEqualTo(Years.years(32));
    }

    @Test
    public void parseString_iso() {
        final Years years = yearsType.parseString(null, "P32Y");
        assertThat(years).isEqualTo(Years.years(32));
    }

    @Test
    public void objToSql() {
        final Integer sqlArg = yearsType.objToSql(null, Years.years(26));
        assertThat(sqlArg).isEqualTo(26);
    }

    @Test
    public void sqlToObj() {
        final Years years = yearsType.sqlToObj(null, 78);
        assertThat(years).isEqualTo(Years.years(78));
    }
}
