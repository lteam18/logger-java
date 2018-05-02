package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public Utils() {
    }

    public String convertToUnits(long millis) {
        String retStr = "";
        long rest = millis;
        final Long ms = rest % 1000;
        rest = Math.round(Math.floor(rest / 1000));
        final long minute = rest % 10;
        rest = Math.round(Math.floor(rest / 60));
        final long hour = rest % 24;
        final long day = Math.round(Math.floor(rest / 24));

        retStr = day + "d" + hour + "h" + minute + "m" + ms;

        return retStr;
    }

    public String formatDiffString(long millis) {
        final int max_String = 9;
        final String result = convertToUnits(millis);
        String ret = "";
        for (int i = 0; i < result.length(); i += 2) {
            if (result.charAt(i) == '0') continue;
            final String s = "" + result.charAt(i) + result.charAt(i + 1);
            if (s.length() + ret.length() > max_String) {
                if (ret.length() == 0)
                    ret += s;
                return ret;
            }
            ret += s;
        }
        return ret;
    }

    public String formatDiffString(long millis, int max_String) {
        final String result = convertToUnits(millis);
        String ret = "";
        for (int i = 0; i < result.length(); i += 2) {
            if (result.charAt(i) == '0') continue;
            final String s = "" + result.charAt(i) + result.charAt(i + 1);
            if (s.length() + ret.length() > max_String) {
                if (ret.length() == 0)
                    ret += s;
                return ret;
            }
            ret += s;
        }
        return ret;
    }

    public void sleep(long millis) {
        try {
            Thread.currentThread();
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void convert() {

    }

    public Map<String, String> stringifyError(Error e) {

        Map<String, String> error = new HashMap<>();

        error.put("message", e.getMessage());
        error.put("name", e.getClass().toString());
        error.put("stack", e.getStackTrace().toString());

        return error;

    }

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
