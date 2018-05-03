package logger.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logger.Utils;

/**
 * Created by Lynnsion on 2018/5/2.
 */

public class Utils_test {

    public static void main(String[] args) {
        Utils utils = new Utils();
        utils.convertToUnits(123423213133L);

        System.out.println("result 1 = " + utils.convertToUnits(123423213133L));

        System.out.println("result 2 = " + utils.formatDiffString(123423213133L, 12));

        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> data2 = new HashMap<>();
        data2.put("b", "1");
        data2.put("c", 2);
        data.put("a", data2);
        ArrayList list = new ArrayList();


        System.out.println("result 3= " + data.toString());
        System.out.println("result 4= " + utils.convert(data, list, ""));


    }
}