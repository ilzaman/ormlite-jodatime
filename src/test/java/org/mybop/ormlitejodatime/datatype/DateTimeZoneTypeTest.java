package org.mybop.ormlitejodatime.datatype;

import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertTrue;

public class DateTimeZoneTypeTest {

    private DateTimeZoneType dateTimeZoneType = new DateTimeZoneType();

    @Test
    public void testDefaultLength() throws Exception {
        Set<String> ids = DateTimeZone.getAvailableIDs();

        int length = 0;

        for (String id : ids) {
            length = Math.max(length, id.length());
        }

        assertTrue("default database width (" + dateTimeZoneType.getDefaultWidth() + ") is shorter than max id length (" + length + ")", dateTimeZoneType.getDefaultWidth() >= length);
    }
}