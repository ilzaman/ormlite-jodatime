package org.mybop.ormlite.jodatime.datatype.partial;

import com.j256.ormlite.field.FieldType;
import org.joda.time.MonthDay;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

public final class MonthDayType extends StringFormatDataType<MonthDay> {

    private static final MonthDayType INSTANCE = new MonthDayType();

    public static MonthDayType getSingleton() {
        return INSTANCE;
    }

    private MonthDayType() {
        super(MonthDay.class);
    }

    @Override
    public int getDefaultWidth() {
        return 7;
    }

    @Override
    public boolean isComparable() {
        return true;
    }

    @Override
    public String objToSql(final FieldType fieldType, final MonthDay monthDay) {
        return monthDay.toString();
    }

    @Override
    public MonthDay sqlToObj(final FieldType fieldType, final String sqlArg) {
        return MonthDay.parse(sqlArg);
    }
}
