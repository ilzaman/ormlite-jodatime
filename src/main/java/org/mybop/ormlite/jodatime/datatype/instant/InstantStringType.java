package org.mybop.ormlite.jodatime.datatype.instant;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Instant;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

import java.sql.SQLException;

/**
 * Type that persists a {@link Instant} object as a String.
 *
 * @author Gautier Levert
 * @see Instant
 */
public final class InstantStringType extends StringFormatDataType<Instant> {

    private static final InstantStringType INSTANCE = new InstantStringType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static InstantStringType getSingleton() {
        return INSTANCE;
    }

    private InstantStringType() {
        super(Instant.class);
    }

    @Override
    public String objToSql(final FieldType fieldType, final Instant instant) {
        return instant.toString();
    }

    @Override
    public Instant sqlToObj(final FieldType fieldType, final String sqlArg) {
        return Instant.parse(sqlArg);
    }

    /**
     * {@link Instant} stored as String are comparable.
     *
     * @return true.
     */
    @Override
    public boolean isComparable() {
        return true;
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
