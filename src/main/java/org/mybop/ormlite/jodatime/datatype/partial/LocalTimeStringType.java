package org.mybop.ormlite.jodatime.datatype.partial;

import com.j256.ormlite.field.FieldType;
import org.joda.time.LocalTime;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

/**
 * Type that persists a {@link LocalTime} object as a String in ISO8601 format (HH:mm:ss.SSS).
 *
 * @author Gautier Levert
 */
public class LocalTimeStringType extends StringFormatDataType<LocalTime> {

    private static final LocalTimeStringType INSTANCE = new LocalTimeStringType();

    public static LocalTimeStringType getSingleton() {
        return INSTANCE;
    }

    private LocalTimeStringType() {
        super(LocalTime.class);
    }

    @Override
    public String objToSql(final FieldType fieldType, final LocalTime localTime) {
        return localTime.toString();
    }

    @Override
    public LocalTime sqlToObj(final FieldType fieldType, final String sqlArg) {
        return LocalTime.parse(sqlArg);
    }

    @Override
    public int getDefaultWidth() {
        return 12;
    }

    @Override
    public boolean isComparable() {
        return true;
    }
}
