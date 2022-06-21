package org.mybop.ormlite.jodatime.datatype.instant;

import com.j256.ormlite.field.FieldType;
import org.joda.time.DateTime;
import org.mybop.ormlite.jodatime.datatype.LongFormatDataType;

import java.sql.SQLException;

/**
 * Type that persists a {@link DateTime} object as a long.
 * <p>
 * NOTE: timezone will be lost during persistence, default {@link org.joda.time.DateTimeZone} will be used for loading.
 *
 * @author Gautier Levert
 * @see DateTime
 * @see org.joda.time.DateTimeZone
 */
public final class DateTimeLongType extends LongFormatDataType<DateTime> {

    private static final DateTimeLongType INSTANCE = new DateTimeLongType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static DateTimeLongType getSingleton() {
        return INSTANCE;
    }

    private DateTimeLongType() {
        super(DateTime.class);
    }

    @Override
    public DateTime parseString(final FieldType fieldType, final String str) {
        return DateTime.parse(str);
    }

    @Override
    public Long objToSql(final FieldType fieldType, final DateTime dateTime) {
        return dateTime.getMillis();
    }

    @Override
    public DateTime sqlToObj(final FieldType fieldType, final Long sqlArg) {
        return new DateTime(sqlArg.longValue());
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
    public Object moveToNextValue(final Object currentValue) {
        return DateTime.now();
    }
}
