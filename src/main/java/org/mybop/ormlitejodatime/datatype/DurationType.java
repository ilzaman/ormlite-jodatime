package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;
import org.joda.time.Duration;
import org.joda.time.Period;

import java.sql.SQLException;

public final class DurationType extends BaseDataType {

    public DurationType() {
        super(SqlType.LONG, new Class<?>[]{Duration.class, Period.class});
    }

    @Override
    public boolean isEscapedValue() {
        return false;
    }

    @Override
    public Long parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
        return javaToSqlArg(fieldType, Duration.parse(defaultStr));
    }

    @Override
    public Long resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getLong(columnPos);
    }

    @Override
    public Long javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        if (javaObject instanceof Duration) {
            return ((Duration) javaObject).getMillis();
        } else if (javaObject instanceof Period) {
            return ((Period) javaObject).toStandardDuration().getMillis();
        } else {
            throw new RuntimeException("Class \"" + javaObject.getClass().getCanonicalName() + "\" not supported");
        }
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
        Duration duration = Duration.millis(((Long) sqlArg).longValue());
        Class<?> type = fieldType.getType();
        if (Period.class.equals(type)) {
            return duration.toPeriod();
        } else if (Duration.class.equals(type)) {
            return duration;
        } else {
            throw new RuntimeException("Class \"" + type.getCanonicalName() + "\" not supported");
        }
    }
}
