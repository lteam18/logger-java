package logger.test;


import java.util.HashMap;
import java.util.Map;

import logger.Status;
import logger.serialize.output.Output;

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


        Output output = new Output("test file 1");
//
//        output.file("./a.txt");
//
//        Output output2 = new Output();
//        output2.setMsg("test file2");
//
//        output2.file("./a.txt");


        output.CONSOLE();

        output.file("./a.txt");



    }

}
