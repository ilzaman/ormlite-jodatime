package org.mybop.ormlitejodatime;

import com.j256.ormlite.field.DataPersisterManager;
import org.mybop.ormlitejodatime.datatype.*;

public final class OrmliteJodatimeUtils {

    public static final DateTimeZoneType DATE_TIME_ZONE_TYPE = new DateTimeZoneType();

    public static final DurationType DURATION_TYPE = new DurationType();

    public static final InstantType INSTANT_TYPE = new InstantType();

    public static final LocalDateTimeType LOCAL_DATE_TIME_TYPE = new LocalDateTimeType();

    public static final LocalDateType LOCAL_DATE_TYPE = new LocalDateType();

    public static final LocalTimeType LOCAL_TIME_TYPE = new LocalTimeType();

    public static final SingleFieldPeriodType SINGLE_FIELD_PERIOD_TYPE = new SingleFieldPeriodType();

    private OrmliteJodatimeUtils() {
        // only for static methods
    }

    public static DateTimeZoneType getDateTimeZoneType() {
        return DATE_TIME_ZONE_TYPE;
    }

    public static DurationType getDurationType() {
        return DURATION_TYPE;
    }

    public static InstantType getInstantType() {
        return INSTANT_TYPE;
    }

    public static LocalDateTimeType getLocalDateTimeType() {
        return LOCAL_DATE_TIME_TYPE;
    }

    public static LocalDateType getLocalDateType() {
        return LOCAL_DATE_TYPE;
    }

    public static LocalTimeType getLocalTimeType() {
        return LOCAL_TIME_TYPE;
    }

    public static SingleFieldPeriodType getSingleFieldPeriodType() {
        return SINGLE_FIELD_PERIOD_TYPE;
    }

    public static void registerTypes() {
        DataPersisterManager.registerDataPersisters(
                getDateTimeZoneType(),
                getDurationType(),
                getInstantType(),
                getLocalDateTimeType(),
                getLocalDateType(),
                getLocalTimeType(),
                getSingleFieldPeriodType());
    }
}
