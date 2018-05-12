package logger.serialize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logger.Utils;
import logger.types.Types;


/**
 * Created by Lynnsion on 2018/5/8.
 */

@SuppressWarnings("SpellCheckingInspection")
public class Stringify {

    public static final String ANSI_RESET = "\u001B[0m";   //RESET
    public static final String ANSI_WHITE = "\u001B[30m";  //WhITE
    public static final String ANSI_RED = "\u001B[31m";    //RED
    public static final String ANSI_BLUE = "\u001B[34m";   //BLUE
    public static final String ANSI_BLACK = "\u001B[37m";  //BLACK
    public static final String ANSI_YELLOW = "\033[0;93m"; // YELLOW
    public static final String ANSI_PURPLE = "\033[0;95m"; // PURPLE
    public static final String ANSI_GREEN = "\033[0;96m";   // GREEN

    private Utils u = new Utils();

    private final int SEP = 9;
    private static final String LEADING_SPACE = "          ";
    private static final String LEADING_CHARS = "_________";

    private long history;

    public Chalk ichalk = new Chalk() {
        @Override
        public String chalk(Types.Persistant.LevelLog data) {
            final long diff = data.T - history;
            history = data.T;

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
                l_msg = general_text_fun(data);
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
    };


    public Stringify() {

    }

    public Chalk createChalk() {
        this.history = new Date().getTime();
        return ichalk;
    }


    private String general_text_fun(Types.Persistant.LevelLog data) {
        System.out.println("stringify data.L = " + data.L);
        switch (data.L) {
            case DEBUG:
                return ANSI_GREEN + data.M + ANSI_RESET;
            case INFO:
                return ANSI_BLUE + data.M + ANSI_RESET;
            case WARN:
                return ANSI_YELLOW + data.M + ANSI_RESET;
            case ERROR:
                return ANSI_PURPLE + data.M + ANSI_RESET;
            case FATAL:
                return ANSI_RED + data.M + ANSI_RESET;
            default:
                return data.M;
        }
    }


    private String wrapWithColor(String color, String msg) {
        return color + msg + ANSI_RESET;
    }


    public interface Chalk {
        String chalk(Types.Persistant.LevelLog data);
    }

}

