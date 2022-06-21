package org.mybop.ormlite.jodatime.datatype.partial;

import org.joda.time.YearMonth;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class YearMonthTypeTest {

    private final YearMonthType yearMonthType = YearMonthType.getSingleton();

    @Test
    public void getDefaultWidth() {
        assertThat(yearMonthType.getDefaultWidth()).isGreaterThanOrEqualTo(YearMonth.now().toString().length());
    }

    @Test
    public void isComparable() {
        assertThat(yearMonthType.isComparable()).isTrue();
    }

    @Test
    public void objToSql() {
        final YearMonth now = YearMonth.now();
        final String sqlArg = yearMonthType.objToSql(null, now);
        assertThat(sqlArg).isEqualTo(now.toString());
    }

    @Test
    public void sqlToObj() {
        final YearMonth now = YearMonth.now();
        final YearMonth obj = yearMonthType.sqlToObj(null, now.toString());
        assertThat(obj).isEqualTo(now);
    }
}
