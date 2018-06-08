package logger.loggers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import logger.serialize.Major;
import logger.serialize.Output;
import logger.serialize.Serializer;
import logger.type.Types;

public class Logger {
    private static long LID = 0;
    private long lid = Logger.LID += 10;

    private ArrayList<String> nameList = new ArrayList<>();
    private Serializer.Type s;

    public Logger(ArrayList<String> nameList, Serializer.Type s) {
        this.nameList = nameList;
        this.s = s != null ? s : Serializer.toJSON();
    }

    public static Logger create(String name, Serializer.Type... sType) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(name));
        return null == sType
                ? new Logger(arrayList, new Major())
                : new Logger(arrayList, Serializer.combine(sType));
    }

    public static Logger createDefault(String loggerName) {
        final String dateString = generateDateString();

        return create(
                loggerName,
                Serializer.toChalk(
                        Output.CONSOLE(),
                        Output.file("./" + loggerName + "." + dateString + ".chalk.log")),
                Serializer.toJSON(Output.file("./" + loggerName + "." + dateString + ".json.log")));
    }

    public Logger createChildLogger(String name) {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.addAll(this.nameList);
        nameList.add(name);

        return new Logger(nameList, this.s);
    }

    public LevelLogger DEBUG = new LevelLogger(this.lid + 1, Types.LevelType.DEBUG, this.s, this);

    public String debug(Types.LevelLoggerOption o) {
        return this.DEBUG.o(o);
    }

    public LevelLogger INFO = new LevelLogger(this.lid + 2, Types.LevelType.INFO, this.s, this);

    public String info(Types.LevelLoggerOption o) {
        return this.INFO.o(o);
    }

    public LevelLogger WARN = new LevelLogger(this.lid + 3, Types.LevelType.WARN, this.s, this);

    public String warn(Types.LevelLoggerOption o) {
        return this.WARN.o(o);
    }

    public LevelLogger ERROR = new LevelLogger(this.lid + 4, Types.LevelType.ERROR, this.s, this);

    public String error(Types.LevelLoggerOption o) {
        return this.ERROR.o(o);
    }

    public LevelLogger FATAL = new LevelLogger(this.lid + 5, Types.LevelType.FATAL, this.s, this);

    public String fatal(Types.LevelLoggerOption o) {
        return this.FATAL.o(o);
    }

    public static String generateDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateNow = sdf.format(new Date());
        return dateNow;
    }

    public static LoggerBuilder builder() {
        return new LoggerBuilder();
    }
}
