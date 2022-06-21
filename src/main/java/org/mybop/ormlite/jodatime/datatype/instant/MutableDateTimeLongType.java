package org.mybop.ormlite.jodatime.datatype.instant;

import com.j256.ormlite.field.FieldType;
import org.joda.time.MutableDateTime;
import org.mybop.ormlite.jodatime.datatype.LongFormatDataType;

import java.sql.SQLException;

/**
 * Type that persists a {@link MutableDateTime} object as a long.
 * <p>
 * NOTE: timezone will be lost during persistence, default {@link org.joda.time.DateTimeZone} will be used for loading.
 *
 * @author Gautier Levert
 * @see MutableDateTime
 * @see org.joda.time.DateTimeZone
 */
public final class MutableDateTimeLongType extends LongFormatDataType<MutableDateTime> {

    private static final MutableDateTimeLongType INSTANCE = new MutableDateTimeLongType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static MutableDateTimeLongType getSingleton() {
        return INSTANCE;
    }

    private MutableDateTimeLongType() {
        super(MutableDateTime.class);
    }

    @Override
    public MutableDateTime parseString(final FieldType fieldType, final String str) {
        return MutableDateTime.parse(str);
    }

    @Override
    public Long objToSql(final FieldType fieldType, final MutableDateTime javaObject) {
        return javaObject.getMillis();
    }

    @Override
    public MutableDateTime sqlToObj(final FieldType fieldType, final Long sqlArg) {
        return new MutableDateTime(sqlArg.longValue());
    }

    /**
     * {@link MutableDateTime} are valid for version.
     *
     * @return true.
     */
    @Override
    public boolean isValidForVersion() {
        return true;
    }

    @Override
    public MutableDateTime moveToNextValue(final Object currentValue) {
        return MutableDateTime.now();
    }
}
