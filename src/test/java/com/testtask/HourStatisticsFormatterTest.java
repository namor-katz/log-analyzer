package com.testtask;

import com.testtask.formatter.FormatterFactory;
import com.testtask.formatter.StatisticsFormatter;
import com.testtask.util.Interval;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;


public class HourStatisticsFormatterTest {
    @Test
    public void testHourFormatter() {
        StatisticsFormatter formatter = FormatterFactory.create(Interval.HOUR);
        Instant instant = Instant.parse("2020-07-08T10:00:00.00Z");
        assertEquals("2020-07-08, 10:00-11:00 Количество ошибок: 1", formatter.format(instant.toEpochMilli(), 1L));
    }
}
