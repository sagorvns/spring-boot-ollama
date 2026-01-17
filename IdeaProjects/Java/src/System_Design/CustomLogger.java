package System_Design;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CustomLogger {

    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    public interface Appender {
        void append(LogLevel level, String message);
    }

    public static class ConsoleAppender implements Appender {
        @Override
        public void append(LogLevel level, String message) {
            System.out.println(formatMessage(level, message));
        }

        private String formatMessage(LogLevel level, String msg) {
            return "[" + level + "] " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + " - " + msg;
        }
    }

    public static class InMemoryAppender implements Appender {
        private final List<String> logs = new CopyOnWriteArrayList<>();

        @Override
        public void append(LogLevel level, String message) {
            logs.add(formatMessage(level, message));
        }

        public List<String> getLogs() {
            return logs;
        }

        private String formatMessage(LogLevel level, String msg) {
            return "[" + level + "] " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + " - " + msg;
        }
    }

    // Singleton Logger Instance
    private static final CustomLogger instance = new CustomLogger();

    private final List<Appender> appenders = new CopyOnWriteArrayList<>();
    private LogLevel currentLevel = LogLevel.DEBUG;

    private CustomLogger() {}

    public static CustomLogger getLogger() {
        return instance;
    }

    public void addAppender(Appender appender) {
        appenders.add(appender);
    }

    public void setLevel(LogLevel level) {
        currentLevel = level;
    }

    public void log(LogLevel level, String message) {
        if (shouldLog(level)) {
            for (Appender appender : appenders) {
                appender.append(level, message);
            }
        }
    }

    public void debug(String msg) { log(LogLevel.DEBUG, msg); }
    public void info(String msg)  { log(LogLevel.INFO, msg); }
    public void warn(String msg)  { log(LogLevel.WARN, msg); }
    public void error(String msg) { log(LogLevel.ERROR, msg); }

    private boolean shouldLog(LogLevel level) {
        return level.ordinal() >= currentLevel.ordinal();
    }

    // Demo
    public static void main(String[] args) {
        CustomLogger logger = CustomLogger.getLogger();
        logger.setLevel(LogLevel.DEBUG); // Set minimum level to DEBUG
        logger.addAppender(new ConsoleAppender());
        InMemoryAppender memoryAppender = new InMemoryAppender();
        logger.addAppender(memoryAppender);

        logger.debug("This is a DEBUG log");
        logger.info("This is an INFO log");
        logger.warn("This is a WARN log");
        logger.error("This is an ERROR log");

        System.out.println("\nStored logs (from InMemoryAppender):");
        memoryAppender.getLogs().forEach(System.out::println);
    }
}

