package logger.loggers;

import java.util.Date;
import java.util.HashMap;
import logger.type.Types;

public class PatternLogEntry {

    public static long PIEID = 0;
    public long pieid = PatternLogEntry.PIEID++;

    private LevelLogger l;
    private Types.LevelLoggerOption o;

    public PatternLogEntry(LevelLogger l, Types.LevelLoggerOption o) {
        this.l = l;
        this.o = o;
    }

    public void beat(HashMap<String, Object> data) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("T", new Date().getTime());
        hashMap.put("data", data);
        this.l.s.logInPattern(this, hashMap);
    }
}