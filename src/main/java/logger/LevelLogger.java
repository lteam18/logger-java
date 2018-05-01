package logger;

import java.util.Map;

public class LevelLogger {
    public LevelLogger(Logger logger, String level, Writer writer) {
        this.logger = logger;
        this.level = level;
        this.writer = writer;
    }

    final String level;
    final Logger logger;
    final Writer writer;

    public void o(Throwable th) {
        this._o(addThrowableIntoMap(th, Utils.map()));
    }

    public void o(String msg, Throwable th) {
        this.o(addThrowableIntoMap(th, Utils.map()), msg);
    }

    public void o(Map<String, Object> map, String msg, Throwable th) {
        this.o(addThrowableIntoMap(th, map), msg);
    }

    public void o(Map<String, Object> map, Throwable th) {
        this._o(addThrowableIntoMap(th, map));
    }

    public void o(Map<String, Object> map, String msg) {
        map.put(Utils.ATTR.MSG, msg);
        this._o(map);
    }

    public void o(String msg) {
        this.o(Utils.map(), msg);
    }

    protected void addLevelAndTimestamp(Map<String, Object> obj, String level) {
        obj.put(Utils.ATTR.TIMESTAMP, System.currentTimeMillis());
        obj.put(Utils.ATTR.LEVEL, level);
        obj.put(Utils.ATTR.NAME, this.logger.name);
    }

    protected Map<String, Object> addThrowableIntoMap(Throwable th, Map<String, Object> map) {
        map.put(Utils.ATTR.THROWABLE, th);
        map.put(Utils.ATTR.STACKTRACE, Utils.getStacks(th));
        return map;
    }

    protected void _o(Map<String, Object> map) {
        addLevelAndTimestamp(map, this.level);
        this.writer.writeLogObject(map);
    }
}
