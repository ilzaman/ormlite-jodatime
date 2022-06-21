package org.mybop.ormlite.jodatime.datatype.period;

import com.j256.ormlite.field.FieldType;
import org.joda.time.MutablePeriod;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

/**
 * Type that persists a {@link MutablePeriod} object as a String (ISO8601 format).
 *
 * @author Gautier Levert
 * @see MutablePeriod
 **/
public final class MutablePeriodType extends StringFormatDataType<MutablePeriod> {

    private static final MutablePeriodType INSTANCE = new MutablePeriodType();

    /**
     * Gets the single instance for this type.
     *
     * @return singleton instance
     */
    public static MutablePeriodType getSingleton() {
        return INSTANCE;
    }

    private MutablePeriodType() {
        super(MutablePeriod.class);
    }

    @Override
    public String objToSql(final FieldType fieldType, final MutablePeriod period) {
        return period.toString();
    }

    @Override
    public MutablePeriod sqlToObj(final FieldType fieldType, final String sqlArg) {
        return MutablePeriod.parse(sqlArg);
    }

    /**
     * {@link MutablePeriod} as String are no more comparable.
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
}
