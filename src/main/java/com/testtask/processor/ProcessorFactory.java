package com.testtask.processor;

import com.testtask.util.Interval;
import com.testtask.store.DataStore;

import static com.testtask.util.Interval.HOUR;

public class ProcessorFactory {

    public static StatisticsProcessor create(Interval interval, DataStore dataStore) throws IllegalArgumentException {
        if (HOUR == interval) {
            return new HourStatisticsProcessor(dataStore);
        }
        throw new IllegalArgumentException(String.format("Unsupported statistics processor type: %s", interval));
    }

}
