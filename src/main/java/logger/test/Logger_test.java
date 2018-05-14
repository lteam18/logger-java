package logger.test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import logger.global.Instance;
import logger.index.Logger;
import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.serialize.Stringify;
import logger.types.Types;

/**
 * Created by Lynnsion on 2018/5/2.
 */
public class Logger_test {

    public static void main(String[] args) {

        Logger index = new Logger();

        Instance instance = new Instance();

        final Logger llo =
                instance.RootLogger =
                        index.createRoot(
                                "MainLogger-123",
                                new Serializer().combine(
                                        new Serializer().new Major(
                                                new Stringify().ichalk,
                                                new Output().combine(
                                                        new Output().CONSOLE(),
                                                        new Output().file("./a.log")))));

        Types.LevelLoggerOption lo = new Types().new LevelLoggerOption("Program ready");

        llo.debug.o(lo);
        try {
            Thread.sleep(1218);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        llo.debug.msg("123");

        HashMap<String, Object> data = new LinkedHashMap<>();
        HashMap<String, Object> data2 = new LinkedHashMap<>();
        data2.put("a", "1");
        data2.put("b", "2");
        data.put("status", "on");
        data.put("work", data2);

        llo.debug.msg_data("123", data);

        data.clear();
        data2.clear();

        data2.put("a", 3);
        data2.put("b", 4);
        data.put("status", "off");
        data.put("work", data2);
        llo.info.msg_data("12312", data);

        final Logger slog = llo.createSub("subLogger");

        slog.warn.trace(new Error("Here"));

        slog.fatal.msg_data("Fatal", data);

        slog.error.msg_data("Error", data);
    }
}
