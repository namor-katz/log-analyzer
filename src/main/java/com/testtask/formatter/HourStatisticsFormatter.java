package com.testtask.formatter;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


/**
 * Formatter for per our statistics
 */
public class HourStatisticsFormatter implements StatisticsFormatter {
    private static final DateTimeFormatter START_INTERVAL_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm")
            .withZone(ZoneOffset.UTC);
    private static final DateTimeFormatter END_INTERVAL_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
            .withZone(ZoneOffset.UTC);
    private static final String STATISTICS_RECORD_FORMAT = "%s-%s Количество ошибок: %s";
    private static final long INTERVAL = TimeUnit.HOURS.toMillis(1);

    @Override
    public String format(Long timestamp, Long value) {
        Instant startTime = Instant.ofEpochMilli(timestamp);
        Instant endTime = Instant.ofEpochMilli(timestamp + INTERVAL);
        return String.format(STATISTICS_RECORD_FORMAT, START_INTERVAL_FORMATTER.format(startTime),
                END_INTERVAL_FORMATTER.format(endTime), value);

    }
}
