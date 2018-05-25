package logger;

import logger.loggers.Logger;
import logger.serialize.Major;
import logger.serialize.Output;
import logger.serialize.Stringify;

public class Logger_test {
    public static void main(String[] args) {

        final Logger llo =
                Logger.builder()
                        .addName("MainLogger-123")
                        .addMajor(Major.Stringify(Output.CONSOLE(), Output.file("./a.log")))
                        .addMajor(Major.JSON(Output.file("./a.json.log")))
                        .build();

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
                Logger.builder()
                        .addName("Logger2")
                        .addMajor(new Major(Stringify::chalkDataStr, Output.CONSOLE()))
                        .build();

        logger2.debug.msg("logger");

        final Logger logger3 =
                Logger.builder()
                        .addName("Logger3")
                        .addMajor(new Major(JSON::stringify, Output.file("./b.json.log")))
                        .build();

        logger3.fatal.msg("logger3:1213");
        logger2.fatal.msg("1213");
        logger2.info.msg("Logger2 info");
    }
}
