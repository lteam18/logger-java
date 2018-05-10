package logger.types;


import java.util.HashMap;

/**
 * Created by Lynnsion on 2018/5/3.
 */

public class LevelLoggerOption {

    public Error error;
    public String msg;
    public HashMap<String, Object> data;

//    public final String[] LOG_TYPE ={"debug", "info", "warn", "error", "fatal"};

    public LevelLoggerOption() {
    }

    public LevelLoggerOption(String msg) {
        this.msg = msg;
    }

        public LevelLoggerOption(Error error) {
        this.error = error;
    }

    public LevelLoggerOption(String msg, Error error) {
        this.error = error;
        this.msg = msg;
    }


    public LevelLoggerOption(String msg, HashMap<String, Object> data) {
        this.msg = msg;
        this.data = data;
    }

    public LevelLoggerOption(String msg, HashMap<String, Object> data, Error error) {
        this.msg = msg;
        this.data = data;
        this.error = error;
    }

}
