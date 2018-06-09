package logger;

import logger.loggers.Logger;

public class Instances {

    public static Logger RootLogger = Logger.create("Logger", Serializer.toChalk(Output.CONSOLE()));
}
