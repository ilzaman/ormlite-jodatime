package org.mybop.ormlitejodatime.datatype;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.joda.time.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstantTypeTest {

    private static class Entity {

        @DatabaseField(generatedId = true)
        private Integer id;

        @DatabaseField
        private Instant instant;

        @DatabaseField
        private DateTime dateTime;

        @DatabaseField
        private MutableDateTime mutableDateTime;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Instant getInstant() {
            return instant;
        }

        public void setInstant(Instant instant) {
            this.instant = instant;
        }

        public DateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public MutableDateTime getMutableDateTime() {
            return mutableDateTime;
        }

        public void setMutableDateTime(MutableDateTime mutableDateTime) {
            this.mutableDateTime = mutableDateTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entity)) return false;

            Entity entity = (Entity) o;

            if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
            if (instant != null ? !instant.equals(entity.instant) : entity.instant != null) return false;
            if (dateTime != null ? !dateTime.isEqual(entity.dateTime) : entity.dateTime != null) return false;
            return !(mutableDateTime != null ? !mutableDateTime.isEqual(entity.mutableDateTime) : entity.mutableDateTime != null);

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (instant != null ? instant.hashCode() : 0);
            result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
            result = 31 * result + (mutableDateTime != null ? mutableDateTime.hashCode() : 0);
            return result;
        }
    }

    private JdbcConnectionSource connectionSource;

    private InstantType instantType;

    private FieldType instantFieldType;

    private FieldType datetimeFieldType;

    private FieldType mutableDateTimeFieldType;

    @Before
    public void setUp() throws Exception {
        instantType = new InstantType();
        DataPersisterManager.registerDataPersisters(instantType);
        connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test");
        instantFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("instant"), Entity.class);
        datetimeFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("dateTime"), Entity.class);
        mutableDateTimeFieldType = FieldType.createFieldType(connectionSource, "entity", Entity.class.getDeclaredField("mutableDateTime"), Entity.class);
    }

    @After
    public void tearDown() throws Exception {
        connectionSource.closeQuietly();
    }

    @Test
    public void testInstant() throws Exception {
        Instant instant = Instant.now();
        Object sqlArg = instantType.javaToSqlArg(instantFieldType, instant);
        Object retrieved = instantType.sqlArgToJava(instantFieldType, sqlArg, 0);
        assertEquals(instant, retrieved);
    }

    @Test
    public void testDateTime() throws Exception {
        DateTime dateTime = DateTime.now().withZone(DateTimeZone.UTC);
        Object sqlArg = instantType.javaToSqlArg(datetimeFieldType, dateTime);
        Object retrieved = instantType.sqlArgToJava(datetimeFieldType, sqlArg, 0);
        assertTrue(retrieved instanceof DateTime);
        assertTrue(dateTime.isEqual((ReadableInstant) retrieved));
    }

    @Test
    public void testMutableDateTime() throws Exception {
        MutableDateTime mutableDateTime = MutableDateTime.now();
        mutableDateTime.setZone(DateTimeZone.forID("Asia/Colombo"));
        Object sqlArg = instantType.javaToSqlArg(mutableDateTimeFieldType, mutableDateTime);
        Object retrieved = instantType.sqlArgToJava(mutableDateTimeFieldType, sqlArg, 0);
        assertTrue(retrieved instanceof MutableDateTime);
        assertTrue(mutableDateTime.isEqual((ReadableInstant) retrieved));
    }

    @Test
    public void testCreateTable() throws Exception {
        TableUtils.createTable(connectionSource, Entity.class);
    }

    @Test
    public void testPersistence() throws Exception {
        Entity entity = new Entity();
        entity.setDateTime(DateTime.now());
        entity.setInstant(Instant.now());
        entity.setMutableDateTime(MutableDateTime.now());

        Dao<Entity, Integer> dao = DaoManager.createDao(connectionSource, Entity.class);
        TableUtils.createTable(connectionSource, Entity.class);

        dao.create(entity);
        Entity retrieved = dao.queryForId(entity.getId());

        assertFalse(entity == retrieved);
        assertEquals("retrieved entity from database is not equals to persisted one", entity, retrieved);
    }
}