package logger.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import logger.JSON;
import logger.Utils;
import logger.global.Instance;
import logger.index.Logger;
import logger.loggers.HeartbeatLogger;
import logger.serialize.Stringify;
import logger.serialize.Output;
import logger.serialize.serializer.Combination;
import logger.serialize.serializer.Major;
import logger.serialize.serializer.Type;
import logger.types.Types;

/**
 * Created by Lynnsion on 2018/5/2.
 */

public class Logger_test {

    public static void main(String[] args) {

        Logger index = new Logger();

        Instance instance = new Instance();

//        Logger llo = index.createRoot("MainLogger-123", new Combination().combine(
//                new Major(
//                        new Stringify().createChalk(),
//                        new Output().combine(
//                                new Output().CONSOLE(),
//                                new Output().file("./a.log")
//                        )
//                ),
//                new Major(
//                        new Output().CONSOLE(),
//                        new Output().file("./a.json.log")
//                )
//
//                )
//        );


        Major major = new Major(new Stringify().createChalk(),
                new Output().combine(
                        new Output().CONSOLE(),
                        new Output().file("./a.log")
                ));
        Types types = new Types();
        types.persistant.levelLog.M = "this is a major";
        types.persistant.levelLog.T = new Date().getTime();
//        ArrayList<String> ns = new ArrayList<>();
//        ns.add("majro1");
//        ns.add("majro2");
//        types.persistant.levelLog.N = ns;

//        types.persistant.levelLog.L = Types.LevelType.DEBUG;
//        major.log(types.persistant.levelLog);

//        types.persistant.levelLog.L = Types.LevelType.INFO;
//        major.log(types.persistant.levelLog);

//        types.persistant.levelLog.L = Types.LevelType.WARN;
//        major.log(types.persistant.levelLog);

//        types.persistant.levelLog.L = Types.LevelType.ERROR;
//        major.log(types.persistant.levelLog);

        types.persistant.levelLog.L = Types.LevelType.FATAL;
        major.log(types.persistant.levelLog);




    }

}
