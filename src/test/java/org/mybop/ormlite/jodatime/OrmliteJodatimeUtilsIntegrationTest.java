package org.mybop.ormlite.jodatime;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.joda.time.DateTime;
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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrmliteJodatimeUtilsIntegrationTest {

    private ConnectionSource connectionSource;

    private Dao<Entity, Integer> dao;

    @Before
    public void setUp() throws Exception {
        connectionSource = new JdbcConnectionSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=0");
        OrmliteJodatimeUtils.registerTypes();

        dao = DaoManager.createDao(connectionSource, Entity.class);

        TableUtils.createTable(connectionSource, Entity.class);
    }

    @After
    public void tearDown() {
        connectionSource.closeQuietly();
        DataPersisterManager.clear();
    }

    @Test
    public void duration() throws Exception {
        final Entity entity = new Entity();
        entity.setDuration(Duration.standardHours(42L));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getDuration()).isEqualTo(Duration.standardHours(42L));
    }

    @Test
    public void datetime() throws Exception {
        DateTime now = DateTime.now();
        final Entity entity = new Entity();
        entity.setDateTime(now);

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getDateTime()).isEqualTo(now);
    }

    @Test
    public void instant() throws Exception {
        Instant now = Instant.now();
        final Entity entity = new Entity();
        entity.setInstant(now);

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getInstant()).isEqualTo(now);
    }

    @Test
    public void mutableDateTime() throws Exception {
        MutableDateTime now = MutableDateTime.now();
        final Entity entity = new Entity();
        entity.setMutableDateTime(now);

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getMutableDateTime()).isEqualTo(now);
    }

    @Test
    public void localDateTime() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        final Entity entity = new Entity();
        entity.setLocalDateTime(now);

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getLocalDateTime()).isEqualTo(now);
    }

    @Test
    public void localDate() throws Exception {
        LocalDate today = LocalDate.now();
        final Entity entity = new Entity();
        entity.setLocalDate(today);

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getLocalDate()).isEqualTo(today);
    }

    @Test
    public void localTime() throws Exception {
        LocalTime now = LocalTime.now();
        final Entity entity = new Entity();
        entity.setLocalTime(now);

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getLocalTime()).isEqualTo(now);
    }

    @Test
    public void monthDay() throws Exception {
        MonthDay today = MonthDay.now();

        final Entity entity = new Entity();
        entity.setMonthDay(today);

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getMonthDay()).isEqualTo(today);
    }

    @Test
    public void yearMonth() throws Exception {
        YearMonth today = YearMonth.now();

        final Entity entity = new Entity();
        entity.setYearMonth(today);

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getYearMonth()).isEqualTo(today);
    }

    @Test
    public void days() throws Exception {
        final Entity entity = new Entity();
        entity.setDays(Days.days(28));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getDays()).isEqualTo(Days.days(28));
    }

    @Test
    public void hours() throws Exception {
        final Entity entity = new Entity();
        entity.setHours(Hours.hours(13));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getHours()).isEqualTo(Hours.hours(13));
    }

    @Test
    public void minutes() throws Exception {
        final Entity entity = new Entity();
        entity.setMinutes(Minutes.minutes(27));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getMinutes()).isEqualTo(Minutes.minutes(27));
    }

    @Test
    public void months() throws Exception {
        final Entity entity = new Entity();
        entity.setMonths(Months.months(9));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getMonths()).isEqualTo(Months.months(9));
    }

    @Test
    public void mutablePeriod() throws Exception {
        final Entity entity = new Entity();
        entity.setMutablePeriod(MutablePeriod.parse("P3Y9M442W28DT13H47M32S"));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getMutablePeriod()).isEqualTo(MutablePeriod.parse("P3Y9M442W28DT13H47M32S"));
    }

    @Test
    public void period() throws Exception {
        final Entity entity = new Entity();
        entity.setPeriod(Period.parse("P3Y9M442W28DT13H47M32S"));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getPeriod()).isEqualTo(Period.parse("P3Y9M442W28DT13H47M32S"));
    }

    @Test
    public void seconds() throws Exception {
        final Entity entity = new Entity();
        entity.setSeconds(Seconds.seconds(32));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getSeconds()).isEqualTo(Seconds.seconds(32));
    }

    @Test
    public void weeks() throws Exception {
        final Entity entity = new Entity();
        entity.setWeeks(Weeks.weeks(442));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getWeeks()).isEqualTo(Weeks.weeks(442));
    }

    @Test
    public void years() throws Exception {
        final Entity entity = new Entity();
        entity.setYears(Years.years(3));

        dao.create(entity);

        final Entity found = dao.queryForId(entity.getId());

        assertThat(found).isNotSameAs(entity);
        assertThat(found).isEqualTo(entity);
        assertThat(found.getYears()).isEqualTo(Years.years(3));
    }
}
