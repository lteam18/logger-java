package logger.type;

import java.util.HashMap;

public class Types {

    public static class LevelLoggerOption {
        public Error error;
        public String msg;
        public HashMap<String, Object> data;
    }

    public enum LevelType {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }

}
