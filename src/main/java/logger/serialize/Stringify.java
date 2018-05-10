package logger.serialize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logger.Utils;
import logger.types.LevelLoggerOption;
import logger.types.Persistant;

/**
 * Created by Lynnsion on 2018/5/8.
 */

public class Stringify {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLACK = "\u001B[37m";

    private Utils u = new Utils();

    private final int SEP = 9;
    private static final String LEADING_SPACE = "          ";
    private static final String LEADING_CHARS = "_________";

    private LevelLoggerOption levelLoggerOption = new LevelLoggerOption();
    private Persistant persistant = new Persistant();
    private Persistant.LevelLog t = persistant.new LevelLog();

    public Stringify() {

    }

    public String createChalk(
    ) {
        long history = new Date().getTime();
        return chalk(history, t);
    }

    private String chalk(long history, Persistant.LevelLog data) {
        final long diff = data.T - history;
        history = data.T;

        general_text_fun(data);
        String temp = LEADING_CHARS + u.formatDiffString(diff);
        final String diff_time_str = temp.substring(temp.length() - SEP);

        String l_difftime = diff_time_str;
        l_difftime = wrapWithColor(ANSI_BLUE, l_difftime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String l_time = sdf.format(data.T);
        l_time = wrapWithColor(ANSI_WHITE, l_time);
        final ArrayList<String> l_nameList = data.N;
        String l_msg = "";
        if (!data.M.equals("")) {
            l_msg = data.M;
        }

        String msg = l_difftime + " " + l_time + " " + l_nameList + " " + l_msg;

        if (data.D.size() > 0) {
            ArrayList<String> ret = new ArrayList<>();
            u.convert(data.D, ret, "");

            final String[] l_data = {""};
            ret.forEach(e -> l_data[0] += e);
            msg += "\n" + l_data[0];
        }
        if (data.E.size() > 0) {
            msg += "\n" + data.E.get("stack");
            data.E.put("stack", wrapWithColor(ANSI_BLACK, data.E.get("stack").toString()));
        }

        return msg.replace("\n", "\n" + LEADING_SPACE);
    }


    private void general_text_fun(Persistant.LevelLog data) {
        switch (data.L) {
            case 0:
                System.out.println(ANSI_GREEN + data.M + ANSI_RESET);
                break;
            case 1:
                System.out.println(ANSI_CYAN + data.M + ANSI_RESET);
                break;
            case 2:
                System.out.println(ANSI_YELLOW + data.M + ANSI_RESET);
                break;
            case 3:
                System.out.println(ANSI_PURPLE + data.M + ANSI_RESET);
                break;
            case 4:
                System.out.println(ANSI_RED + data.M + ANSI_RESET);
                break;
            default:
                break;
        }
    }


    private String wrapWithColor(String color, String msg) {
        return color + msg + ANSI_RESET;
    }


}

