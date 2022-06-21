package org.mybop.ormlite.jodatime;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.MonthDay;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.YearMonth;
import org.joda.time.Years;

@DatabaseTable
public final class Entity {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private Duration duration;

    @DatabaseField
    private DateTime dateTime;

    @DatabaseField
    private Instant instant;

    @DatabaseField
    private MutableDateTime mutableDateTime;

    @DatabaseField
    private LocalDateTime localDateTime;

    @DatabaseField
    private LocalDate localDate;

    @DatabaseField
    private LocalTime localTime;

    @DatabaseField
    private MonthDay monthDay;

    @DatabaseField
    private YearMonth yearMonth;

    @DatabaseField
    private Days days;

    @DatabaseField
    private Hours hours;

    @DatabaseField
    private Minutes minutes;

    @DatabaseField
    private Months months;

    @DatabaseField
    private MutablePeriod mutablePeriod;

    @DatabaseField
    private Period period;

    @DatabaseField
    private Seconds seconds;

    @DatabaseField
    private Weeks weeks;

    @DatabaseField
    private Years years;

    @DatabaseField
    private DateTimeZone dateTimeZone;

    public Integer getId() {
        return id;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(final Duration duration) {
        this.duration = duration;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(final Instant instant) {
        this.instant = instant;
    }

    public MutableDateTime getMutableDateTime() {
        return mutableDateTime;
    }

    public void setMutableDateTime(final MutableDateTime mutableDateTime) {
        this.mutableDateTime = mutableDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(final LocalTime localTime) {
        this.localTime = localTime;
    }

    public MonthDay getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(final MonthDay monthDay) {
        this.monthDay = monthDay;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(final YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Days getDays() {
        return days;
    }

    public void setDays(final Days days) {
        this.days = days;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(final Hours hours) {
        this.hours = hours;
    }

    public Minutes getMinutes() {
        return minutes;
    }

    public void setMinutes(final Minutes minutes) {
        this.minutes = minutes;
    }

    public Months getMonths() {
        return months;
    }

    public void setMonths(final Months months) {
        this.months = months;
    }

    public MutablePeriod getMutablePeriod() {
        return mutablePeriod;
    }

    public void setMutablePeriod(final MutablePeriod mutablePeriod) {
        this.mutablePeriod = mutablePeriod;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(final Period period) {
        this.period = period;
    }

    public Seconds getSeconds() {
        return seconds;
    }

    public void setSeconds(final Seconds seconds) {
        this.seconds = seconds;
    }

    public Weeks getWeeks() {
        return weeks;
    }

    public void setWeeks(final Weeks weeks) {
        this.weeks = weeks;
    }

    public Years getYears() {
        return years;
    }

    public void setYears(final Years years) {
        this.years = years;
    }

    public DateTimeZone getDateTimeZone() {
        return dateTimeZone;
    }

    public void setDateTimeZone(final DateTimeZone dateTimeZone) {
        this.dateTimeZone = dateTimeZone;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Entity entity = (Entity) o;

        if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
        if (duration != null ? !duration.equals(entity.duration) : entity.duration != null) return false;
        if (dateTime != null ? !dateTime.equals(entity.dateTime) : entity.dateTime != null) return false;
        if (instant != null ? !instant.equals(entity.instant) : entity.instant != null) return false;
        if (mutableDateTime != null ? !mutableDateTime.equals(entity.mutableDateTime) : entity.mutableDateTime != null)
            return false;
        if (localDateTime != null ? !localDateTime.equals(entity.localDateTime) : entity.localDateTime != null)
            return false;
        if (localDate != null ? !localDate.equals(entity.localDate) : entity.localDate != null) return false;
        if (localTime != null ? !localTime.equals(entity.localTime) : entity.localTime != null) return false;
        if (monthDay != null ? !monthDay.equals(entity.monthDay) : entity.monthDay != null) return false;
        if (yearMonth != null ? !yearMonth.equals(entity.yearMonth) : entity.yearMonth != null) return false;
        if (days != null ? !days.equals(entity.days) : entity.days != null) return false;
        if (hours != null ? !hours.equals(entity.hours) : entity.hours != null) return false;
        if (minutes != null ? !minutes.equals(entity.minutes) : entity.minutes != null) return false;
        if (months != null ? !months.equals(entity.months) : entity.months != null) return false;
        if (mutablePeriod != null ? !mutablePeriod.equals(entity.mutablePeriod) : entity.mutablePeriod != null)
            return false;
        if (period != null ? !period.equals(entity.period) : entity.period != null) return false;
        if (seconds != null ? !seconds.equals(entity.seconds) : entity.seconds != null) return false;
        if (weeks != null ? !weeks.equals(entity.weeks) : entity.weeks != null) return false;
        if (years != null ? !years.equals(entity.years) : entity.years != null) return false;
        return dateTimeZone != null ? dateTimeZone.equals(entity.dateTimeZone) : entity.dateTimeZone == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (instant != null ? instant.hashCode() : 0);
        result = 31 * result + (mutableDateTime != null ? mutableDateTime.hashCode() : 0);
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        result = 31 * result + (localDate != null ? localDate.hashCode() : 0);
        result = 31 * result + (localTime != null ? localTime.hashCode() : 0);
        result = 31 * result + (monthDay != null ? monthDay.hashCode() : 0);
        result = 31 * result + (yearMonth != null ? yearMonth.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (minutes != null ? minutes.hashCode() : 0);
        result = 31 * result + (months != null ? months.hashCode() : 0);
        result = 31 * result + (mutablePeriod != null ? mutablePeriod.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (seconds != null ? seconds.hashCode() : 0);
        result = 31 * result + (weeks != null ? weeks.hashCode() : 0);
        result = 31 * result + (years != null ? years.hashCode() : 0);
        result = 31 * result + (dateTimeZone != null ? dateTimeZone.hashCode() : 0);
        return result;
    }
}
