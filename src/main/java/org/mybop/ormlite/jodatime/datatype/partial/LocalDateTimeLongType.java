package org.mybop.ormlite.jodatime.datatype.partial;

import com.j256.ormlite.field.FieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.mybop.ormlite.jodatime.datatype.LongFormatDataType;

/**
 * Type that persists a {@link LocalDateTime} object as a long (in milliseconds from start of day in UTC).
 *
 * @author Gautier Levert
 */
public final class LocalDateTimeLongType extends LongFormatDataType<LocalDateTime> {

    private static final LocalDateTimeLongType INSTANCE = new LocalDateTimeLongType();

    /**
     * Returns the singleton instance of LocalDateTimeLongType.
     *
     * @return singleton instance
     */
    public static LocalDateTimeLongType getSingleton() {
        return INSTANCE;
    }

    private LocalDateTimeLongType() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime parseString(final FieldType fieldType, final String str) {
        return LocalDateTime.parse(str);
    }

    @Override
    public Long objToSql(final FieldType fieldType, final LocalDateTime localDateTime) {
        return localDateTime.toDateTime(DateTimeZone.UTC).getMillis();
    }

    @Override
    public LocalDateTime sqlToObj(final FieldType fieldType, final Long sqlArg) {
        return new LocalDateTime(sqlArg, DateTimeZone.UTC);
    }
}
