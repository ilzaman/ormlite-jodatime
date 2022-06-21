package org.mybop.ormlite.jodatime.datatype.instant;

import com.j256.ormlite.field.FieldType;
import org.joda.time.DateTime;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

import java.sql.SQLException;

/**
 * Type that persists a {@link DateTime} object as a String.
 * <p>
 * NOTE: timezone will be lost during persistence, only time offset will be restored, which is not exactly the same.
 *
 * @author Gautier Levert
 * @see DateTime
 * @see org.joda.time.DateTimeZone
 */
public final class DateTimeStringType extends StringFormatDataType<DateTime> {

    private static final DateTimeStringType INSTANCE = new DateTimeStringType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static DateTimeStringType getSingleton() {
        return INSTANCE;
    }

    private DateTimeStringType() {
        super(DateTime.class);
    }

    /**
     * Returns SQL argument for given {@link DateTime}
     *
     * @param fieldType Entity field information (may be null).
     * @param dateTime  The {@link DateTime} to be converted in sql arg.
     * @return ISO8601 time formatted string.
     */
    @Override
    public String objToSql(final FieldType fieldType, final DateTime dateTime) {
        return dateTime.toString();
    }

    /**
     * Parse given String into {@link DateTime} using {@link DateTime#parse(String)}.
     *
     * @param fieldType Entity field information (may be null).
     * @param sqlArg    The string to parse (must be in ISO format).
     * @return {@link DateTime} parsed.
     */
    @Override
    public DateTime sqlToObj(final FieldType fieldType, final String sqlArg) {
        return DateTime.parse(sqlArg);
    }

    /**
     * {@link DateTime} stored as String are comparable.
     *
     * @return true.
     */
    @Override
    public boolean isComparable() {
        return true;
    }

    /**
     * Gives a default value that must fit common cases.
     *
     * @return 50.
     */
    @Override
    public int getDefaultWidth() {
        return 50;
    }

    /**
     * {@link DateTime} are valid for version.
     *
     * @return true.
     */
    @Override
    public boolean isValidForVersion() {
        return true;
    }

    @Override
    public DateTime moveToNextValue(final Object currentValue) {
        return DateTime.now();
    }
}
