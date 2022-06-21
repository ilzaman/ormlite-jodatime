package org.mybop.ormlite.jodatime.datatype.partial;

import com.j256.ormlite.field.FieldType;
import org.joda.time.LocalTime;
import org.mybop.ormlite.jodatime.datatype.IntegerFormatDataType;

/**
 * Type that persists a {@link LocalTime} object as an integer (milliseconds from start of day).
 *
 * @author Gautier Levert
 */
public final class LocalTimeIntegerType extends IntegerFormatDataType<LocalTime> {

    private static final LocalTimeIntegerType INSTANCE = new LocalTimeIntegerType();

    public static LocalTimeIntegerType getSingleton() {
        return INSTANCE;
    }

    private LocalTimeIntegerType() {
        super(LocalTime.class);
    }

    @Override
    public LocalTime parseString(final FieldType fieldType, final String str) {
        return LocalTime.parse(str);
    }

    @Override
    public Integer objToSql(final FieldType fieldType, final LocalTime localTime) {
        return localTime.getMillisOfDay();
    }

    @Override
    public LocalTime sqlToObj(final FieldType fieldType, final Integer sqlArg) {
        return LocalTime.fromMillisOfDay(sqlArg);
    }
}
