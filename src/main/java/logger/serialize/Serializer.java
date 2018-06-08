package logger.serialize;

import java.util.ArrayList;
import java.util.HashMap;
import logger.JSON;
import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.stringify.Stringify;
import logger.type.Persistant;

public class Serializer {
    public interface Type {
        String getVersionName();

        String log(LevelLogger levelLogger, Persistant.LevelLog data);

        String defineLogger(Logger logger);

        String defineLevelLogger(LevelLogger levelLogger);

        String definePatternLogEntry(PatternLogEntry data);

        String logInPattern(PatternLogEntry log, Object data);

        String logStatus(long id, HashMap<String, Object> data);
    }

    public static class Combination implements Type {

        private ArrayList<Type> s;

        public Combination(ArrayList<Type> s) {
            this.s = s;
        }
        //        @Override
        //        public void log(Persistant.LevelLog data) {
        //            this.s.forEach(type -> type.log(data));
        //        }
        //
        //        @Override
        //        public void defineHeart(long hid, Map<String, Object> data) {
        //            this.s.forEach(type -> type.defineHeart(hid, data));
        //        }
        //
        //        @Override
        //        public void beat(long hid) {
        //            this.s.forEach(type -> type.beat(hid));
        //        }
        //
        //        @Override
        //        public void defineStatus(long sid, Map<String, Object> data) {
        //            this.s.forEach(type -> type.defineStatus(sid, data));
        //        }
        //
        //        @Override
        //        public void rec(long sid, Map<String, Object> status) {
        //            this.s.forEach(type -> type.rec(sid, status));
        //        }

        @Override
        public String logStatus(long id, HashMap<String, Object> data) {
            return null;
        }

        @Override
        public String getVersionName() {
            return null;
        }

        @Override
        public String log(LevelLogger levelLogger, Persistant.LevelLog data) {
            StringBuilder ret = null;
            this.s.forEach((e) -> ret.append(e.log(levelLogger, data)));
            return ret.toString();
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
        public String logInPattern(PatternLogEntry ple, Object data) {
            return null;
        }
    }

    public static Combination combine(Type... types) {
        ArrayList<Type> arrayList = new ArrayList<>();
        for (int i = 0; i < types.length; i++) arrayList.add(types[i]);
        return new Combination(arrayList);
    }

    public static Combination combine(ArrayList<Type> types) {
        return new Combination(types);
    }

    public static Major toJSON(Output.Type... outputs) {
        return new Major(JSON::stringify, outputs);
    }

    public static Major toChalk(Output.Type... outputs) {
        return new Major(Stringify::chalkDataStr, outputs);
    }
}
