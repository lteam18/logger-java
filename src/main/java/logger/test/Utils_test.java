package logger.test;

import java.util.ArrayList;
import java.util.HashMap;

import logger.Utils;

/**
 * Created by Lynnsion on 2018/5/2.
 */
public class Utils_test {

    public static void main(String[] args) {

        System.out.println("result 1 = " + Utils.formatDiffString(123423213133L, 12));
        System.out.println("result 2 = " + Utils.formatDiffString(1001));
        System.out.println("result 3 = " + Utils.formatDiffString(60001));

        HashMap<String, Object> data2 = Utils.map("b", "1", "c", 2);
        HashMap<String, Object> data = Utils.map("st", "ss", "a", data2);
        System.out.println("result 4: " + data.toString());

        ArrayList list = new ArrayList();
        Utils.convert(data, list, "");
        System.out.println("result 5: " + list.toString());
    }
}
