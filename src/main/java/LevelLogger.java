import java.util.Map;

class LevelLoggerOption {
    Throwable error;
    String msg;
    Map<String, Object> data;
}

public class LevelLogger {
    public LevelLogger() {}

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
