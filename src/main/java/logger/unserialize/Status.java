package logger.unserialize;

import java.util.Map;

public class Status {
    public long sid;
    public Map<String, String> data;

    public Status(long sid, Map<String, String> data) {
        this.sid = sid;
        this.data = data;
    }
}
