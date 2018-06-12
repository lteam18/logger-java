package logger.serialize;

import java.util.HashMap;
import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.output.Output;
import logger.stringify.Chalk;
import logger.stringify.Normal;
import logger.type.Persistant;

public class Serialize {

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
        return new Major(new Normal(), output);
    }

    public static Combination combine(Type... s) {
        return new Combination(s);
    }

}
