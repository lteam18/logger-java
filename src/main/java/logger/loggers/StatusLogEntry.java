package logger.loggers;

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

    public void record(RECORD redord) {
        this.s.logStatus(this.sleid, {});
    }

    static createDiff(Serializer.Type s) {
        return new Diff(s);
    }


    public class Diff {
        public long sleid = StatusLogEntry.SLEID++;

        public HashMap<String, Object> history = new HashMap<>();
        private Serializer.Type s;

        public Diff(Serializer.Type s) {
            this.s = s;
        }

        public void record(HashMap<String, Object> record) {
            this.s.logStatus(this.sleid, {});
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
                final Object d = diff(c, p);
            } else {

            }
        }

        return ret;
    }


}

