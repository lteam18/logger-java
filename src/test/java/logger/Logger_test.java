package logger;


import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.serialize.Stringify;

public class Logger_test {
    public static void main(String[] args) {

        Instance.RootLogger = Logger.createRoot(
                "MainLogger-123",
                Serializer.combine(
                        new Serializer.Major(
                                Stringify::stringifyLevelLog,
                                Output.CONSOLE(),
                                Output.file("./a.log")
                        ),
                        new Serializer.Major(
                                JSON::stringify,
                                Output.file("./a.json.log")
                        )
                )
        );

        final Logger llo = Instance.RootLogger;

        llo.debug.msg("Program ready");
        try {
            Utils.sleep(1218);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        llo.debug.msg("123");

        llo.debug.msg_data("123", Utils.map("static", "on", "work", Utils.map("a", 1, "b", 2)));

        llo.info.msg_data("12321", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));

        final Logger slog = llo.createSub("sublogger");
        slog.warn.trace(new Error("Here"));
        slog.fatal.msg_data("Fatal", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));

        llo.error.msg_data("Error", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));

    }
}
