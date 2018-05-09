package logger.serialize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

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
    private final String LEADING_SPACE = stringRepeat(" ", SEP + 1);
    private final String LEADING_CHARS = stringRepeat("_", SEP);

    private LevelLoggerOption levelLoggerOption = new LevelLoggerOption();
    private Persistant persistant = new Persistant();
    private Persistant.LevelLog t = persistant.new LevelLog();

    public Stringify() {

    }

    public void createChalk(
    ) {
        long history = new Date().getTime();
        chalk(history, t);
    }

    private void chalk(long history, Persistant.LevelLog data) {
        final long diff = data.T - history;
        history = data.T;

        general_text_fun(data);
        String temp = LEADING_CHARS + u.formatDiffString(diff);
        final String diff_time_str = temp.substring(temp.length() - SEP);

        String l_difftime = diff_time_str;
        l_difftime = printBlue(l_difftime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String l_time = sdf.format(data.T);
        l_time = printGray(l_time);
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
            data.E.put("stack", printBlack(data.E.get("stack").toString()));
        }

        msg.replace("\n", "\n" + LEADING_SPACE);
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

    private String printBlue(String msg) {
        return ANSI_BLUE + msg + ANSI_RESET;
    }

    private String printGray(String msg) {
        // ts 的 gray 用 white
        return ANSI_WHITE + msg + ANSI_RESET;

    }

    private String printBlack(String msg) {
        return ANSI_BLACK + msg + ANSI_RESET;
    }


    public String stringRepeat(String a, int b) {
        String result = "";
        if (a.equals("")) {
            return null;
        } else {
            for (int i = 0; i < b; i++) {
                result += a;
            }
            return result;
        }
    }


}

