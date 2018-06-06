package logger;

import logger.loggers.Logger;
import logger.serialize.Output;
import logger.serialize.Serializer;

public class Logger_test {
    public static void main(String[] args) {

        final Logger llo =
                Logger.builder()
                        .setName("MainLogger-123")
                        .addOutputType(Serializer.toChalk(Output.CONSOLE(), Output.file("./a.log")))
                        .addOutputType(Serializer.toJSON(Output.file("./a.json.log")))
                        .build();

        final Logger llo2 =
                Logger.create(
                        "MainLogger-789",
                        Serializer.toChalk(Output.CONSOLE(), Output.file("./a.log")),
                        Serializer.toJSON(Output.file("./a.json.log")));

        llo.debug.msg("Program ready");
        try {
            Utils.sleep(1218);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        llo.debug.msg("123");

        llo.debug.msg_data("123", Utils.map("status", "on", "work", Utils.map("a", 1, "b", 2)));

        llo.info.msg_data("12321", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));

        final Logger slog = llo.createChildLogger("sublogger");
        slog.warn.trace(new Error("Here"));
        slog.fatal.msg_data(
                "Fatal data", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));

        llo.error.msg_data(
                "Error", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));
    }
}
