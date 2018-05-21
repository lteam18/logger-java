package logger;


import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Lynnsion on 2018/5/20.
 */

public class MapTest {

    public static class MapInit {
        private HashMap<Object, Object> data = new HashMap<>();

        public MapInit(Object k, Object v) {
            this.data.put(k, v);

        }

        public MapInit o(Object k, Object v) {
            this.data.put(k, v);
            return this;
        }

        public static MapInit h(Object k, Object v) {
            return new MapInit(k, v);
        }
    }

    @Test
    public void main() {

        for (int i = 0; i < 100000; i++) {
            HashMap m = MapInit.h("1", 2)
                    .o("3", 4)
                    .data;

            HashMap m1 = new HashMap() {
                {
                    put(1, 2);
                    put(3, 4);
                }
            };

            HashMap<String, Object> m2 = Utils.map(
                    "1", 2,
                    "3", "4"
            );
        }



//        System.out.println("m =" + m
//                + "\nm1 =" + m1
//                + "\nm2 =" + m2);


    }

}
