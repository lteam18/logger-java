package logger.loggers;

import java.util.HashMap;

import logger.serialize.Serializer;


/**
 * Created by Lynnsion on 2018/5/10.
 */

public class StatusLogger {

    private Serializer.Type s;
    public static long SID = 0;
    private long sid;

    public StatusLogger(Serializer.Type s, HashMap<String, Object> data) {
        if (s == null) {
            this.s = new Serializer().major;
        } else {
            this.s = s;
        }

        this.sid = this.SID++;
        this.s.defineStatus(this.sid, data);
    }

    public void rec(HashMap<String, Object> status) {
        this.s.rec(this.sid, status);
    }
}
