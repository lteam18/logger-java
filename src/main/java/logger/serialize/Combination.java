package logger.serialize;

import java.util.ArrayList;
import java.util.HashMap;
import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.type.Persistant;

public class Combination implements Serialize.Type {

    public ArrayList<Serialize.Type> s = new ArrayList<>();

    public Combination(Serialize.Type... s) {
        if (s.length > 0) {
            for (Serialize.Type type : s) {
                this.s.add(type);
            }
        }
    }

    public Combination(ArrayList<Serialize.Type> s){
        this.s =s;
    }

    @Override
    public String getVersionName() {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(
                e -> {
                    ret.delete(0, ret.length());
                    ret.append(this.getVersionName());
                });

        return ret.toString();
    }

    @Override
    public String log(LevelLogger levelLogger, Persistant.LevelLog data) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(
                e -> {
                    ret.delete(0, ret.length());
                    ret.append(e.log(levelLogger, data));
                });
        return ret.toString();
    }

    @Override
    public String defineLogger(Logger logger) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(
                e -> {
                    ret.delete(0, ret.length());
                    ret.append(e.defineLogger(logger));
                });
        return ret.toString();
    }

    @Override
    public String defineLevelLogger(LevelLogger levelLogger) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(
                e -> {
                    ret.delete(0, ret.length());
                    ret.append(e.defineLevelLogger(levelLogger));
                });
        return ret.toString();
    }

    @Override
    public String definePatternLogEntry(PatternLogEntry data) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(
                e -> {
                    ret.delete(0, ret.length());
                    ret.append(e.definePatternLogEntry(data));
                });
        return ret.toString();
    }

    @Override
    public String logInPattern(PatternLogEntry log, HashMap<String, Object> data) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(
                e -> {
                    ret.delete(0, ret.length());
                    ret.append(e.logInPattern(log, data));
                });
        return ret.toString();
    }

    @Override
    public String logStatus(long id, HashMap<String, Object> data) {
        StringBuilder ret = new StringBuilder();
        this.s.forEach(
                e -> {
                    ret.delete(0, ret.length());
                    ret.append(e.logStatus(id, data));
                });
        return ret.toString();
    }
}
