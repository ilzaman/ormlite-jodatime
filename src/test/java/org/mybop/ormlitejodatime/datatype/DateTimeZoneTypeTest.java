package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class DateTimeZoneTypeTest {

    private static class Entity {

        @DatabaseField(generatedId = true)
        private Integer id;

        @DatabaseField
        private DateTimeZone dateTimeZone;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public DateTimeZone getDateTimeZone() {
            return dateTimeZone;
        }

        public void setDateTimeZone(DateTimeZone dateTimeZone) {
            this.dateTimeZone = dateTimeZone;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entity)) return false;

            Entity entity = (Entity) o;

            if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
            return !(dateTimeZone != null ? !dateTimeZone.equals(entity.dateTimeZone) : entity.dateTimeZone != null);

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (dateTimeZone != null ? dateTimeZone.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "id=" + id +
                    ", dateTimeZone=" + dateTimeZone +
                    '}';
        }
    }

    private DateTimeZoneType dateTimeZoneType = new DateTimeZoneType();

    private ConnectionSource connectionSource;

    private FieldType fieldType;

    @Before
    public void setUp() throws Exception {
        DataPersisterManager.registerDataPersisters(dateTimeZoneType);
        connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test");
        fieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("dateTimeZone"), Entity.class);
    }

    @After
    public void tearDown() throws Exception {
        connectionSource.closeQuietly();
    }

    @Test
    public void testDefaultLength() throws Exception {
        Set<String> ids = DateTimeZone.getAvailableIDs();

        int length = 0;

        for (String id : ids) {
            length = Math.max(length, id.length());
        }

        assertTrue("default database width (" + dateTimeZoneType.getDefaultWidth() + ") is shorter than max id length (" + length + ")", dateTimeZoneType.getDefaultWidth() >= length);
    }

    @Test
    public void testOffsetHours() throws Exception {
        DateTimeZone dateTimeZone = DateTimeZone.forOffsetHours(4);
        String sqlArg = dateTimeZoneType.javaToSqlArg(fieldType, dateTimeZone);
        DateTimeZone retrievedDateTimeZone = dateTimeZoneType.sqlArgToJava(fieldType, sqlArg, 0);
        assertEquals(dateTimeZone, retrievedDateTimeZone);
    }

    @Test
    public void testOffsetHoursMinutes() throws Exception {
        DateTimeZone dateTimeZone = DateTimeZone.forOffsetHoursMinutes(2, 30);
        String sqlArg = dateTimeZoneType.javaToSqlArg(fieldType, dateTimeZone);
        DateTimeZone retrievedDateTimeZone = dateTimeZoneType.sqlArgToJava(fieldType, sqlArg, 0);
        assertEquals(dateTimeZone, retrievedDateTimeZone);
    }

    @Test
    public void testOffsetMillis() throws Exception {
        DateTimeZone dateTimeZone = DateTimeZone.forOffsetMillis(2636457);
        String sqlArg = dateTimeZoneType.javaToSqlArg(fieldType, dateTimeZone);
        DateTimeZone retrievedDateTimeZone = dateTimeZoneType.sqlArgToJava(fieldType, sqlArg, 0);
        assertEquals(dateTimeZone, retrievedDateTimeZone);
    }

    @Test
    public void testID() throws Exception {
        DateTimeZone dateTimeZone = DateTimeZone.forID("Africa/Bamako");
        String sqlArg = dateTimeZoneType.javaToSqlArg(fieldType, dateTimeZone);
        DateTimeZone retrievedDateTimeZone = dateTimeZoneType.sqlArgToJava(fieldType, sqlArg, 0);
        assertEquals(dateTimeZone, retrievedDateTimeZone);
    }

    @Test
    public void testCreateTable() throws Exception {
        TableUtils.createTable(connectionSource, Entity.class);
    }

    @Test
    public void testPersistence() throws Exception {
        Entity entity = new Entity();
        entity.setDateTimeZone(DateTimeZone.forID("Europe/Paris"));

        Dao<Entity, Integer> dao = DaoManager.createDao(connectionSource, Entity.class);
        TableUtils.createTable(connectionSource, Entity.class);

        dao.create(entity);

        Entity retrieved = dao.queryForId(entity.getId());

        assertFalse(entity == retrieved);
        assertEquals("retrieved entity from database is not equals to persisted one", entity, retrieved);
    }
}