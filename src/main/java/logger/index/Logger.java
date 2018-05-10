package logger.index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import logger.loggers.HeartbeatLogger;
import logger.loggers.LevelLogger;
import logger.loggers.StatusLogger;
import logger.serialize.Stringify;
import logger.serialize.serializer.Combination;
import logger.serialize.serializer.Major;
import logger.serialize.serializer.Type;
import logger.types.LevelType;
import sun.rmi.runtime.Log;

/**
 * Created by Lynnsion on 2018/5/4.
 */

public class Logger {

    private ArrayList<String> namelist;
    private Type s = new Major();
    private LevelType t;

    public Logger(){

    }

    public Logger(ArrayList<String> namelist, Type s) {
        this.namelist = namelist;
        this.s = s;
    }

    public static Logger createRoot(String name, Type s) {
        String[] a = {name};
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(a));
        return new Logger(arrayList, s);
    }

    public Logger createSub(String name) {
        this.namelist.add(name);
        return new Logger(this.namelist, this.s);
    }

    // Very detailed infomation
    public LevelLogger debug = new LevelLogger(t.DEBUG, this.s, this.namelist);
    public LevelLogger info = new LevelLogger(t.INFO, this.s, this.namelist);

    // Warning
    // Some unoccasional situation, not important
    public LevelLogger warn = new LevelLogger(t.WARN, this.s, this.namelist);

    // Unexepected situation, handled or not
    // To do:Issue established, explantion or solution MUST GIVEN
    public LevelLogger error = new LevelLogger(t.ERROR, this.s, this.namelist);

    // Error that resulted in exit
    public LevelLogger fatal = new LevelLogger(t.FATAL, this.s, this.namelist);


    public HeartbeatLogger defineHeartbeatLogger(String msg, HashMap<String, Object> data) {
        return new HeartbeatLogger(this.s, msg, data);
    }

    public StatusLogger defineStatusLogger(HashMap<String, Object> Schema) {
        return new StatusLogger(this.s, Schema);
    }

}
