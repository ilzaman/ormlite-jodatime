package org.mybop.ormlite.jodatime.datatype.duration;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.mybop.ormlite.jodatime.datatype.LongFormatDataType;

/**
 * Type that persists a {@link Instant} object as a long.
 *
 * @author Gautier Levert
 * @see Duration
 */
public final class DurationLongType extends LongFormatDataType<Duration> {

    private static final DurationLongType INSTANCE = new DurationLongType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static DurationLongType getSingleton() {
        return INSTANCE;
    }

    private DurationLongType() {
        super(Duration.class);
    }

    @Override
    public Duration parseString(final FieldType fieldType, final String str) {
        return Duration.parse(str);
    }

    @Override
    public Long objToSql(final FieldType fieldType, final Duration duration) {
        return duration.getMillis();
    }

    @Override
    public Duration sqlToObj(final FieldType fieldType, final Long sqlArg) {
        return Duration.millis(sqlArg);
    }
}
