package logger.test;


import java.util.HashMap;
import java.util.Map;

import logger.Status;
import logger.serialize.output.Output;
import logger.serialize.output.Type;
import logger.serialize.serializer.Major;

/**
 * Created by Lynnsion on 2018/5/2.
 */

public class Logger_test {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLACK = "\u001B[37m";



    public static void main(String[] args) {
//        Status status = new Status();
//        Map<String, Object> given = new HashMap<>();
//        Map<String, Object> item2 = new HashMap<>();
//        item2.put("b", 1);
//        item2.put("c", 2);
//
//        given.put("a", item2);
//        given.put("d", 3);
//        Map<String, Object> alist = new HashMap<>();
//        status.rec(given, alist);
//
//        System.out.println("result : " + given);
//        System.out.println("result : " + alist);
//
//
//        Type type = new Output().CONSOLE();
//        type.output("console");
//
//        Type ty2 = new Output().file("./a.txt");
//        ty2.output("fileOutput 123123");
//
//        System.out.println(ANSI_WHITE + "This text is WHITE!" + ANSI_RESET);
//        System.out.println(ANSI_RED + "This text is RED!" + ANSI_RESET);
//        System.out.println(ANSI_GREEN + "This text is GREEN!" + ANSI_RESET);
//        System.out.println(ANSI_YELLOW + "This text is YELLOW !" + ANSI_RESET);
//        System.out.println(ANSI_BLUE + "This text is BLUE!" + ANSI_RESET);
//        System.out.println(ANSI_PURPLE + "This text is PURPLE!" + ANSI_RESET);
//        System.out.println(ANSI_CYAN + "This text is CYAN!" + ANSI_RESET);
//        System.out.println(ANSI_BLACK + "This text is BLACK!" + ANSI_RESET);

        Major major = new Major("ssssss", new Output().CONSOLE());


    }


}
