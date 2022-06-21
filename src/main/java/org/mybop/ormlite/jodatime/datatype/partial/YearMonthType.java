package org.mybop.ormlite.jodatime.datatype.partial;

import com.j256.ormlite.field.FieldType;
import org.joda.time.YearMonth;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;


public final class YearMonthType extends StringFormatDataType<YearMonth> {

    private static final YearMonthType INSTANCE = new YearMonthType();

    public static YearMonthType getSingleton() {
        return INSTANCE;
    }

    private YearMonthType() {
        super(YearMonth.class);
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
    public String objToSql(final FieldType fieldType, final YearMonth yearMonth) {
        return yearMonth.toString();
    }

    @Override
    public YearMonth sqlToObj(final FieldType fieldType, final String sqlArg) {
        return YearMonth.parse(sqlArg);
    }
}
