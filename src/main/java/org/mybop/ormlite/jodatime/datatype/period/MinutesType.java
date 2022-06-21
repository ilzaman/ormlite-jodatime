package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Minutes;
import org.mybop.ormlite.jodatime.datatype.IntegerFormatDataType;

/**
 * Type that persists a {@link Minutes} object as an integer (number of units).
 *
 * @author Gautier Levert
 * @see Minutes
 */
public final class MinutesType extends IntegerFormatDataType<Minutes> {

    private static final MinutesType INSTANCE = new MinutesType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static MinutesType getSingleton() {
        return INSTANCE;
    }

    private MinutesType() {
        super(Minutes.class);
    }

    @Override
    public Minutes parseString(final FieldType fieldType, final String str) {
        if (isNaturalInteger(str)) {
            return Minutes.minutes(Integer.parseInt(str));
        }
        return Minutes.parseMinutes(str);
    }

    @Override
    public Integer objToSql(final FieldType fieldType, final Minutes minutes) {
        return minutes.getMinutes();
    }

    @Override
    public Minutes sqlToObj(final FieldType fieldType, final Integer sqlArg) {
        return Minutes.minutes(sqlArg);
    }
}
