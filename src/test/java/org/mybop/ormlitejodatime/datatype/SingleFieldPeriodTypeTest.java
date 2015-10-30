package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import org.joda.time.*;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SingleFieldPeriodTypeTest {

    private static class Entity {
        @DatabaseField
        private Days days;

        @DatabaseField
        private Hours hours;

        @DatabaseField
        private Minutes minutes;

        @DatabaseField
        private Months months;

        @DatabaseField
        private Seconds seconds;

        @DatabaseField
        private Weeks weeks;

        @DatabaseField
        private Years years;

        public Days getDays() {
            return days;
        }

        public void setDays(Days days) {
            this.days = days;
        }

        public Hours getHours() {
            return hours;
        }

        public void setHours(Hours hours) {
            this.hours = hours;
        }

        public Minutes getMinutes() {
            return minutes;
        }

        public void setMinutes(Minutes minutes) {
            this.minutes = minutes;
        }

        public Months getMonths() {
            return months;
        }

        public void setMonths(Months months) {
            this.months = months;
        }

        public Seconds getSeconds() {
            return seconds;
        }

        public void setSeconds(Seconds seconds) {
            this.seconds = seconds;
        }

        public Weeks getWeeks() {
            return weeks;
        }

        public void setWeeks(Weeks weeks) {
            this.weeks = weeks;
        }

        public Years getYears() {
            return years;
        }

        public void setYears(Years years) {
            this.years = years;
        }
    }

    private Entity entity = new Entity();

    private FieldType daysFieldType;
    private FieldType hoursFieldType;
    private FieldType minutesFieldType;
    private FieldType monthsFieldType;
    private FieldType secondsFieldType;
    private FieldType weeksFieldType;
    private FieldType yearsFieldType;

    private SingleFieldPeriodType singleFieldPeriodType = new SingleFieldPeriodType();

    @Before
    public void setUp() throws Exception {
        DataPersisterManager.registerDataPersisters(singleFieldPeriodType);

        ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

        daysFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("days"), Entity.class);
        hoursFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("hours"), Entity.class);
        minutesFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("minutes"), Entity.class);
        monthsFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("months"), Entity.class);
        secondsFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("seconds"), Entity.class);
        weeksFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("weeks"), Entity.class);
        yearsFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("years"), Entity.class);

        entity.setDays(Days.ONE);
        entity.setHours(Hours.TWO);
        entity.setMinutes(Minutes.THREE);
        entity.setMonths(Months.FOUR);
        entity.setSeconds(Seconds.seconds(5));
        entity.setWeeks(Weeks.weeks(6));
        entity.setYears(Years.years(7));
    }

    @Test
    public void testParseDefaultValue() throws Exception {
        assertEquals(Integer.valueOf(42), singleFieldPeriodType.parseDefaultString(daysFieldType, "42"));
    }

    @Test
    public void testDaysToSqlArg() throws Exception {
        Integer value = singleFieldPeriodType.javaToSqlArg(daysFieldType, entity.getDays());
        assertEquals("field doesn't persist good value", entity.getDays().getDays(), value.intValue());
    }

    @Test
    public void testHoursToSqlArg() throws Exception {
        Integer value = singleFieldPeriodType.javaToSqlArg(hoursFieldType, entity.getHours());
        assertEquals("field doesn't persist good value", entity.getHours().getHours(), value.intValue());
    }

    @Test
    public void testMinutesToSqlArg() throws Exception {
        Integer value = singleFieldPeriodType.javaToSqlArg(minutesFieldType, entity.getMinutes());
        assertEquals("field doesn't persist good value", entity.getMinutes().getMinutes(), value.intValue());
    }

    @Test
    public void testMonthsToSqlArg() throws Exception {
        Integer value = singleFieldPeriodType.javaToSqlArg(monthsFieldType, entity.getMonths());
        assertEquals("field doesn't persist good value", entity.getMonths().getMonths(), value.intValue());
    }

    @Test
    public void testSecondsToSqlArg() throws Exception {
        Integer value = singleFieldPeriodType.javaToSqlArg(secondsFieldType, entity.getSeconds());
        assertEquals("field doesn't persist good value", entity.getSeconds().getSeconds(), value.intValue());
    }

    @Test
    public void testWeeksToSqlArg() throws Exception {
        Integer value = singleFieldPeriodType.javaToSqlArg(weeksFieldType, entity.getWeeks());
        assertEquals("field doesn't persist good value", entity.getWeeks().getWeeks(), value.intValue());
    }

    @Test
    public void testYearsToSqlArg() throws Exception {
        Integer value = singleFieldPeriodType.javaToSqlArg(yearsFieldType, entity.getYears());
        assertEquals("field doesn't persist good value", entity.getYears().getYears(), value.intValue());
    }

    @Test
    public void testSqlArgToDays() throws Exception {
        BaseSingleFieldPeriod value = singleFieldPeriodType.sqlArgToJava(daysFieldType, Integer.valueOf(1), 1);
        assertTrue("bad class retrieved", value instanceof Days);
        assertEquals("retrieved value is not good", Days.days(1), value);
    }

    @Test
    public void testSqlArgToHours() throws Exception {
        BaseSingleFieldPeriod value = singleFieldPeriodType.sqlArgToJava(hoursFieldType, Integer.valueOf(2), 2);
        assertTrue("bad class retrieved", value instanceof Hours);
        assertEquals("retrieved value is not good", Hours.hours(2), value);
    }

    @Test
    public void testSqlArgToMinutes() throws Exception {
        BaseSingleFieldPeriod value = singleFieldPeriodType.sqlArgToJava(minutesFieldType, Integer.valueOf(3), 3);
        assertTrue("bad class retrieved", value instanceof Minutes);
        assertEquals("retrieved value is not good", Minutes.minutes(3), value);
    }

    @Test
    public void testSqlArgToMonths() throws Exception {
        BaseSingleFieldPeriod value = singleFieldPeriodType.sqlArgToJava(monthsFieldType, Integer.valueOf(4), 4);
        assertTrue("bad class retrieved", value instanceof Months);
        assertEquals("retrieved value is not good", Months.months(4), value);
    }

    @Test
    public void testSqlArgToSeconds() throws Exception {
        BaseSingleFieldPeriod value = singleFieldPeriodType.sqlArgToJava(secondsFieldType, Integer.valueOf(5), 5);
        assertTrue("bad class retrieved", value instanceof Seconds);
        assertEquals("retrieved value is not good", Seconds.seconds(5), value);
    }

    @Test
    public void testSqlArgToWeeks() throws Exception {
        BaseSingleFieldPeriod value = singleFieldPeriodType.sqlArgToJava(weeksFieldType, Integer.valueOf(6), 6);
        assertTrue("bad class retrieved", value instanceof Weeks);
        assertEquals("retrieved value is not good", Weeks.weeks(6), value);
    }

    @Test
    public void testSqlArgToYears() throws Exception {
        BaseSingleFieldPeriod value = singleFieldPeriodType.sqlArgToJava(yearsFieldType, Integer.valueOf(7), 7);
        assertTrue("bad class retrieved", value instanceof Years);
        assertEquals("retrieved value is not good", Years.years(7), value);
    }
}