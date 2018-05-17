package logger.test;


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
@SuppressWarnings("SpellCheckingInspection")
public class Logger_test {

    public static final String ANSI_YELLOW = "\033[0;93m"; // YELLOW
    public static final String ANSI_PURPLE = "\033[0;95m"; // PURPLE
    public static final String ANSI_GREEN = "\033[0;96m"; // GREEN
    public static final String ANSI_RESET = "\u001B[0m"; //RESET

    public static void main(String[] args) {

        Instance.RootLogger = Logger.createRoot(
                "MainLogger-123",
                Serializer.combine(
                        new Serializer.Major(
                                Stringify.ichalk,
                                Output.CONSOLE(),
                                Output.file("./a.log")
                        )
                        ,
                        new Serializer.Major(
                                JSON.stringify,
                                Output.file("./a.json.log")
                        )
                )
        );
        final Logger llo = Instance.RootLogger;

        llo.debug.msg("Program ready");
        Utils.sleep(1218);

        llo.debug.msg("123");

        llo.debug.msg_data("123", Utils.map("static", "on", "work", Utils.map("a", 1, "b", 2)));

        llo.info.msg_data("12321", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));

        final Logger slog = llo.createSub("sublogger");
        slog.warn.trace(new Error("Here"));
        slog.fatal.msg_data("Fatal", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));

        llo.error.msg_data("Error", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));

    }
}
