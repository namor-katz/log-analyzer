package com.testtask.reader;

import com.testtask.processor.StatisticsProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


/**
 * Read logs from path and process by provided {@link StatisticsProcessor}
 */
public class FileLogReader implements LogReader {

    private static final String LOG_FILE_EXTENSION = ".log";

    private StatisticsProcessor statisticsProcessor;

    public FileLogReader(StatisticsProcessor statisticsProcessor) {
        this.statisticsProcessor = statisticsProcessor;
    }

    @Override
    public void read(String logPath) {
        try (Stream<Path> walk = Files.walk(Paths.get(logPath))) {
            walk.filter(file -> file.toString().endsWith(LOG_FILE_EXTENSION))
                    .parallel()
                    .forEach(this::processLogFile);
        } catch (IOException e) {
            System.out.println(String.format("Failed to read log files: %s", e.getMessage()));
        }
    }

    private void processLogFile(Path file) {
        try (Stream<String> stream = Files.lines(file)) {
            stream.forEach(statisticsProcessor::processLogRecord);
        } catch (IOException e) {
            System.out.println(String.format("Failed to read log file: %s, error: %s", file.getFileName(),
                    e.getMessage()));
        }
    }

}
