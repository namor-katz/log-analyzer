package com.testtask.processor;

import com.testtask.store.DataStore;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Process log records, group by hour and persist to provided data store.
 */
public class HourStatisticsProcessor implements StatisticsProcessor {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private static final String ERROR_PATTERN = ";ERROR;";
    private static final long INTERVAL = TimeUnit.HOURS.toMillis(1);

    private DataStore dataStore;

    public HourStatisticsProcessor(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void processLogRecord(String line) {
        Long timestamp = getErrorTimestamp(line);

        if (timestamp != null) {
            timestamp -= timestamp % INTERVAL;
            dataStore.compute(timestamp);
        }
    }

    private Long getErrorTimestamp(String line) {
        int position = line.indexOf(ERROR_PATTERN);

        if (position > 0) {
            LocalDateTime errorDateTime = null;
            try {
                errorDateTime = LocalDateTime.parse(line.substring(0, position), DATE_FORMATTER);
                return errorDateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
            } catch (DateTimeException e) {
                // Failed to parse error date. Ignore - just not an error record in log.
            }
        }
        return null;
    }
}
