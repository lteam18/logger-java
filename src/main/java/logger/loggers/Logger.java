package logger.loggers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import logger.output.Output;
import logger.serialize.Major;
import logger.serialize.Serialize;
import logger.type.Types;

public class Logger {
    public static long LID = 0;
    public long lid = Logger.LID += 10;

    public ArrayList<String> nameList = new ArrayList<>();
    public Serialize.Type s;

    public LevelLogger DEBUG;
    public LevelLogger INFO;
    public LevelLogger WARN;
    public LevelLogger ERROR;
    public LevelLogger FATAL;

    public Logger(ArrayList<String> nameList, Serialize.Type s) {

        this.nameList = nameList;
        this.s = s != null ? s : new Major(Serialize.toJSON());
        this.s.defineLogger(this);

        DEBUG = new LevelLogger(this.lid + 1, Types.LevelType.DEBUG, this.s, this);
        INFO = new LevelLogger(this.lid + 2, Types.LevelType.INFO, this.s, this);
        WARN = new LevelLogger(this.lid + 3, Types.LevelType.WARN, this.s, this);
        ERROR = new LevelLogger(this.lid + 4, Types.LevelType.ERROR, this.s, this);
        FATAL = new LevelLogger(this.lid + 5, Types.LevelType.FATAL, this.s, this);
    }

    public static Logger create(String name, Serialize.Type... s) {
        ArrayList<String> namelist = new ArrayList<>(Arrays.asList(name));

        Serialize.Type ss =
                s.length > 1 ? Serialize.combine(s) : s.length == 1 ? s[0] : Serialize.toChalk();

        return new Logger(namelist, ss);
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

    public String debug(Types.LevelLoggerOption o) {
        return this.DEBUG.o(o);
    }

    public String info(Types.LevelLoggerOption o) {
        return this.INFO.o(o);
    }

    public String warn(Types.LevelLoggerOption o) {
        return this.WARN.o(o);
    }

    public String error(Types.LevelLoggerOption o) {
        return this.ERROR.o(o);
    }

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

    public void finishLogger(){
        Output.closeFile();
    }
}
