package org.mybop.ormlite.jodatime.datatype.instant;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import org.joda.time.DateTimeUtils;
import org.joda.time.Instant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstantStringTypeIntegrationTest {

    @DatabaseTable
    static final class Entity {

        @DatabaseField(generatedId = true)
        private Integer id;

        @DatabaseField(version = true)
        private Instant version;

        public Integer getId() {
            return id;
        }

        public Instant getVersion() {
            return version;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Entity entity = (Entity) o;

            if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
            return version != null ? version.equals(entity.version) : entity.version == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (version != null ? version.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "id=" + id +
                    ", version=" + version +
                    '}';
        }
    }

    private ConnectionSource connectionSource;

    private Dao<Entity, Instant> dao;

    @Before
    public void setUp() throws Exception {
        connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=0");
        DataPersisterManager.registerDataPersisters(InstantStringType.getSingleton());

        dao = DaoManager.createDao(connectionSource, Entity.class);

        TableUtils.createTable(connectionSource, Entity.class);
    }

    @After
    public void tearDown() {
        connectionSource.closeQuietly();
        DateTimeUtils.setCurrentMillisSystem();
        DataPersisterManager.clear();
    }

    @Test
    public void generation() throws Exception {
        final Entity entity = new Entity();

        DateTimeUtils.setCurrentMillisFixed(System.currentTimeMillis());

        dao.create(entity);

        assertThat(entity.getVersion()).isInstanceOf(Instant.class);
        assertThat(entity.getVersion()).isEqualByComparingTo(Instant.now());
    }

    @Test
    public void update() throws Exception {
        final Entity entity = new Entity();

        dao.create(entity);

        final Instant creation = entity.getVersion();

        DateTimeUtils.setCurrentMillisFixed(System.currentTimeMillis() + 500);

        dao.update(entity);

        assertThat(entity.getVersion()).isGreaterThan(creation);
        assertThat(entity.getVersion()).isEqualByComparingTo(Instant.now());
    }
}
