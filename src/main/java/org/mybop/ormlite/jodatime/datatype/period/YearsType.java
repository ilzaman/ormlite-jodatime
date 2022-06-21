package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Years;
import org.mybop.ormlite.jodatime.datatype.IntegerFormatDataType;

/**
 * Type that persists a {@link Years} object as an integer (number of units).
 *
 * @author Gautier Levert
 * @see Years
 */
public final class YearsType extends IntegerFormatDataType<Years> {

    private static final YearsType INSTANCE = new YearsType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static YearsType getSingleton() {
        return INSTANCE;
    }

    private YearsType() {
        super(Years.class);
    }

    @Override
    public Years parseString(final FieldType fieldType, final String str) {
        if (isNaturalInteger(str)) {
            return Years.years(Integer.parseInt(str));
        }
        return Years.parseYears(str);
    }

    @Override
    public Integer objToSql(final FieldType fieldType, final Years years) {
        return years.getYears();
    }

    @Override
    public Years sqlToObj(final FieldType fieldType, final Integer sqlArg) {
        return Years.years(sqlArg);
    }
}
