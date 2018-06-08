package logger.loggers;

import java.util.Date;
import java.util.HashMap;
import logger.serialize.Serializer;

public class StatusLogEntry {

    public static long SLEID = 0;
    public long sleid = StatusLogEntry.SLEID++;

    private Serializer.Type s;

    public StatusLogEntry(Serializer.Type s) {
        this.s = s;
    }

    public void record(HashMap<String, Object> record) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("T", new Date().getTime());
        hashMap.put("R", record);
        this.s.logStatus(this.sleid, hashMap);
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
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("T", new Date().getTime());
            hashMap.put("R", diff(this.history, record));
            this.history = record;
        }
    }

    public static HashMap<String, Object> diff(
            HashMap<String, Object> previous, HashMap<String, Object> current) {
        if (previous == current) {
            return null;
        }

        HashMap<String, Object> ret = new HashMap<>();
        for (HashMap.Entry<String, Object> entry : current.entrySet()) {
            final HashMap<String, Object> p = (HashMap<String, Object>) current.get(entry);
            final HashMap<String, Object> c = (HashMap<String, Object>) current.get(entry);
            if (p == c) continue;

            if ((c instanceof HashMap) && (p instanceof HashMap)) {
                final HashMap<String, Object> d = diff(c, p);
                if (d != null) ret.put(entry.getValue().toString(), d);
            } else {
                ret.put(entry.getValue().toString(), c);
            }
        }

        return ret;
    }
}
