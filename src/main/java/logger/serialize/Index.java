package logger.serialize;

import java.util.ArrayList;
import java.util.HashMap;

import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.type.Persistant;

public class Index {

    public interface Type {
        String getVersionName();

        String log(LevelLogger levelLogger, Persistant.LevelLog data);

        String defineLogger(Logger logger);
        String defineLevelLogger(LevelLogger levelLogger);

        String definePatternLogEntry(PatternLogEntry data);
        String logInPattern(PatternLogEntry log, HashMap<String, Object> data);

        String logStatus(long id, HashMap<String, Object> data);
    }

    public static Major toChalk(Output.Type... output) {
        return new Major(new Chalk(), output);
    }

    public static Major toJSON(Output.Type... output) {
        return  new Major(new Nomal(), output);
    }

    public static Combination combine(ArrayList<Type> s) {
    return  new Combination(s);
    }
}
