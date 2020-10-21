Log analyzer
==========================

> Read logs and provide statistics grouped by time interval

To run the analyzer it's required to provide 3 params on start.
- _log path_ - path to log files (files with .log extension), e.g. _"/home/username/logs"_
- _statistics path_ - path to store processed statistics, e.g. _"/home/username/logs/statistics.txt"_
- _interval_ - time interval to group statistics, e.g. _"hour"_ (only hour time interval is supported now)

### To run:
```bash
// clean, compile, test & package
mvn clean install
```

```bash
// an example of how to run log-analyzer with params
// use actual paths to logs and statistics
java -jar target/log-analyzer.jar /home/username/logs /home/username/logs/statistics.txt hour
```
