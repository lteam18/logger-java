package logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import logger.loggers.HeartbeatLogger;
import logger.loggers.LevelLogger;
import logger.loggers.StatusLogger;
import logger.serialize.Serializer;
import logger.type.Types;

public class Logger {

    private ArrayList<String> namelist = new ArrayList<>();
    private Serializer.Type s;

    public final LevelLogger debug;
    public final LevelLogger info;
    public final LevelLogger warn;
    public final LevelLogger error;
    public final LevelLogger fatal;

    public Logger(ArrayList<String> namelist, Serializer.Type s) {
        this.namelist = namelist;

        this.s = null != s ? s : new Serializer.Major();

        this.debug = new LevelLogger(Types.LevelType.DEBUG, this.s, this.namelist);
        this.info = new LevelLogger(Types.LevelType.INFO, this.s, this.namelist);
        this.warn = new LevelLogger(Types.LevelType.WARN, this.s, this.namelist);
        this.error = new LevelLogger(Types.LevelType.ERROR, this.s, this.namelist);
        this.fatal = new LevelLogger(Types.LevelType.FATAL, this.s, this.namelist);
    }

    public static Logger createRoot(String name, Serializer.Type sType) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(name));
        return null == sType
                ? new Logger(arrayList, new Serializer.Major())
                : new Logger(arrayList, sType);
    }

    public static Logger createRoot(String name, Serializer.Type... sType) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(name));
        return null == sType
                ? new Logger(arrayList, new Serializer.Major())
                : new Logger(arrayList, Serializer.combine(sType));
    }

    public Logger createSub(String name) {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.addAll(this.namelist);
        nameList.add(name);

        return new Logger(nameList, s);
    }

    public HeartbeatLogger defineHeartbeatLogger(String msg, HashMap<String, Object> data) {
        return new HeartbeatLogger(this.s, msg, data);
    }

    public StatusLogger defineStatusLogger(HashMap<String, Object> Schema) {
        return new StatusLogger(this.s, Schema);
    }
}
