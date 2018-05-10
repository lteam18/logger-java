package logger.loggers;


import java.util.HashMap;

import logger.serialize.serializer.Major;
import logger.serialize.serializer.Type;

/**
 * Created by Lynnsion on 2018/5/4.
 */

public class HeartbeatLogger {

    private Type s;
    public static long HID = 0;
    private long hid;  //hid  readonly

    public HeartbeatLogger(Type s, String msg, HashMap<String, Object> data) {
        if (s == null) {
            this.s = new Major();
        } else {
            this.s = s;
        }

        this.hid = this.HID++;
        def(msg, data);

    }

    public void def(String msg, HashMap<String, Object> data) {

    }

    public void beat(String msg, HashMap<String, Object> status) {
        this.s.beat(this.hid);
    }

}
