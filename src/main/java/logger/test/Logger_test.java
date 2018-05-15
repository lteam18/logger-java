package logger.test;

import java.util.HashMap;

import logger.JSON;
import logger.Utils;
import logger.global.Instance;
import logger.index.Logger;
import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.serialize.Stringify;

/**
 * Created by Lynnsion on 2018/5/2.
 */
public class Logger_test {

    public static void main(String[] args) {

         Logger llo = Instance.RootLogger;
         Instance.RootLogger = Logger.createRoot(
                "MainLogger-123",
                Serializer.combine(
                        new Serializer.Major(
                                Stringify.ichalk,
                                Output.CONSOLE(),
                                Output.file("./a.log")
                        ),
                        new Serializer.Major(
                                JSON.stringify,
                                Output.file("./a.json.log")
                        )
                )
        );



        llo.debug.msg("Program ready");

        try {
            Thread.sleep(1218);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        llo.debug.msg("123");

        HashMap<String, Object> data2 = Utils.map("a", "1", "b", "2");
        HashMap<String, Object> data = Utils.map("status", "on", "work", data2);
        data2.put("a", 3);
        data2.put("b", 4);
        data.put("status", "off");
        data.put("work", data2);
        llo.debug.msg_data("123", data);

        final Logger slog = llo.createSub("subLogger");

        slog.warn.trace(new Error("Here"));

        slog.fatal.msg_data("Fatal", data);

        slog.error.msg_data("Error", data);
    }
}
