package logger.loggers;

import java.util.HashMap;
import logger.serialize.Serializer;

public class HeartbeatLogger {

    private Serializer.Type s;
    public static long HID = 0;
    private long hid;

    public HeartbeatLogger(Serializer.Type s, String msg, HashMap<String, Object> data) {
        if (s == null) {
            this.s =  new Serializer.Major();
        } else {
            this.s = s;
        }
        this.hid = this.HID++;
        def(msg, data);
    }

    public static void def(String msg, HashMap<String, Object> data) {
    }

    public void beat(String msg, HashMap<String, Object> status) {
        this.s.beat(this.hid);
    }

    public long gethid() {
        return this.hid;
    }
}


