package logger.serialize;


import java.util.ArrayList;
import java.util.HashMap;

import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.type.Persistant;

public class Combination implements Serializer.Type {

    private ArrayList<Serializer.Type> s;

    public Combination(ArrayList<Serializer.Type> s) {
        this.s = s;
    }

    @Override
    public String getVersionName() {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(e -> ret.append(this.getVersionName()));
        return ret.toString();
    }

    @Override
    public String log(LevelLogger levelLogger, Persistant.LevelLog data) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(e -> ret.append(e.log(levelLogger, data)));
        return ret.toString();
    }

    @Override
    public String defineLogger(Logger logger) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(e -> ret.append(e.defineLogger(logger)));
        return ret.toString();
    }

    @Override
    public String defineLevelLogger(LevelLogger levelLogger) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(e -> ret.append(e.defineLevelLogger(levelLogger)));
        return ret.toString();
    }

    @Override
    public String definePatternLogEntry(PatternLogEntry data) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(e -> ret.append(e.definePatternLogEntry(data)));
        return ret.toString();
    }

    @Override
    public String logInPattern(PatternLogEntry log, Object data) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(e -> ret.append(e.logInPattern(log, data)));
        return ret.toString();
    }

    @Override
    public String logStatus(long id, HashMap<String, Object> data) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(e -> ret.append(e.logStatus(id, data)));
        return ret.toString();
    }
}
