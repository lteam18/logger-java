package logger.global;

import logger.index.Logger;
import logger.serialize.Output;
import logger.serialize.Stringify;
import logger.serialize.serializer.Major;

/**
 * Created by Lynnsion on 2018/5/4.
 */

public class Instance {

    private Logger index;

    public static Logger RootLogger;

    public Instance() {
        RootLogger = index.createRoot(
                "Logger",
                new Major(
                        new Stringify().createChalk(),
                        new Output().combine(
                                new Output().CONSOLE()
                        )
                )
        );

        System.out.print(RootLogger.getClass().toString());
    }
}
