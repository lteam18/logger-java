package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String convertToUnits(long millis) {
        long rest = millis;
        final Long ms = rest % 1000;
        final Long second = (rest / 1000) % 60;
        rest = Math.round(rest / 1000); //second
        final long minute = (rest / 60) % 60;
        rest = Math.round(rest / 60 / 60); // minute
        final long hour = rest % 24;
        final long day = Math.round(rest / 24);

        return (day > 0 ? day + "d" : "")
                + (hour > 0 ? hour + "h" : "")
                + (minute > 0 ? minute + "m" : "")
                + (second > 0 ? second + "s" : "")
                + (ms > 0 ? ms : "");
    }

    public static String formatDiffString(long millis) {
        return formatDiffString(millis, 9);
    }

    public static String formatDiffString(long millis, int max_String) {

        String resStr = convertToUnits(millis);

        if (max_String < 0) {
            return "maxLength must be a positive number";
        }

        if (max_String < resStr.length()) {
            for (int i = max_String - 1; i > 0; i--) {
                if (resStr.charAt(i) == 's'
                        || resStr.charAt(i) == 'm'
                        || resStr.charAt(i) == 'h'
                        || resStr.charAt(i) == 'd') {
                    return resStr.substring(0, i + 1);
                }
            }
            return "maxLength is less than result.length()";
        }

        return resStr;
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
        HashMap<String, Object> resMap = new HashMap<>();
        for (int i = 0; i < values.length; i += 2) {
            resMap.put(values[i].toString(), i + 1 < values.length ? values[i + 1] : "");
        }

        return resMap;
    }

    public static String getStacks(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        StringBuffer error = sw.getBuffer();
        return error.toString();
    }
}
