package logger.loggers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import logger.output.Output;
import logger.serialize.Serialize;
import logger.type.Types;

public class Logger {
    public static long LID = 0;
    public long lid = Logger.LID += 10;

    public ArrayList<String> nameList = new ArrayList<>();
    public Serialize.Type s;

    public Logger(ArrayList<String> nameList, Serialize.Type s) {
        this.nameList = nameList;
        this.s = s != null ? s : Serialize.toJSON();
    }

    public static Logger create(String name, Serialize.Type... s) {
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList(name));

        Serialize.Type ss =
                s.length > 1 ? Serialize.combine(s) : s.length == 1 ? s[0] : Serialize.toChalk();

        return new Logger(nameList, ss);
    }

    public static Logger createDefault(String loggerName) {
        final String dateString = generateDateString();

        return create(
                loggerName,
                Serialize.toChalk(
                        Output.CONSOLE(),
                        Output.file("./" + loggerName + "." + dateString + ".chalk.log")),
                Serialize.toJSON(Output.file("./" + loggerName + "." + dateString + ".json.log")));
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
