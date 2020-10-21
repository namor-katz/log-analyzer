package com.testtask.writer;

import com.testtask.formatter.StatisticsFormatter;
import com.testtask.store.DataStore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStatisticsWriter implements StatisticsWriter {

    private StatisticsFormatter formatter;
    private DataStore dataStore;

    public FileStatisticsWriter(StatisticsFormatter formatter, DataStore dataStore) {
        this.formatter = formatter;
        this.dataStore = dataStore;
    }

    public void write(String statisticsPath) {
        Path dir = Paths.get(statisticsPath);
        try {
            //TODO: other data structure to avoid explicit sorting
            Files.write(dir, () -> dataStore.keySet()
                    .stream()
                    .sorted()
                    .<CharSequence>map(key -> {
                        return formatter.format(key, dataStore.get(key));
                    })
                    .iterator());
        } catch (IOException e) {
            System.out.println(String.format("Failed to write statistics: %s", e.getMessage()));
        }
    }

}
