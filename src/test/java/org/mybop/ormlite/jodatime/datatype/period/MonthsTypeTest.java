package org.mybop.ormlite.jodatime.datatype.period;

import org.joda.time.Months;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MonthsTypeTest {

    private final MonthsType monthsType = MonthsType.getSingleton();

    @Test
    public void parseString() {
        final Months months = monthsType.parseString(null, "12");
        assertThat(months).isEqualTo(Months.months(12));
    }

    @Test
    public void parseString_iso() {
        final Months months = monthsType.parseString(null, "P12M");
        assertThat(months).isEqualTo(Months.months(12));
    }

    @Test
    public void objToSql() {
        final Integer sqlArg = monthsType.objToSql(null, Months.months(7));
        assertThat(sqlArg).isEqualTo(7);
    }

    @Test
    public void sqlToObj() {
        final Months obj = monthsType.sqlToObj(null, 75);
        assertThat(obj).isEqualTo(Months.months(75));
    }
}
