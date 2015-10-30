package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;
import org.joda.time.*;
import org.joda.time.base.BaseSingleFieldPeriod;

import java.sql.SQLException;

public final class SingleFieldPeriodType extends BaseDataType {

    public SingleFieldPeriodType() {
        super(SqlType.INTEGER, new Class[]{Days.class, Hours.class, Minutes.class, Months.class, Seconds.class, Weeks.class, Years.class});
    }

    @Override
    public boolean isEscapedValue() {
        return false;
    }

    @Override
    public Integer parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
        return Integer.valueOf(defaultStr);
    }

    @Override
    public Integer resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getInt(columnPos);
    }

    @Override
    public Integer javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        BaseSingleFieldPeriod singleFieldPeriod = (BaseSingleFieldPeriod) javaObject;
        return singleFieldPeriod.get(singleFieldPeriod.getFieldType());
    }

    @Override
    public BaseSingleFieldPeriod sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
        Class<?> type = fieldType.getType();
        int value = (Integer) sqlArg;
        if (Days.class.equals(type)) {
            return Days.days(value);
        } else if (Hours.class.equals(type)) {
            return Hours.hours(value);
        } else if (Minutes.class.equals(type)) {
            return Minutes.minutes(value);
        } else if (Months.class.equals(type)) {
            return Months.months(value);
        } else if (Seconds.class.equals(type)) {
            return Seconds.seconds(value);
        } else if (Weeks.class.equals(type)) {
            return Weeks.weeks(value);
        } else if (Years.class.equals(type)) {
            return Years.years(value);
        } else {
            throw new RuntimeException("Class \"" + type.getCanonicalName() + "\" not supported");
        }
    }
}
