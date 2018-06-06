package logger.loggers;


import java.util.Map;

import logger.type.Persistant;
import logger.type.Types;

public class PatternLogEntry {

    public static long PIEID = 0;
    public long pieid = PatternLogEntry.PIEID++;

    private Persistant.LevelLog l;
    private Types.LevelLoggerOption o;

    public PatternLogEntry(Persistant.LevelLog l, Types.LevelLoggerOption o) {
        this.l = l;
        this.o = o;
    }

    public void beat(Map<String, Object> data){
        this.l.s.logInPattern(this,)
    }
}
