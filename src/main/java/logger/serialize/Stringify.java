package logger.serialize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import logger.Utils;
import logger.type.Persistant;

public class Stringify {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_BLACK = "\u001B[37m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\033[0;95m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static final int SEP = 9;
    private static final String LEADING_SPACE = "          ";
    private static final String LEADING_CHARS = "_________";

    private static long history = new Date().getTime();

    private static String general_text_fun(Persistant.LevelLog data, String msg) {
        switch (data.L) {
            case DEBUG:
                return ANSI_GREEN + msg + ANSI_RESET;
            case INFO:
                return ANSI_BLUE + msg + ANSI_RESET;
            case WARN:
                return ANSI_YELLOW + msg + ANSI_RESET;
            case ERROR:
                return ANSI_PURPLE + msg + ANSI_RESET;
            case FATAL:
                return ANSI_RED + msg + ANSI_RESET;
            default:
                return "";
        }
    }

    private static String wrapWithColor(String color, String msg) {
        return color + msg + ANSI_RESET;
    }

    public static String stringifyLevelLog(Persistant.LevelLog data) {

        final long diff = data.T - history > 0 ? data.T - history : 0;
        history = data.T;

        String temp = LEADING_CHARS + Utils.formatDiffString(diff);
        final String diff_time_str = temp.substring(temp.length() - SEP);

        String l_difftime = diff_time_str;

        l_difftime = wrapWithColor(ANSI_BLUE, l_difftime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String l_time = sdf.format(data.T);
        l_time = wrapWithColor(ANSI_WHITE, l_time);

        final ArrayList<String> l_nameList = data.N;

        String l_msg = "";
        if (!data.M.equals("")) {
            l_msg = general_text_fun(data, data.M);
        }

        String msg = l_difftime + " " + l_time + " " + l_nameList + " " + l_msg;
        if (data.D != null) {
            ArrayList<String> ret = new ArrayList<>();
            Utils.convert(data.D, ret, "");
            final String[] l_data = {""};
            ret.forEach(e -> l_data[0] += general_text_fun(data, e + " "));
            msg += "\n" + l_data[0];
        }
        if (data.E.size() > 0) {
            msg += "\n" + data.E.get("stack");
            data.E.put(
                    "stack",
                    wrapWithColor(ANSI_BLACK, data.E.get("stack").toString()));
        }

        return msg.replace("\n", "\n" + LEADING_SPACE);
    }
}
