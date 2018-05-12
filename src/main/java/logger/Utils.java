package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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
            final String s;
            if (result.charAt(i) == '0') continue;
            if (i + 1 < result.length())
                s = "" + result.charAt(i) + result.charAt(i + 1);
            else
                s = "" + result.charAt(i);
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
            final String s;
            if (result.charAt(i) == '0') continue;
            if (i + 1 < result.length())
                s = "" + result.charAt(i) + result.charAt(i + 1);
            else
                s = "" + result.charAt(i);
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


    // { a: { b:1, c:2 }}
    // => [a.b]=1 [a.c]=2
    public void convert(HashMap<String, Object> data, ArrayList<String> ret, String prefix) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String || entry.getValue() instanceof Integer) {
                ret.add(prefix + entry.getKey() + "=" + entry.getValue());
            } else if (entry.getValue() instanceof HashMap) {
                convert((HashMap<String, Object>) entry.getValue(), ret, prefix + entry.getKey() + ".");
            }

        }
    }

    public void convert(HashMap<String, Object> data, ArrayList<String> ret) {
        String prefix = "";
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String || entry.getValue() instanceof Integer) {
                ret.add(prefix + entry.getKey() + "=" + entry.getValue());
            } else if (entry.getValue() instanceof HashMap) {
                convert((HashMap<String, Object>) entry.getValue(), ret, prefix + entry.getKey() + ".");
            }

        }
    }

    //测试方法,不用的时候删掉就好了
    public String stringifyErrors(Error e) {
        return "message: " + e.getMessage() + "\n name: " + e.getClass().toString() + "\n stack: " + getStacks(e);
    }

    public HashMap<String, Object> stringifyError(Error e) {
        return map("message", e.getMessage(), "name", e.getClass().toString(), "stack", getStacks(e));
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
//        return pw.toString();
        StringBuffer error = sw.getBuffer();
        return error.toString();
//
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
