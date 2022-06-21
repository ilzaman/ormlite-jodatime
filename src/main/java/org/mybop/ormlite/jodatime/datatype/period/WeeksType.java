package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Weeks;
import org.mybop.ormlite.jodatime.datatype.IntegerFormatDataType;

/**
 * Type that persists a {@link Weeks} object as an integer (number of units).
 *
 * @author Gautier Levert
 * @see Weeks
 */
public final class WeeksType extends IntegerFormatDataType<Weeks> {

    private static final WeeksType INSTANCE = new WeeksType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static WeeksType getSingleton() {
        return INSTANCE;
    }

    private WeeksType() {
        super(Weeks.class);
    }

    @Override
    public Weeks parseString(final FieldType fieldType, final String str) {
        if (isNaturalInteger(str)) {
            return Weeks.weeks(Integer.parseInt(str));
        }
        return Weeks.parseWeeks(str);
    }

    @Override
    public Integer objToSql(final FieldType fieldType, final Weeks weeks) {
        return weeks.getWeeks();
    }

    @Override
    public Weeks sqlToObj(final FieldType fieldType, final Integer sqlArg) {
        return Weeks.weeks(sqlArg);
    }
}
