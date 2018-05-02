package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

public class Utils {

    public Utils() {

    }

    public String convertToUnits(Long millis) {
        String retStr = "";
        Long rest = millis;
        final Long ms = rest % 1000;
        rest = Math.round(Math.floor(rest / 1000));
        final Long minute = rest % 10;
        rest = Math.round(Math.floor(rest / 60));
        final Long hour = rest % 24;
        final Long day = Math.round(Math.floor(rest / 24));

        retStr = day + "d" + hour + "h" + minute + "m" + ms;

        return retStr;
    }

    public String formatDiffString(Long millis) {
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

    public String formatDiffString(Long millis, int max_String) {
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
