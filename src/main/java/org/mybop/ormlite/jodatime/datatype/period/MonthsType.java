package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Months;
import org.mybop.ormlite.jodatime.datatype.IntegerFormatDataType;

/**
 * Type that persists a {@link Months} object as an integer (number of units).
 *
 * @author Gautier Levert
 * @see Months
 */
public final class MonthsType extends IntegerFormatDataType<Months> {

    private static final MonthsType INSTANCE = new MonthsType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static MonthsType getSingleton() {
        return INSTANCE;
    }

    private MonthsType() {
        super(Months.class);
    }

    @Override
    public Months parseString(final FieldType fieldType, final String str) {
        if (isNaturalInteger(str)) {
            return Months.months(Integer.parseInt(str));
        }
        return Months.parseMonths(str);
    }

    @Override
    public Integer objToSql(final FieldType fieldType, final Months months) {
        return months.getMonths();
    }

    @Override
    public Months sqlToObj(final FieldType fieldType, final Integer sqlArg) {
        return Months.months(sqlArg);
    }
}
