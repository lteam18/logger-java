package logger;

import java.util.HashMap;
import logger.loggers.Logger;
import logger.output.Output;
import logger.serialize.Serialize;
import logger.type.Types;

public class Logger_test {
    public static void main(String[] args) {

        Instances.RootLogger =
                Logger.create(
                        "MainLogger-123",
                        Serialize.toChalk(Output.CONSOLE(), Output.file("./a.log")),
                        Serialize.toJSON(Output.file("./a.json.log")));

        final Logger llo = Instances.RootLogger;

        //        final Logger llo =
        //                Logger.builder()
        //                        .setName("MainLogger-123")
        //                        .addOutputType(Serialize.toChalk(Output.CONSOLE(), Output.file("./a.log")))
        //                        .addOutputType(Serialize.toJSON(Output.file("./a.json.log")))
        //                        .build();

        //        final Logger llo2 =
        //                Logger.create(
        //                        "MainLogger-789",
        //                        Serialize.toChalk(Output.CONSOLE(), Output.file("./a.log")),
        //                        Serialize.toJSON(Output.file("./a.json.log")));

        //        llo.DEBUG.msg("Program ready");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("msg", "Program ready");
        Types.LevelLoggerOption levelLoggerOption = new Types.LevelLoggerOption();
        levelLoggerOption.data = hashMap;
        llo.debug(levelLoggerOption);

        try {
            Utils.sleep(1218);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        llo.DEBUG.msg("123");

        llo.DEBUG.msg_data("123", Utils.map("status", "on", "work", Utils.map("a", 1, "b", 2)));

        llo.INFO.msg_data("12321", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));

        final Logger slog = llo.createChildLogger("sublogger");
        slog.WARN.trace(new Error("Here"));
        slog.FATAL.msg_data(
                "Fatal data", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));

        llo.ERROR.msg_data(
                "Error", Utils.map("status", "off", "work", Utils.map("a", "3", "b", 4)));
    }
}
