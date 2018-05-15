package logger.index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import logger.loggers.HeartbeatLogger;
import logger.loggers.LevelLogger;
import logger.loggers.StatusLogger;
import logger.serialize.Serializer;
import logger.types.Types;

/**
 * Created by Lynnsion on 2018/5/4.
 */
@SuppressWarnings("SpellCheckingInspection")
public class Logger {

    private static ArrayList<String> namelist;
    private static Serializer.Type s;
    private Types.LevelType t;

    public Logger() {
        this.namelist = new ArrayList<>();
        this.s = new Serializer.Major();
    }

    public Logger(ArrayList<String> namelist, Serializer.Type s) {
        this.namelist = namelist;
        System.out.println(" Logger namelist ="+this.namelist);
        if (s == null) {
            this.s = new Serializer.Major();
        } else {
            this.s = s;
            System.out.println("createRoot ss=" + this.s);

        }
    }

    public static Logger createRoot(String name, Serializer.Type ss) {
        String[] a = {name};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(a));
//        System.out.println("name ="+name);
        System.out.println("namelist ="+arrayList);
        if (ss == null) {
            return new Logger(arrayList, new Serializer.Major());
        } else {
            s = ss;
            System.out.println("createRoot ss=" + ss);
            return new Logger(arrayList, ss);
        }
    }

    public static Logger createSub(String name) {
        namelist.add(name);
        return new Logger(namelist, s);
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
