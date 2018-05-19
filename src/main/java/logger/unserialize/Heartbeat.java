package logger.unserialize;

import java.util.Map;

public class Heartbeat {
    public long hid;
    public Map<String, String> data;

    public Heartbeat(long hid, Map<String, String> data) {
        this.hid = hid;
        this.data = data;
    }
}
