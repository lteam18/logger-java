package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

public class Utils {

    public static HashMap<String, Object> map(Object... values) {
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < values.length; i += 2) {
            map.put(values[i].toString(), values[i + 1]);
        }
        return map;
    }

    public static String getStacks(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return pw.toString();
    }

    public static class ATTR {
        public static String LEVEL = "L";
        public static String TIMESTAMP = "T";
        public static String MSG = "M";
        public static String STACKTRACE = "S";
        public static String NAME = "N";
        public static String DATA = "D";
        public static String THROWABLE = "TH";
    }

    public static class LEVEL {
        public static String data = "DATA";
        public static String trace = "TRACE";
        public static String debug = "DEBUG";
        public static String info = "INFO";
        public static String warn = "WARN";
        public static String error = "ERROR";
        public static String fatal = "FATAL";
    }

    public static void main() {
        HashMap<String, Object> m =
                map(
                        "a", 1,
                        "b", 2,
                        "c", 3);
    }
}
