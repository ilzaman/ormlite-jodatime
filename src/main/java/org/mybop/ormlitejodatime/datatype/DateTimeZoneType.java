package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;
import org.joda.time.DateTimeZone;

import java.sql.SQLException;

public final class DateTimeZoneType extends BaseDataType {

    public DateTimeZoneType() {
        super(SqlType.STRING, new Class[]{DateTimeZone.class});
    }

    @Override
    public boolean isEscapedValue() {
        return true;
    }

    @Override
    public int getDefaultWidth() {
        return 32;
    }

    @Override
    public String parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
        assert DateTimeZone.getAvailableIDs().contains(defaultStr);
        return defaultStr;
    }

    @Override
    public String resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getString(columnPos);
    }

    @Override
    public String javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        return ((DateTimeZone) javaObject).getID();
    }

    @Override
    public DateTimeZone sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
        return DateTimeZone.forID((String) sqlArg);
    }
}
