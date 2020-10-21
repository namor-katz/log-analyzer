package com.testtask.formatter;

import com.testtask.util.Interval;

import static com.testtask.util.Interval.HOUR;

public class FormatterFactory {

    public static StatisticsFormatter create(Interval interval) throws IllegalArgumentException {
        if (HOUR == interval) {
            return new HourStatisticsFormatter();
        }
        throw new IllegalArgumentException(String.format("Unsupported formatter type: %s", interval));
    }

}
