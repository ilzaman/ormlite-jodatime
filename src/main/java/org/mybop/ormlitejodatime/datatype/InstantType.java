package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableInstant;

import java.sql.SQLException;

public final class InstantType extends BaseDataType {

    public InstantType() {
        super(SqlType.LONG, new Class[]{Instant.class, DateTime.class, MutableDateTime.class});
    }

    @Override
    public boolean isEscapedValue() {
        return false;
    }

    @Override
    public Long parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
        return javaToSqlArg(fieldType, Instant.parse(defaultStr));
    }

    @Override
    public Long resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getLong(columnPos);
    }

    @Override
    public Long javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        return ((ReadableInstant) javaObject).getMillis();
    }

    @Override
    public ReadableInstant sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
        Class<?> type = fieldType.getType();
        long value = (Long) sqlArg;
        if (Instant.class.equals(type)) {
            return new Instant(value);
        } else if (DateTime.class.equals(type)) {
            return new DateTime(value);
        } else if (MutableDateTime.class.equals(type)) {
            return new MutableDateTime(value);
        } else {
            throw new RuntimeException("Class \"" + type.getCanonicalName() + "\" not supported");
        }
    }
}
