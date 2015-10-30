package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DurationTypeTest {

    private static class Entity {
        @DatabaseField
        private Duration duration;

        @DatabaseField
        private Period period;

        public Duration getDuration() {
            return duration;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Period getPeriod() {
            return period;
        }

        public void setPeriod(Period period) {
            this.period = period;
        }
    }

    private DurationType durationType;

    private FieldType periodFieldType;
    private FieldType durationFieldType;

    @Before
    public void setUp() throws Exception {
        durationType = new DurationType();

        DataPersisterManager.registerDataPersisters(durationType);

        ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

        periodFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("period"), Entity.class);
        durationFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("duration"), Entity.class);
    }

    @Test
    public void testDurationSqlArg() throws Exception {
        Duration duration = Duration.standardMinutes(45L);
        Long sqlArg = durationType.javaToSqlArg(durationFieldType, duration);
        Duration retrieved = (Duration) durationType.sqlArgToJava(durationFieldType, sqlArg, 0);
        assertEquals(duration, retrieved);
    }

    @Test
    public void testPeriodSqlArg() throws Exception {
        Period period = Period.hours(1).plusMinutes(30);
        Long sqlArg = durationType.javaToSqlArg(periodFieldType, period);
        Period retrieved = (Period) durationType.sqlArgToJava(periodFieldType, sqlArg, 0);
        assertEquals(period, retrieved);
    }
}