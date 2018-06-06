package logger.serialize;


import java.util.Map;

import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.type.Persistant;

public class Index {

    public interface Type {
        String getVersionName();

        String log(LevelLogger levelLogger, Persistant.LevelLog data);

        String defineLogger(Logger logger);
        String defineLevelLogger(LevelLogger levelLogger);

//        String definePatternLogEntry(PatternLogEntry data);
//        String logInPattern(PatternLogEntry log);
//        String logInPattern(PatternLogEntry log, Map<String, Object> data);

        String logStatus(long id, Map<String, Object> data);
    }

//    public static toChalk(Output.Type ... output){
//        return new Major(new )
//    }
}
