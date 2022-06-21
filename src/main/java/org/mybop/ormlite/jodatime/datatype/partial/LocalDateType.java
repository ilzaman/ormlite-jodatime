package org.mybop.ormlite.jodatime.datatype.partial;

import com.j256.ormlite.field.FieldType;
import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;
import org.mybop.ormlite.jodatime.datatype.StringFormatDataType;

/**
 * Type that persists a {@link LocalDate} object as a String (ISO format: yyyy-MM-dd).
 *
 * @author Gautier Levert
 */
public final class LocalDateType extends StringFormatDataType<LocalDate> {

    private static final LocalDateType INSTANCE = new LocalDateType();

    /**
     * Returns the singleton instance of LocalDateType.
     *
     * @return singleton instance
     */
    public static LocalDateType getSingleton() {
        return INSTANCE;
    }

    private LocalDateType() {
        super(LocalDate.class);
    }

    /**
     * Gives the length of a LocalDate String in ISO format (yyyy-MM-dd)
     *
     * @return 10
     */
    @Override
    public int getDefaultWidth() {
        return 10;
    }

    /**
     * LocalDateTime in String format as String are comparable.
     *
     * @return true
     */
    @Override
    public boolean isComparable() {
        return true;
    }

    /**
     * Parses a {@code LocalDate} from the specified string.
     * <p>
     * This uses {@link ISODateTimeFormat#localDateParser()}.
     *
     * @param str the string to parse, not null
     * @since 2.0
     */
    @Override
    public LocalDate sqlToObj(final FieldType fieldType, final String str) {
        return LocalDate.parse(str);
    }

    @Override
    public String objToSql(final FieldType fieldType, final LocalDate localDate) {
        return localDate.toString();
    }
}
