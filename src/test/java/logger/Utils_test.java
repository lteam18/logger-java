package logger;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils_test {

    public static void main(String[] args) {
        //10d23h56m123
        System.out.println("10d23h56m123 = " + Utils.formatDiffString(123123123, 20));
        System.out.println("result 1001 = " + Utils.formatDiffString(1001));
        System.out.println("result 60001 = " + Utils.formatDiffString(60001, 12));

        //3d15h0m32s123
        System.out.println("3d15h0m32s123 = " + Utils.formatDiffString(313232123, 4));

        HashMap<String, Object> data2 = Utils.map("b", "1", "c", 2);
        HashMap<String, Object> data = Utils.map("st", "ss", "a", data2);
        System.out.println("map = " + data.toString());

        ArrayList list = new ArrayList();
        Utils.convert(data, list, "");
        System.out.println("convert map =" + list.toString());

        System.out.println("result 2223 =" + Utils.formatDiffString(2223));

        String a = "1234567890";
    }
}
