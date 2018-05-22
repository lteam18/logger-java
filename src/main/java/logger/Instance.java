package logger;

import logger.serialize.Major;
import logger.serialize.Output;
import logger.serialize.Stringify;

public class Instance {

    public static Logger RootLogger =
            Logger.createRoot("Logger", new Major(Stringify::chalkDataStr, Output.CONSOLE()));
}
