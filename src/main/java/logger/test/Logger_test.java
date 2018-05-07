package logger.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logger.Status;
import logger.serialize.output.Output;
import logger.serialize.output.Type;
import logger.serialize.serializer.Major;
import logger.types.Persistant;

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


//        Type type = new Output().CONSOLE();
//        type.output("console");
//
//        Type ty2 = new Output().file("./a.txt");
//        ty2.output("fileOutput 123123");



        





    }


}
