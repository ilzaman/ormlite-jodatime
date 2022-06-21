package org.mybop.ormlite.jodatime.datatype;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

import java.sql.SQLException;

public abstract class LongFormatDataType<OBJ> extends BaseDataType<OBJ, Long> {

    /**
     * @param objClass Class of the Java objects (must not be null).
     */
    @SuppressWarnings("unchecked")
    protected LongFormatDataType(final Class<OBJ> objClass) {
        super(SqlType.LONG, Long.class, objClass);
    }

    /**
     * Return the SQL argument object extracted from the results associated with column in position columnPos. For
     * example, if the type is a date-long then this will return a long value or null.
     *
     * @param fieldType Associated FieldType which may be null.
     * @throws SQLException If there is a problem accessing the results data.
     */
    @Override
    public final Long resultToSqlArg(final FieldType fieldType, final DatabaseResults results, final int columnPos) throws SQLException {
        return results.getLong(columnPos);
    }

    /**
     * Sql arg long should not be escaped.
     *
     * @return false
     */
    @Override
    public final boolean isEscapedValue() {
        return false;
    }

    /**
     * SQL long type is comparable.
     *
     * @return true
     */
    @Override
    public final boolean isComparable() {
        return true;
    }
}
