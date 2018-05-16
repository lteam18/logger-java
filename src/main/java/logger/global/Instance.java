package logger.global;

import logger.index.Logger;
import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.serialize.Stringify;

/**
 * Created by Lynnsion on 2018/5/4.
 */
@SuppressWarnings("SpellCheckingInspection")
public class Instance {
//    public static Logger RootLogger = Logger.createRoot(
//            "Logger",
//            new Serializer.Major(
//                    Stringify.createChalk(),
//                    Output.CONSOLE()
//            )
//    );

    public static Logger RootLogger ;
}
