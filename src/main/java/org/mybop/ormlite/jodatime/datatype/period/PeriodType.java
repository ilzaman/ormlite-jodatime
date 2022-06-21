package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.Period;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

/**
 * Type that persists a {@link Period} object as a String (ISO8601 format).
 *
 * @author Gautier Levert
 * @see Period
 **/
public final class PeriodType extends StringFormatDataType<Period> {

    private static final PeriodType INSTANCE = new PeriodType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static PeriodType getSingleton() {
        return INSTANCE;
    }

    private PeriodType() {
        super(Period.class);
    }

    /**
     * {@link Period} as String are no more comparable.
     *
     * @return false.
     */
    @Override
    public boolean isComparable() {
        return false;
    }

    @Override
    public int getDefaultWidth() {
        return 128;
    }

    @Override
    public Period sqlToObj(final FieldType fieldType, final String str) {
        return Period.parse(str);
    }

    @Override
    public String objToSql(final FieldType fieldType, final Period period) {
        return period.toString();
    }
}
