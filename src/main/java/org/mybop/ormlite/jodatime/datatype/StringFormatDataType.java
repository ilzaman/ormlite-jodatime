package org.mybop.ormlite.jodatime.datatype;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

import java.sql.SQLException;

public abstract class StringFormatDataType<OBJ> extends BaseDataType<OBJ, String> {

    /**
     * @param objClass Class of the Java objects (must not be null).
     */
    @SuppressWarnings("unchecked")
    protected StringFormatDataType(final Class<OBJ> objClass) {
        super(SqlType.STRING, String.class, objClass);
    }

    /**
     * Sql arg string should be escaped.
     *
     * @return true
     */
    @Override
    public final boolean isEscapedValue() {
        return true;
    }

    /**
     * Return the SQL argument object extracted from the results associated with column in position columnPos. For
     * example, if the type is a date-long then this will return a long value or null.
     *
     * @param fieldType Associated FieldType which may be null.
     * @throws SQLException If there is a problem accessing the results data.
     */
    @Override
    public final String resultToSqlArg(final FieldType fieldType, final DatabaseResults results, final int columnPos) throws SQLException {
        return results.getString(columnPos);
    }

    @Override
    public final OBJ parseString(final FieldType fieldType, final String str) {
        return sqlToObj(fieldType, str);
    }
}
