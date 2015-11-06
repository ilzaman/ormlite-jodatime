package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.joda.time.*;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SingleFieldPeriodTypeTest {

    private ConnectionSource connectionSource;

    private static class Entity {
        @DatabaseField(generatedId = true)
        private Integer id;

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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entity)) return false;

            Entity entity = (Entity) o;

            if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
            if (days != null ? !days.equals(entity.days) : entity.days != null) return false;
            if (hours != null ? !hours.equals(entity.hours) : entity.hours != null) return false;
            if (minutes != null ? !minutes.equals(entity.minutes) : entity.minutes != null) return false;
            if (months != null ? !months.equals(entity.months) : entity.months != null) return false;
            if (seconds != null ? !seconds.equals(entity.seconds) : entity.seconds != null) return false;
            if (weeks != null ? !weeks.equals(entity.weeks) : entity.weeks != null) return false;
            return !(years != null ? !years.equals(entity.years) : entity.years != null);

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (days != null ? days.hashCode() : 0);
            result = 31 * result + (hours != null ? hours.hashCode() : 0);
            result = 31 * result + (minutes != null ? minutes.hashCode() : 0);
            result = 31 * result + (months != null ? months.hashCode() : 0);
            result = 31 * result + (seconds != null ? seconds.hashCode() : 0);
            result = 31 * result + (weeks != null ? weeks.hashCode() : 0);
            result = 31 * result + (years != null ? years.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "id=" + id +
                    ", days=" + days +
                    ", hours=" + hours +
                    ", minutes=" + minutes +
                    ", months=" + months +
                    ", seconds=" + seconds +
                    ", weeks=" + weeks +
                    ", years=" + years +
                    '}';
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

        connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test");

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

    @After
    public void tearDown() throws Exception {
        connectionSource.closeQuietly();
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

    @Test
    public void testCreateTable() throws Exception {
        TableUtils.createTable(connectionSource, Entity.class);
    }

    @Test
    public void testPersistence() throws Exception {
        Dao<Entity, Integer> dao = DaoManager.createDao(connectionSource, Entity.class);
        TableUtils.createTable(connectionSource, Entity.class);
        dao.create(entity);

        Entity retrieved = dao.queryForId(entity.getId());

        assertFalse(entity == retrieved);
        assertEquals("retrieved entity from database is not equals to persisted one", entity, retrieved);
    }
}