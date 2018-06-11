package logger;

import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.loggers.StatusLogEntry;
import logger.type.Types;

public class Logger_test1 {

    public static void main(String[] args) {
        Instances.RootLogger = Logger.createDefault("MainLogger-123");
        final Logger llo = Instances.RootLogger;

        Types.LevelLoggerOption levelLoggerOption = new Types.LevelLoggerOption();
        levelLoggerOption.msg = "Heatbeat";
        final PatternLogEntry heart = llo.INFO.createPatternLogEntry(levelLoggerOption);

        final StatusLogEntry statusLogger = llo.INFO.createStatusLogger();

        Types.LevelLoggerOption levelLoggerOption2 = new Types.LevelLoggerOption();
        levelLoggerOption2.msg = "Program ready";
        llo.debug(levelLoggerOption2);

        heart.beat();

        try {
            Utils.sleep(1218);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        llo.DEBUG.msg("123");
        llo.DEBUG.msg_data("123", Utils.map("status", "on", "work", Utils.map("a", 1, "b", 2)));

        statusLogger.record(Utils.map("a", 1, "b", 2));

        llo.INFO.msg_data("12312", Utils.map("status", "off", "work", Utils.map("a", 3, "b", 4)));

        heart.beat();
        final Logger slog = llo.createChildLogger("sublogger");
        slog.WARN.trace(new Error("Here"));
        slog.FATAL.msg_data("Fatal",Utils.map("status","off","work",Utils.map("a","3","b",4)));

        statusLogger.record(Utils.map("a", 3, "b", 4));

        llo.ERROR.msg_data("Error",Utils.map("status", "off", "work", Utils.map("a", 3, "b", 4)));

        heart.beat();

        statusLogger.record(Utils.map("a", 8, "b", 4));
    }
}
