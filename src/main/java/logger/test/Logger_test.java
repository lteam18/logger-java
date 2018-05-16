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

    public static void main(String[] args) {

        Logger llo = Instance.RootLogger;

        llo = Logger.createRoot(
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



        Instance.RootLogger.debug.msg("root");
        llo.debug.msg("llo1");
        final Logger slog = llo.createSub("sublogger");

        slog.debug.msg("slog");

        llo.debug.msg("llo2");
        Instance.RootLogger.debug.msg("root2");



//        llo.debug.msg("Program ready");
//        Utils.sleep(1218);
//
//        llo.debug.msg("123");
//
//        llo.debug.msg_data("123", Utils.map("static", "on", "work", Utils.map("a", 1, "b", 2)));
//
//        llo.info.msg_data("12321", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));
//
//        final Logger slog = llo.createSub("sublogger");
//        slog.warn.trace(new Error("Here"));
//        slog.fatal.msg_data("Fatal", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));
//
//        llo.error.msg_data("Error", Utils.map("static", "off", "work", Utils.map("a", "3", "b", 4)));


    }
}
