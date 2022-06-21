package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Days;
import org.mybop.ormlite.jodatime.datatype.IntegerFormatDataType;

/**
 * Type that persists a {@link Days} object as an integer (number of units).
 *
 * @author Gautier Levert
 * @see Days
 */
public final class DaysType extends IntegerFormatDataType<Days> {

    private static final DaysType INSTANCE = new DaysType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static DaysType getSingleton() {
        return INSTANCE;
    }

    private DaysType() {
        super(Days.class);
    }

    @Override
    public Days parseString(final FieldType fieldType, final String str) {
        if (isNaturalInteger(str)) {
            return Days.days(Integer.parseInt(str));
        }
        return Days.parseDays(str);
    }

    @Override
    public Integer objToSql(final FieldType fieldType, final Days days) {
        return days.getDays();
    }

    @Override
    public Days sqlToObj(final FieldType fieldType, final Integer sqlArg) {
        return Days.days(sqlArg);
    }
}
