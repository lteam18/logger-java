package logger.test;


import java.util.HashMap;

import logger.JSON;
import logger.Utils;
import logger.global.Instance;
import logger.index.Logger;
import logger.loggers.HeartbeatLogger;
import logger.serialize.Stringify;
import logger.serialize.Output;
import logger.serialize.serializer.Combination;
import logger.serialize.serializer.Major;
import logger.serialize.serializer.Type;

/**
 * Created by Lynnsion on 2018/5/2.
 */

public class Logger_test {

    public static void main(String[] args) {

        Logger index = new Logger();

        Logger llo = index.createRoot("MainLogger-123", new Combination().combine(
                new Major(
                        new Stringify().createChalk(),
                        new Output().combine(
                                new Output().CONSOLE(),
                                new Output().file("./a.log")
                        )
                ),
                new Major(
                        new Output().CONSOLE(),
                        new Output().file("./a.json.log")
                )

                )
        );

        llo.error.msg("123");


    }


}
