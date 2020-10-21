package com.testtask;

import com.testtask.processor.ProcessorFactory;
import com.testtask.processor.StatisticsProcessor;
import com.testtask.store.DataStore;
import com.testtask.store.HashMapDataStore;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static com.testtask.util.Interval.HOUR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HourStatisticsProcessorTest {

    DataStore dataStore = HashMapDataStore.getInstance();

    @Before
    public void clearDataSource() {
        dataStore.clear();
    }

    @Test
    public void testHourProcessor() {
        StatisticsProcessor processor = ProcessorFactory.create(HOUR, dataStore);
        processor.processLogRecord("2020-07-08T10:30:00.001;ERROR; Ошибка 1");
        Instant instant = Instant.parse("2020-07-08T10:00:00.00Z");

        assertEquals(Long.valueOf(1), dataStore.get(instant.toEpochMilli()));
    }

    @Test
    public void testHourProcessorFailedToParse() {
        StatisticsProcessor processor = ProcessorFactory.create(HOUR, dataStore);
        processor.processLogRecord("2020-07-08T10:30:00.001;WARNING; Ошибка 1");
        processor.processLogRecord("2020/07/08T10:30:00.001;ERROR; Ошибка 1");

        assertTrue(dataStore.keySet().isEmpty());
    }
}
