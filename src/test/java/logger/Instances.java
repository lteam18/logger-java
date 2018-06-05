package logger;


import logger.loggers.Logger;
import logger.serialize.Output;
import logger.serialize.Serializer;

public class Instances {

    public static Logger RootLogger = Logger.create(
            "Logger",
            Serializer.toChalk(
                    Output.CONSOLE()
            )
    );
}

