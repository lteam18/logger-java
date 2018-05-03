package logger.unserialize;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Lynnsion on 2018/5/3.
 */

public class Heartbeat {
    public long hid;
    public Map<String, String> data;

    public Heartbeat(long hid, Map<String, String> data) {
        this.hid = hid;
        this.data = data;
    }



}
