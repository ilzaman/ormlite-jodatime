package org.mybop.ormlite.jodatime.datatype.instant;

import com.j256.ormlite.field.FieldType;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

import java.sql.SQLException;

/**
 * Type that persists a {@link MutableDateTime} object as a String.
 * <p>
 * NOTE: timezone will be lost during persistence, only time offset will be restored, which is not exactly the same.
 *
 * @author Gautier Levert
 * @see MutableDateTime
 * @see org.joda.time.DateTimeZone
 */
public final class MutableDateTimeStringType extends StringFormatDataType<MutableDateTime> {

    private static final MutableDateTimeStringType INSTANCE = new MutableDateTimeStringType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static MutableDateTimeStringType getSingleton() {
        return INSTANCE;
    }

    private MutableDateTimeStringType() {
        super(MutableDateTime.class);
    }

    /**
     * Returns SQL argument for given {@link MutableDateTime}
     *
     * @param fieldType Entity field information (may be null).
     * @param dateTime  The {@link MutableDateTime} to be converted in sql arg.
     * @return ISO8601 time formatted string.
     */
    @Override
    public String objToSql(final FieldType fieldType, final MutableDateTime dateTime) {
        return dateTime.toString();
    }

    /**
     * Parse given String into {@link MutableDateTime} using {@link MutableDateTime#parse(String)}.
     *
     * @param fieldType Entity field information (may be null).
     * @param sqlArg    The string to parse (must be in ISO format).
     * @return {@link MutableDateTime} parsed.
     */
    @Override
    public MutableDateTime sqlToObj(final FieldType fieldType, final String sqlArg) {
        return MutableDateTime.parse(sqlArg);
    }

    /**
     * {@link MutableDateTime} stored as String are comparable.
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
    public MutableDateTime moveToNextValue(final Object currentValue) {
        return MutableDateTime.now();
    }
}
