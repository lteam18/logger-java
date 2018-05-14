package logger.global;

import logger.index.Logger;
import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.serialize.Stringify;

/**
 * Created by Lynnsion on 2018/5/4.
 */
public class Instance {

    public static Logger RootLogger;

    public Instance() {
        this.RootLogger =
                Logger.createRoot(
                        "Logger",
                        new Serializer().new Major(
                                Stringify.createChalk(),
                                Output.combine(Output.CONSOLE())));
    }
}
