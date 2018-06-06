package logger.loggers;

import java.util.Date;
import java.util.HashMap;

import logger.serialize.Serializer;

public class StatusLogEntry {

    private HashMap<String, Object> RECORD = new HashMap<>();

    public static long SLEID = 0;
    public long sleid = StatusLogEntry.SLEID++;


    private Serializer.Type s;

    public StatusLogEntry(Serializer.Type s) {
        this.s = s;
    }

    public void record(HashMap<String, Object> record) {
        HashMap<String, Object> msg = new HashMap<>();
        msg.put("T", new Date().getTime());
        msg.put("R", record);
        this.s.logStatus(this.sleid, msg);
    }

    public static Diff createDiff(Serializer.Type s) {
        return new Diff(s);
    }


    public static class Diff {
        public long sleid = StatusLogEntry.SLEID++;

        public HashMap<String, Object> history = new HashMap<>();
        private Serializer.Type s;

        public Diff(Serializer.Type s) {
            this.s = s;
        }

        public void record(HashMap<String, Object> record) {
            HashMap<String, Object> msg = new HashMap<>();
            msg.put("T", new Date().getTime());
            msg.put("R", record);
            this.s.logStatus(this.sleid, msg);
            this.history = record;
        }
    }

    public HashMap<String, Object> diff(HashMap<String, Object> previous, HashMap<String, Object> current) {
        if (previous == current) {
            return null;
        }

        HashMap<String, Object> ret = new HashMap<>();
        for (HashMap.Entry<String, Object> entry : current.entrySet()) {
            final Object p = previous.get(entry);
            final Object c = current.get(entry);
            if (p == c) continue;

            if ((c instanceof Object) && (p instanceof Object)) {
//                final Object d = diff(c, p);
//                d && ret.put(entry,d);
            } else {
//                ret.put(entry,c);
            }
        }

        return ret;
    }


}

