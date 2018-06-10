package logger.stringify;

import java.util.HashMap;

import logger.JSON;
import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.serialize.Serialize;
import logger.type.Persistant;

public class Normal implements Serialize.Type {
    @Override
    public String getVersionName() {
        return "V1";
    }

    @Override
    public String log(LevelLogger levelLogger, Persistant.LevelLog data) {
        return "L" + levelLogger.llid + " " + JSON.stringify(data);
    }

    @Override
    public String defineLogger(Logger logger) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("N", logger.nameList);
        return "DL" + logger.lid + " " + JSON.stringify(hashMap);
    }

    @Override
    public String defineLevelLogger(LevelLogger levelLogger) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("lid", levelLogger.logger.lid);
        return "DLL" + levelLogger.llid + " " + JSON.stringify(hashMap);
    }

    public String defineLogEntry(Persistant.LevelLog data) {
        return null;
    }

    @Override
    public String definePatternLogEntry(PatternLogEntry data) {
        return "DPLE" + data.pieid + JSON.stringify(data);
    }

    @Override
    public String logInPattern(PatternLogEntry log, HashMap<String, Object> data) {

        return !data.isEmpty() ? "P" + log.pieid + " " + JSON.stringify(data)
                : "P" + log.pieid;
    }

    @Override
    public String logStatus(long id, HashMap<String, Object> data) {
        return "S" + id + JSON.stringify(data);
    }
}
