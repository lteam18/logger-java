package logger.index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

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

    public ArrayList<String> namelist = new ArrayList<>();
    public Serializer.Type s;

    // Very detailed infomation
    public LevelLogger debug;
    // Warning
    // Some unoccasional situation, not important
    public LevelLogger info;
    public LevelLogger warn;
    // Unexepected situation, handled or not
    // To do:Issue established, explantion or solution MUST GIVEN
    public LevelLogger error;
    // Error that resulted in exit
    public LevelLogger fatal;

    public String name;

    private static int count = 0;

    public Logger(ArrayList<String> namelist, Serializer.Type s) {
        this.name = namelist + "";
        this.namelist = namelist;
        if (s == null) {
            this.s = new Serializer.Major();
        } else {
            this.s = s;
        }

        this.debug = new LevelLogger(Types.LevelType.DEBUG, this.s, this.namelist);
        this.info = new LevelLogger(Types.LevelType.INFO, this.s, this.namelist);
        this.warn = new LevelLogger(Types.LevelType.WARN, this.s, this.namelist);
        this.error = new LevelLogger(Types.LevelType.ERROR, this.s, this.namelist);
        this.fatal = new LevelLogger(Types.LevelType.FATAL, this.s, this.namelist);

//        System.out.println("count ="+ ++count + " name ="+namelist +"Serializer.Type ="+s);
    }

    public static Logger createRoot(String name, Serializer.Type sType) {
        String[] a = {name};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(a));


        if (sType == null) {
            return new Logger(arrayList, new Serializer.Major());
        } else {
            return new Logger(arrayList, sType);
        }
    }

    public Logger createSub(String name) {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add(this.name);
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
