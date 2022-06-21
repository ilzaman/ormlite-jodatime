package org.mybop.ormlite.jodatime.datatype.duration;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

/**
 * Type that persists a {@link Instant} object as a String.
 *
 * @author Gautier Levert
 * @see Duration
 */
public final class DurationStringType extends StringFormatDataType<Duration> {

    private static final DurationStringType INSTANCE = new DurationStringType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static DurationStringType getSingleton() {
        return INSTANCE;
    }

    private DurationStringType() {
        super(Duration.class);
    }

    @Override
    public String objToSql(final FieldType fieldType, final Duration duration) {
        return duration.toString();
    }

    @Override
    public Duration sqlToObj(final FieldType fieldType, final String sqlArg) {
        return Duration.parse(sqlArg);
    }
}
