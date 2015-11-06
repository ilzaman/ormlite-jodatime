package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocalTypeTest {

    private static class Entity {
        @DatabaseField(generatedId = true)
        private Integer id;
        @DatabaseField
        private LocalDate localDate;
        @DatabaseField
        private LocalTime localTime;
        @DatabaseField
        private LocalDateTime localDateTime;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public LocalTime getLocalTime() {
            return localTime;
        }

        public void setLocalTime(LocalTime localTime) {
            this.localTime = localTime;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entity)) return false;

            Entity entity = (Entity) o;

            if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
            if (localDate != null ? !localDate.equals(entity.localDate) : entity.localDate != null) return false;
            if (localTime != null ? !localTime.equals(entity.localTime) : entity.localTime != null) return false;
            return !(localDateTime != null ? !localDateTime.equals(entity.localDateTime) : entity.localDateTime != null);

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (localDate != null ? localDate.hashCode() : 0);
            result = 31 * result + (localTime != null ? localTime.hashCode() : 0);
            result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "id=" + id +
                    ", localDate=" + localDate +
                    ", localTime=" + localTime +
                    ", localDateTime=" + localDateTime +
                    '}';
        }
    }

    private JdbcConnectionSource connectionSource;

    private LocalDateTimeType localDateTimeType = new LocalDateTimeType();

    private LocalDateType localDateType = new LocalDateType();

    private LocalTimeType localTimeType = new LocalTimeType();

    private Entity entity = new Entity();

    private FieldType localDateTimeFieldType;
    private FieldType localTimeFieldType;
    private FieldType localDateFieldType;

    @Before
    public void setUp() throws Exception {
        DataPersisterManager.registerDataPersisters(localDateTimeType, localDateType, localTimeType);
        connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test");

        localDateFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("localDate"), Entity.class);
        localDateTimeFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("localDateTime"), Entity.class);
        localTimeFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("localTime"), Entity.class);

        entity.setLocalDate(LocalDate.now());
        entity.setLocalTime(LocalTime.now());
        entity.setLocalDateTime(LocalDateTime.now());
    }

    @After
    public void tearDown() throws Exception {
        connectionSource.closeQuietly();
    }

    @Test
    public void testLocalDateTime() throws Exception {
        Object sqlArg = localDateTimeType.javaToSqlArg(localDateTimeFieldType, entity.getLocalDateTime());
        Object retrieved = localDateTimeType.sqlArgToJava(localDateTimeFieldType, sqlArg, 0);

        assertTrue(retrieved instanceof LocalDateTime);
        assertEquals(entity.getLocalDateTime(), retrieved);
    }

    @Test
    public void testLocalDate() throws Exception {
        Object sqlArg = localDateType.javaToSqlArg(localDateFieldType, entity.getLocalDate());
        Object retrieved = localDateType.sqlArgToJava(localDateFieldType, sqlArg, 0);

        assertTrue(retrieved instanceof LocalDate);
        assertEquals(entity.getLocalDate(), retrieved);
    }

    @Test
    public void testLocalTime() throws Exception {
        Object sqlArg = localTimeType.javaToSqlArg(localTimeFieldType, entity.getLocalTime());
        Object retrieved = localTimeType.sqlArgToJava(localTimeFieldType, sqlArg, 0);

        assertTrue(retrieved instanceof LocalTime);
        assertEquals(entity.getLocalTime(), retrieved);
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