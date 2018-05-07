package logger;

public class Logger {

    public String name;
    public Writer writer;

    static enum Level {
        debug,
        info,
        warn,
        error,
        fatal
    }

    public LevelLogger debug = new LevelLogger(this, Level.debug, this.writer);
    public LevelLogger info = new LevelLogger(this, Level.info, this.writer);
    public LevelLogger warn = new LevelLogger(this, Level.warn, this.writer);
    public LevelLogger error = new LevelLogger(this, Level.error, this.writer);
    public LevelLogger fatal = new LevelLogger(this, Level.fatal, this.writer);
}
