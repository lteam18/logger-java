package logger.stringify;

import java.util.HashMap;
import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.serialize.Serialize;
import logger.type.Persistant;

public class Nomal implements Serialize.Type {
    @Override
    public String getVersionName() {
        return null;
    }

    @Override
    public String log(LevelLogger levelLogger, Persistant.LevelLog data) {
        return null;
    }

    @Override
    public String defineLogger(Logger logger) {
        return null;
    }

    @Override
    public String defineLevelLogger(LevelLogger levelLogger) {
        return null;
    }

    @Override
    public String definePatternLogEntry(PatternLogEntry data) {
        return null;
    }

    @Override
    public String logInPattern(PatternLogEntry log, HashMap<String, Object> data) {
        return null;
    }

    @Override
    public String logStatus(long id, HashMap<String, Object> data) {
        return null;
    }
}
