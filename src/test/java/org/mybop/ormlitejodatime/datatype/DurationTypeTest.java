package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DurationTypeTest {

    private ConnectionSource connectionSource;

    private static class Entity {

        @DatabaseField(generatedId = true)
        private int id;

        @DatabaseField
        private Duration duration;

        @DatabaseField
        private Period period;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entity)) return false;

            Entity entity = (Entity) o;

            if (id != entity.id) return false;
            if (duration != null ? !duration.equals(entity.duration) : entity.duration != null) return false;
            return !(period != null ? !period.equals(entity.period) : entity.period != null);

        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (duration != null ? duration.hashCode() : 0);
            result = 31 * result + (period != null ? period.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "id=" + id +
                    ", duration=" + duration +
                    ", period=" + period +
                    '}';
        }
    }

    private DurationType durationType;

    private FieldType periodFieldType;
    private FieldType durationFieldType;

    @Before
    public void setUp() throws Exception {
        durationType = new DurationType();

        DataPersisterManager.registerDataPersisters(durationType);

        connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test");

        periodFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("period"), Entity.class);
        durationFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("duration"), Entity.class);
    }

    @After
    public void tearDown() throws Exception {
        connectionSource.closeQuietly();
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

    @Test
    public void testCreateTable() throws Exception {
        TableUtils.createTable(connectionSource, Entity.class);
    }

    @Test
    public void testPersistence() throws Exception {
        Dao<Entity, Integer> dao = DaoManager.createDao(connectionSource, Entity.class);
        TableUtils.createTable(connectionSource, Entity.class);

        Entity entity = new Entity();
        entity.setDuration(Duration.standardMinutes(15));
        entity.setPeriod(Period.hours(3));

        dao.create(entity);

        Entity retrieved = dao.queryForId(entity.getId());

        assertFalse(entity == retrieved);
        assertEquals("retrieved entity from database is not equals to persisted one", entity, retrieved);
    }
}