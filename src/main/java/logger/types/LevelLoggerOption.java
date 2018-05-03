package logger.types;


import java.util.Map;

/**
 * Created by Lynnsion on 2018/5/3.
 */

public class LevelLoggerOption {

    public Error error;
    public String msg;
    public Map<String, Object> data;

    public final String[] LOG_TYPE = {"debug", "info", "warn", "fatal"};

    public LevelLoggerOption() {
    }

    public LevelLoggerOption(Error error) {
        this.error = error;
    }

    public LevelLoggerOption(String msg) {
        this.msg = msg;
    }

    public LevelLoggerOption(Map<String, Object> data) {
        this.data = data;
    }

    public LevelLoggerOption(Error error, String msg) {
        this.error = error;
        this.msg = msg;
    }

    public LevelLoggerOption(Error error, Map<String, Object> data) {
        this.error = error;
        this.data = data;
    }

    public LevelLoggerOption(String msg, Map<String, Object> data) {
        this.msg = msg;
        this.data = data;
    }

    public LevelLoggerOption(Error error, String msg, Map<String, Object> data) {
        this.error = error;
        this.msg = msg;
        this.data = data;
    }
}
