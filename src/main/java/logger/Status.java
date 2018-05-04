package logger;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Lynnsion on 2018/5/4.
 */

public class Status {

    //const given = { a: {b:1, c:2}, d:3 }
    private Map<String, Object> given = new HashMap<>();

    public Status() {
        setGiven();
    }

    public void setGiven() {
        Map<String, Object> item2 = new HashMap<>();
        item2.put("b", 1);
        item2.put("c", 2);

        this.given.put("a", item2);
        this.given.put("d", 3);
    }

//    public ArrayList<Object> rec(Map<String, Object> given, ArrayList<Object> alist) {
//        for (Map.Entry<String, Object> entry : given.entrySet()) {
//            if (entry.getValue() instanceof Integer) {
//                alist.add(entry.getKey() + ":" + entry.getValue());
//            } else {
//                alist.add(entry.getKey() + ":" + entry.getValue());
//            }
//        }
//
//        return alist;
//    }

    public Map<String, Object> rec(Map<String, Object> given,Map<String, Object> alist) {
        for (Map.Entry<String, Object> entry : given.entrySet()) {
            if (entry.getValue() instanceof Map) {
                alist.put(entry.getKey(),entry.getValue());
            } else {
               alist.put(entry.getKey(),entry.getValue());
            }

        }

        return alist;
    }



}
