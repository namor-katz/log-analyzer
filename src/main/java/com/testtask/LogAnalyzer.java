package com.testtask;

import com.testtask.formatter.FormatterFactory;
import com.testtask.processor.ProcessorFactory;
import com.testtask.reader.FileLogReader;
import com.testtask.reader.LogReader;
import com.testtask.store.DataStore;
import com.testtask.store.HashMapDataStore;
import com.testtask.util.Interval;
import com.testtask.writer.FileStatisticsWriter;
import com.testtask.writer.StatisticsWriter;


public class LogAnalyzer {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Mandatory input parameters should be provided. Log path, statistics path and time interval");
            return;
        }
        analyze(args[0], args[1], Interval.valueOf(args[2].toUpperCase()));
    }

    /**
     * Analyze log files from logPath and write statistics to statisticsPath
     *
     * @param logPath path to logs e.g. /home/user/logs
     * @param statisticsPath path to created statistics file e.g. /home/user/logs/statistics.txt
     * @param interval statistics interval e.g. hour
     */
    public static void analyze(String logPath, String statisticsPath, Interval interval) {
        DataStore statisticsStore = HashMapDataStore.getInstance();

        // read logs from files to store
        LogReader reader = new FileLogReader(ProcessorFactory.create(interval, statisticsStore));
        reader.read(logPath);

        // write statistics from store to file
        StatisticsWriter writer = new FileStatisticsWriter(FormatterFactory.create(interval), statisticsStore);
        writer.write(statisticsPath);
    }

}

