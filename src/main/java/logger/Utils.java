package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {

    public static String convertToUnits(long millis) {
        long rest = millis;
        final Long ms = rest % 1000;
        final Long s = (rest / 1000) % 60;
        rest = Math.round(rest / 1000); //second
        final long minute = (rest / 60) % 60;
        rest = Math.round(rest / 60 / 60); // minute
        final long hour = rest % 24;
        final long day = Math.round(rest / 24);

        return day + "d" + hour + "h" + minute + "m" + s + "s" + ms;
    }

    public static String formatDiffString(long millis) {
        return formatDiffString(millis, 9);
    }

    public static String formatDiffString(long millis, int max_String) {
        final String result = convertToUnits(millis);
        StringBuilder ret = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        getret(result, 0, result.indexOf("d") > 0 ? result.indexOf("d") : 1, temp, ret, "d");
        getret(result, "d", "h", temp, ret, "h");
        getret(result, "h", "m", temp, ret, "m");
        getret(result, "m", "s", temp, ret, "s");
        getret(result, "s", result.length(), temp, ret, "");

        return ret.length() < max_String ? ret.toString() : ret.substring(0, max_String);
    }

    private static void getret(
            String cStr,
            Object start,
            Object end,
            StringBuilder temp,
            StringBuilder ret,
            String flag) {
        if (start instanceof Integer && end instanceof Integer) {
            temp.append(cStr.subSequence((Integer) start, (Integer) end));
        } else if (end instanceof Integer) {
            temp.append(cStr.subSequence(cStr.indexOf((String) start) + 1, (Integer) end));
        } else {
            temp.append(
                    cStr.subSequence(cStr.indexOf((String) start) + 1, cStr.indexOf((String) end)));
        }
        if (!temp.toString().equals("0")) ret.append(temp + flag);
        temp.delete(0, temp.length());
    }

    public static void sleep(long millis) throws InterruptedException {
        Thread.currentThread();
        Thread.sleep(millis);
    }

    // { a: { b:1, c:2 }}  => [a.b]=1 [a.c]=2
    public static void convert(HashMap<String, Object> data, ArrayList<String> ret, String prefix) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String || entry.getValue() instanceof Integer) {
                ret.add(prefix + entry.getKey() + "=" + entry.getValue());
            } else if (entry.getValue() instanceof HashMap) {
                convert(
                        (HashMap<String, Object>) entry.getValue(),
                        ret,
                        prefix + entry.getKey() + ".");
            }
        }
    }

    public static void convert(HashMap<String, Object> data, ArrayList<String> ret) {
        convert(data, ret, "");
    }

    public static HashMap<String, Object> stringifyError(Error e) {
        return map(
                "message", e.getMessage(), "name", e.getClass().toString(), "stack", getStacks(e));
    }

    public static HashMap<String, Object> map(Object... values) {
        if (values.length < 2) {
            return LoggerMap.h(values[0].toString(), "null").data;
        }

        LoggerMap map = LoggerMap.h(values[0].toString(), values[1]);
        for (int i = 2; i < values.length; i += 2) {
            if (i + 1 < values.length) map.o(values[i].toString(), values[i + 1]);
        }

        return map.data;
    }

    public static String getStacks(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        StringBuffer error = sw.getBuffer();
        return error.toString();
    }

    private static class LoggerMap {
        private HashMap<String, Object> data = new LinkedHashMap<>();

        public LoggerMap(String key, Object value) {
            this.data.put(key, value);
        }

        public LoggerMap o(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public static LoggerMap h(String key, Object value) {
            return new LoggerMap(key, value);
        }
    }
}
