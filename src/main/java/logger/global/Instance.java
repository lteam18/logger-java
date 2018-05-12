package logger.global;

import logger.index.Logger;
import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.serialize.Stringify;

/**
 * Created by Lynnsion on 2018/5/4.
 */

public class Instance {


    public static Logger RootLogger;

    public static Serializer serializer = new Serializer();

    public Instance() {
        this.RootLogger = new Logger().createRoot(
                "Logger",
                serializer.new Major(
                        new Stringify().createChalk(),
                        new Output().combine(
                                new Output().CONSOLE()
                        )
                )
        );

//        System.out.println("Instance class =" + RootLogger.getClass().toString());
    }
}
