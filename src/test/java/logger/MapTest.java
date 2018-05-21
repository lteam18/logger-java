package logger;


import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Lynnsion on 2018/5/20.
 */

public class MapTest {

    private HashMap<Object, Object> data = new HashMap<>();

    public MapTest(Object k, Object v) {
        this.data.put(k, v);

    }

    public MapTest o(Object k, Object v) {
        this.data.put(k, v);
        return this;
    }

    public static MapTest h(Object k, Object v) {
        return new MapTest(k, v);
    }

    public static void main(String[] agrs){
        HashMap m = MapTest.h("1",2)
                .o("3",4)
                .data;

        HashMap m1 = new HashMap(){
            {
                put(1,3);
                put(2,4);
            }
        };

        HashMap<String,Object> m2 = Utils.map(
                "1",3,
                "2","4"
        );

        System.out.println("m ="+m
        +"\nm1 ="+m1
        +"\nm2 ="+m2);


    }

}
