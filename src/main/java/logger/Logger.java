package logger;

public class Logger {

    public String name;
    public Writer writer;

    public static class LEVEL {
        public static String debug = "DEBUG";
        public static String info = "INFO";
        public static String warn = "WARN";
        public static String error = "ERROR";
        public static String fatal = "FATAL";
    }

    public LevelLogger debug = new LevelLogger(this, LEVEL.debug, this.writer);
    public LevelLogger info = new LevelLogger(this, LEVEL.info, this.writer);
    public LevelLogger warn = new LevelLogger(this, LEVEL.warn, this.writer);
    public LevelLogger error = new LevelLogger(this, LEVEL.error, this.writer);
    public LevelLogger fatal = new LevelLogger(this, LEVEL.fatal, this.writer);
}
