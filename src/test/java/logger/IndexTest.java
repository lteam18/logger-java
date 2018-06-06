package logger;

import logger.loggers.Logger;

public class IndexTest {

    public static void main(String[] args) {

        Instances.RootLogger = Logger.createDefault("MainLogger-123");
        final Logger llo = Instances.RootLogger;

        llo.debug.msg("sssds");
//                final heart = llo.INFO.createPatternLogEntry({
//
//                });

    }
}
