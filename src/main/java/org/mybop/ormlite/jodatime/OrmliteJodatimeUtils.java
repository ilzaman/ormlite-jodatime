package org.mybop.ormlite.jodatime;

import com.j256.ormlite.field.DataPersisterManager;
import org.mybop.ormlite.jodatime.datatype.duration.DurationLongType;
import org.mybop.ormlite.jodatime.datatype.instant.DateTimeLongType;
import org.mybop.ormlite.jodatime.datatype.instant.InstantLongType;
import org.mybop.ormlite.jodatime.datatype.instant.MutableDateTimeLongType;
import org.mybop.ormlite.jodatime.datatype.partial.LocalDateTimeLongType;
import org.mybop.ormlite.jodatime.datatype.partial.LocalDateType;
import org.mybop.ormlite.jodatime.datatype.partial.LocalTimeIntegerType;
import org.mybop.ormlite.jodatime.datatype.partial.MonthDayType;
import org.mybop.ormlite.jodatime.datatype.partial.YearMonthType;
import org.mybop.ormlite.jodatime.datatype.period.DaysType;
import org.mybop.ormlite.jodatime.datatype.period.HoursType;
import org.mybop.ormlite.jodatime.datatype.period.MinutesType;
import org.mybop.ormlite.jodatime.datatype.period.MonthsType;
import org.mybop.ormlite.jodatime.datatype.period.MutablePeriodType;
import org.mybop.ormlite.jodatime.datatype.period.PeriodType;
import org.mybop.ormlite.jodatime.datatype.period.SecondsType;
import org.mybop.ormlite.jodatime.datatype.period.WeeksType;
import org.mybop.ormlite.jodatime.datatype.period.YearsType;
import org.mybop.ormlite.jodatime.datatype.timezone.DateTimeZoneType;

/**
 * Helper class that allows to register all Joda-Time types in a single call.
 *
 * @author Gautier Levert
 */
public final class OrmliteJodatimeUtils {

    private OrmliteJodatimeUtils() {
        // only for static methods
    }

    public static void registerTypes() {
        DataPersisterManager.registerDataPersisters(
                DurationLongType.getSingleton(),
                DateTimeLongType.getSingleton(),
                InstantLongType.getSingleton(),
                MutableDateTimeLongType.getSingleton(),
                LocalDateTimeLongType.getSingleton(),
                LocalDateType.getSingleton(),
                LocalTimeIntegerType.getSingleton(),
                MonthDayType.getSingleton(),
                YearMonthType.getSingleton(),
                DaysType.getSingleton(),
                HoursType.getSingleton(),
                MinutesType.getSingleton(),
                MonthsType.getSingleton(),
                MutablePeriodType.getSingleton(),
                PeriodType.getSingleton(),
                SecondsType.getSingleton(),
                WeeksType.getSingleton(),
                YearsType.getSingleton(),
                DateTimeZoneType.getSingleton()
        );
    }
}
