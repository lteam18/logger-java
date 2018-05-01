package logger;

import java.util.Map;

public abstract class Writer {
    public abstract void write(String msg);

    public void writeLogObject(Map<String, Object> map) {
        write(JSON.stringify(map));
    }
}
