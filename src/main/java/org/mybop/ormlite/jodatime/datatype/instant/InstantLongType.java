package org.mybop.ormlite.jodatime.datatype.instant;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Instant;
import org.mybop.ormlite.jodatime.datatype.LongFormatDataType;

import java.sql.SQLException;


/**
 * Type that persists a {@link Instant} object as a long.
 *
 * @author Gautier Levert
 * @see Instant
 */
public final class InstantLongType extends LongFormatDataType<Instant> {

    private static final InstantLongType INSTANCE = new InstantLongType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static InstantLongType getSingleton() {
        return INSTANCE;
    }

    private InstantLongType() {
        super(Instant.class);
    }

    @Override
    public Instant parseString(final FieldType fieldType, final String str) {
        return Instant.parse(str);
    }

    @Override
    public Long objToSql(final FieldType fieldType, final Instant instant) {
        return instant.getMillis();
    }

    @Override
    public Instant sqlToObj(final FieldType fieldType, final Long millis) {
        return new Instant(millis.longValue());
    }

    @Override
    public boolean isValidForVersion() {
        return true;
    }

    @Override
    public Instant moveToNextValue(final Object currentValue) {
        return Instant.now();
    }
}
