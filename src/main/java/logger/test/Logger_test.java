package logger.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logger.Status;
import logger.serialize.output.FileOutput;

/**
 * Created by Lynnsion on 2018/5/2.
 */

public class Logger_test {


    public static void main(String[] args) {
        Status status = new Status();
        Map<String, Object> given = new HashMap<>();
        Map<String, Object> item2 = new HashMap<>();
        item2.put("b", 1);
        item2.put("c", 2);

        given.put("a", item2);
        given.put("d", 3);
        Map<String, Object> alist = new HashMap<>();
        status.rec(given, alist);

        System.out.println("result : " + given);
        System.out.println("result : " + alist);


        FileOutput fo = new FileOutput("./a.txt");
        fo.output("ssdsds");
        fo.output("ssdsds");
        fo.output("ssdsds");
    }

}
