package logger;

import logger.serialize.Major;
import logger.serialize.Output;
import logger.serialize.Stringify;

public class Logger_test {
    public static void main(String[] args) {

        Instance.RootLogger =
                Logger.createRoot(
                        "MainLogger-123",
                        new Major(Stringify::chalkDataStr, Output.CONSOLE(), Output.file("./a.log")),
                        new Major(JSON::stringify, Output.file("./a.json.log")));

        final Logger llo = Instance.RootLogger;

        llo.debug.msg("Program ready");
        try {
            Utils.sleep(1218);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        llo.debug.msg("123");

        llo.debug.msg_data("123", Utils.map("status", "on", "work", Utils.map("a", 1, "b", 2)));

        llo.info.msg_data("12321", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));

        final Logger slog = llo.createSub("sublogger");
        slog.warn.trace(new Error("Here"));
        slog.fatal.msg_data(
                "Fatal data", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));

        llo.error.msg_data(
                "Error", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));

        final Logger logger2 =
                Logger.createRoot("Logger2", new Major(Stringify::chalkDataStr, Output.CONSOLE()));
        logger2.debug.msg("logger");

        final Logger logger3 =
                Logger.createRoot(
                        "Logger3", new Major(JSON::stringify, Output.file("./b.json.log")));

        logger3.fatal.msg("logger3:1213");
        logger2.fatal.msg("1213");
        logger2.info.msg("Logger2 info");
    }
}
