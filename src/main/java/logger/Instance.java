package logger;

import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.serialize.Stringify;

/**
 * Created by Lynnsion on 2018/5/4.
 */
@SuppressWarnings("SpellCheckingInspection")
public class Instance {

    public static Logger RootLogger = Logger.createRoot(
            "Logger",
            new Serializer.Major(
                    Stringify::chalk,
                    Output.CONSOLE()
            )
    );

}
