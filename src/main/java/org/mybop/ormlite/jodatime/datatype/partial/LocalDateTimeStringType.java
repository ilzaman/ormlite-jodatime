package org.mybop.ormlite.jodatime.datatype.partial;

import com.j256.ormlite.field.FieldType;
import org.joda.time.LocalDateTime;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

/**
 * Type that persists a {@link LocalDateTime} object as a String (in ISO format).
 *
 * @author Gautier Levert
 */
public final class LocalDateTimeStringType extends StringFormatDataType<LocalDateTime> {

    private static final LocalDateTimeStringType INSTANCE = new LocalDateTimeStringType();

    /**
     * Returns the singleton instance of LocalDateTimeLongType.
     *
     * @return singleton instance
     */
    public static LocalDateTimeStringType getSingleton() {
        return INSTANCE;
    }

    private LocalDateTimeStringType() {
        super(LocalDateTime.class);
    }

    @Override
    public String objToSql(final FieldType fieldType, final LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    @Override
    public LocalDateTime sqlToObj(final FieldType fieldType, final String sqlArg) {
        return LocalDateTime.parse(sqlArg);
    }

    @Override
    public int getDefaultWidth() {
        return 23;
    }

    @Override
    public boolean isComparable() {
        return true;
    }
}
