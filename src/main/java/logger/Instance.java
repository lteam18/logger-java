package logger;

import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.serialize.Stringify;

public class Instance {

    public static Logger RootLogger =
            Logger.createRoot(
                    "Logger", new Serializer.Major(Stringify::chalkDataStr, Output.CONSOLE()));
}
