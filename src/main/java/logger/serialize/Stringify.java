package logger.serialize;


import java.util.HashMap;
import java.util.Map;

import logger.Utils;

/**
 * Created by Lynnsion on 2018/5/8.
 */

public class Stringify {

    private Utils u = new Utils();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Map<Integer, Object> DEFAULT_CHALK_LEVEL_MAP = new HashMap<>();

    public Stringify() {
        init();
    }

    public void createChalk(

    ) {

    }

    private void init() {
        DEFAULT_CHALK_LEVEL_MAP.put(0, ANSI_GREEN);
        DEFAULT_CHALK_LEVEL_MAP.put(1, ANSI_CYAN);
        DEFAULT_CHALK_LEVEL_MAP.put(2, ANSI_YELLOW);
        DEFAULT_CHALK_LEVEL_MAP.put(3, printStringify);
        DEFAULT_CHALK_LEVEL_MAP.put(4, ANSI_YELLOW);
    }

    private void printPurple(String msg) {
        System.out.println(ANSI_PURPLE + msg + ANSI_RESET);
    }

    interface printStringify {
        void stringify3(String msg);

        void stringify4(String msg);
    }

}

