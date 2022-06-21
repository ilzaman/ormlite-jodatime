package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Seconds;
import org.mybop.ormlite.jodatime.datatype.IntegerFormatDataType;

/**
 * Type that persists a {@link Seconds} object as an integer (number of units).
 *
 * @author Gautier Levert
 * @see Seconds
 */
public final class SecondsType extends IntegerFormatDataType<Seconds> {

    private static final SecondsType INSTANCE = new SecondsType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static SecondsType getSingleton() {
        return INSTANCE;
    }

    private SecondsType() {
        super(Seconds.class);
    }

    @Override
    public Seconds parseString(final FieldType fieldType, final String str) {
        if (isNaturalInteger(str)) {
            return Seconds.seconds(Integer.parseInt(str));
        }
        return Seconds.parseSeconds(str);
    }

    @Override
    public Integer objToSql(final FieldType fieldType, final Seconds seconds) {
        return seconds.getSeconds();
    }

    @Override
    public Seconds sqlToObj(final FieldType fieldType, final Integer sqlArg) {
        return Seconds.seconds(sqlArg);
    }
}
