package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {

    public static long trunc(double val){
        return Math.round(val - 0.5);
    }

    public static String convertToUnits(long millis) {
        String retStr;
        long rest = millis;
        final Long ms = rest % 1000;
        rest = trunc(rest / 1000);
        final long minute = rest % 60;
        rest = trunc(rest / 60);
        final long hour = rest % 24;
        final long day = trunc(rest / 24);

        retStr = day + "d" + hour + "h" + minute + "m" + ms;
        return retStr;
    }

    public static String formatDiffString(long millis) {
        return formatDiffString(millis, 9);
    }

    // BUG
    public static String formatDiffString(long millis, int max_String) {
        final String result = convertToUnits(millis);
        System.out.println("result =" + result);
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < result.length(); i += 2) {
//            StringBuilder s = new StringBuilder();
            if (result.charAt(i) == '0') continue;
            ret.append(result.charAt(i));
            if(i + 1 < result.length() ) {
                ret.append(result.charAt(i + 1));
            }

            if (s.length() + ret.length() > max_String) {
                if (ret.length() == 0) ret.append(s);
                return ret.toString();
            }
            ret.append(s);
        }
        return ret.toString();
    }

    public static void sleep(long millis) {
        try {
            Thread.currentThread();
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // { a: { b:1, c:2 }}
    // => [a.b]=1 [a.c]=2
    public static void convert(HashMap<String, Object> data, ArrayList<String> ret, String prefix) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String || entry.getValue() instanceof Integer) {
                ret.add(prefix + entry.getKey() + "=" + entry.getValue());
            } else if (entry.getValue() instanceof HashMap) {
                convert(
                        (HashMap<String, Object>) entry.getValue(), ret, prefix + entry.getKey() + ".");
            }
        }
    }

    public static void convert(HashMap<String, Object> data, ArrayList<String> ret) {
        String prefix = "";
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String || entry.getValue() instanceof Integer) {
                ret.add(prefix + entry.getKey() + "=" + entry.getValue());
            } else if (entry.getValue() instanceof HashMap) {
                convert(
                        (HashMap<String, Object>) entry.getValue(), ret, prefix + entry.getKey() + ".");
            }
        }
    }

    public static HashMap<String, Object> stringifyError(Error e) {
        return map(
                "message", e.getMessage(), "name", e.getClass().toString(), "stack", getStacks(e));
    }

    public static HashMap<String, Object> map(Object... values) {
        HashMap<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < values.length; i += 2) {
            if (i + 1 < values.length)
                map.put(values[i].toString(), values[i + 1]);
        }
        return map;
    }

    public static String getStacks(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        StringBuffer error = sw.getBuffer();
        return error.toString();
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

 
}
