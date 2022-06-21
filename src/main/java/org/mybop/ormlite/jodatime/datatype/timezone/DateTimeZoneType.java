package org.mybop.ormlite.jodatime.datatype.timezone;

import com.j256.ormlite.field.FieldType;
import org.joda.time.DateTimeZone;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

/**
 * Type that persists a {@link DateTimeZone} object as a String.
 *
 * @author Gautier Levert
 */
public final class DateTimeZoneType extends StringFormatDataType<DateTimeZone> {

    private static final DateTimeZoneType INSTANCE = new DateTimeZoneType();

    /**
     * Returns the singleton instance of DateTimeZoneType.
     *
     * @return singleton instance
     */
    public static DateTimeZoneType getSingleton() {
        return INSTANCE;
    }

    private DateTimeZoneType() {
        super(DateTimeZone.class);
    }

    @Override
    public final DateTimeZone sqlToObj(final FieldType fieldType, final String str) {
        return DateTimeZone.forID(str);
    }

    @Override
    public String objToSql(final FieldType fieldType, final DateTimeZone dateTimeZone) {
        return dateTimeZone.getID();
    }

    /**
     * Max length of {@link DateTimeZone#getAvailableIDs()}.
     *
     * @return 32
     */
    @Override
    public int getDefaultWidth() {
        return 32;
    }

    /**
     * DateTimeZon ids are not comparable.
     *
     * @return false
     */
    @Override
    public boolean isComparable() {
        return false;
    }
}
