package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Hours;
import org.mybop.ormlite.jodatime.datatype.IntegerFormatDataType;

/**
 * Type that persists a {@link Hours} object as an integer (number of units).
 *
 * @author Gautier Levert
 * @see Hours
 */
public final class HoursType extends IntegerFormatDataType<Hours> {

    private static final HoursType INSTANCE = new HoursType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static HoursType getSingleton() {
        return INSTANCE;
    }

    private HoursType() {
        super(Hours.class);
    }

    @Override
    public Hours parseString(FieldType fieldType, String str) {
        if (isNaturalInteger(str)) {
            return Hours.hours(Integer.parseInt(str));
        }
        return Hours.parseHours(str);
    }

    @Override
    public Integer objToSql(final FieldType fieldType, final Hours hours) {
        return hours.getHours();
    }

    @Override
    public Hours sqlToObj(final FieldType fieldType, final Integer sqlArg) {
        return Hours.hours(sqlArg);
    }
}
