package logger.loggers;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import logger.serialize.Serialize;

public class StatusLogEntry {

    public static long SLEID = 0;
    public long sleid = StatusLogEntry.SLEID++;
    public Serialize.Type s;

    public StatusLogEntry(Serialize.Type s) {
        this.s = s;
    }

    public void record(HashMap<String, Object> record) {
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("T", new Date().getTime());
        hashMap.put("R", record);
        this.s.logStatus(this.sleid, hashMap);
    }

    public static Diff createDiff(Serialize.Type s) {
        return new Diff(s);
    }

    public static class Diff {
        private long sleid = StatusLogEntry.SLEID++;
        private HashMap<String, Object> history = new HashMap<>();
        private Serialize.Type s;

        public Diff(Serialize.Type s) {
            this.s = s;
        }

        public void record(HashMap<String, Object> record) {
            HashMap<String, Object> hashMap = new LinkedHashMap<>();
            hashMap.put("T", new Date().getTime());
            hashMap.put("R", diff(this.history, record));
            this.s.logStatus(this.sleid, hashMap);
            this.history = record;
        }
    }

    public static HashMap<String, Object> diff(
            HashMap<String, Object> previous, HashMap<String, Object> current) {
        if (previous == current) {
            return null;
        }

        HashMap<String, Object> ret = new HashMap<>();

        Iterator iterP, iterC;
        HashMap.Entry entryP, entryC;
        iterP = previous.entrySet().iterator();
        iterC = current.entrySet().iterator();
        while (iterC.hasNext()) {
            entryC = (HashMap.Entry) iterC.next();
            if (iterP.hasNext()) {
                entryP = (HashMap.Entry) iterP.next();
                if (entryC.getKey() == entryP.getKey() && entryC.getValue() == entryP.getValue()) {
                    continue;
                }

                if (entryC.getValue() instanceof HashMap && entryP.getValue() instanceof HashMap) {
                    final HashMap<String, Object> d =
                            diff(
                                    (HashMap<String, Object>) entryP.getValue(),
                                    (HashMap<String, Object>) entryC.getValue());
                    ret.put(entryC.getKey().toString(), d);
                    continue;
                }
            }
            ret.put(entryC.getKey().toString(), entryC.getValue());
        }
        return ret;
    }
}
