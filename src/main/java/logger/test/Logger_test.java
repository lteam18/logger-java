package logger.test;


import logger.global.Instance;
import logger.index.Logger;
import logger.serialize.Serializer;
import logger.serialize.Stringify;
import logger.serialize.Output;

/**
 * Created by Lynnsion on 2018/5/2.
 */

public class Logger_test {


    public static void main(String[] args) {

        Logger index = new Logger();

        Instance instance = new Instance();

        Logger llo = instance.RootLogger = index.createRoot(
                "MainLogger-123",
                new Serializer().combine(
                        new Serializer().new Major(
                                new Stringify().ichalk,
                                new Output().combine(
                                        new Output().CONSOLE(),
                                        new Output().file("./a.log")
                                )
                        ),
                        new Serializer().new Major(
                                new Stringify().ichalk,
                                new Output().combine(
                                        new Output().CONSOLE(),
                                        new Output().file("./a.log")
                                )
                        )
                )
        );


        llo.debug.msg("123");
        llo.info.msg("123");
        llo.warn.msg("123");
        llo.error.msg("123");
        llo.fatal.msg("123");

    }


}
