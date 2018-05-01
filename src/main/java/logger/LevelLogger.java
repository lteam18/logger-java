import java.util.Map;

import Utils;

public class LevelLogger {
    public LevelLogger() {}

    public static class ATTR {
        public static String LEVEL = "_L";
        public static String TIMESTAMP = "_T";
        public static String MSG = "_M";
        public static String STACKTRACE = "_S";
        public static String NAME = "_N";
        public static String STATUS = "_ST";
        public static String THROWABLE = "_TH";
    }

    public static class LEVEL {
        public static String status = "STATUS";
        public static String trace = "TRACE";
        public static String debug = "DEBUG";
        public static String info = "INFO";
        public static String warn = "WARN";
        public static String error = "ERROR";
        public static String fatal = "FATAL";
    }

    public Map<String, Object> addThrowableIntoMap(Throwable th, Map<String, Object> map) {
        map.put(ATTR.THROWABLE, th);
        map.put(ATTR.STACKTRACE, getStacks(th));
        return map;
    }

    public void o(Throwable th) {
        this.o(addThrowableIntoMap(th, createMap()));
    }

    public void o(String msg, Throwable th) {
        this.o(addThrowableIntoMap(th, createMap()), msg);
    }

    public void o(Map<String, Object> map, String msg, Throwable th) {
        this.o(addThrowableIntoMap(th, map), msg);
    }

    public void o(Map<String, Object> map, Throwable th) {
        this.o(addThrowableIntoMap(th, map));
    }

    public void o(Map<String, Object> map, String msg) {
        map.put(ATTR.MSG, msg);
        this.o(map);
    }

    public void o(String msg) {
        this.o(createMap(), msg);
    }

    public void o(Map<String, Object> map) {
        addLevelAndTimestamp(map, this.level);
        writeLogObject(map);
    }
}
