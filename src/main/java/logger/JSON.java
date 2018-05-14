package logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import logger.serialize.IChalk;
import logger.serialize.Stringify;
import logger.types.Types;

public class JSON {

    public static Map<String, String> parse(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static String stringify(Map<String, ?> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public static String stringify_Object(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static String createMapAndStringify(String... values) {
        return stringify(Utils.map(values));
    }

    public static String createMapJson(Object... values) {
        return stringify(Utils.map(values));
    }

    public static IChalk stringify = new IChalk() {
        @Override
        public String chalk(Object msg) {
            if (msg instanceof Types.Persistant.LevelLog) {
                Types.Persistant.LevelLog data = (Types.Persistant.LevelLog) msg;
                HashMap<String, Object> map = new LinkedHashMap<>();


                if (data.N != null) {
                    map.put("N", data.N);
                    System.out.println("data.N =" + data.N);
                }

                if (data.T != 0) {
                    map.put("T", data.T);
                    System.out.println("data.T =" + data.T);

                }
                if (data.L != null) {
                    map.put("L", data.L);
                    System.out.println("data.L =" + data.L);
                }
                if (data.M != null) {
                    map.put("M", data.M);
                    System.out.println("data.M =" + data.M);
                }
                if (data.D != null) {
                    map.put("D", data.D);
                    System.out.println("data. =" + data.D);
                }
                if (data.E != null && data.E.size() > 0) {
                    map.put("E", data.E);
                    System.out.println("data.E =" + data.E);
                }
                System.out.println("JSON map=" + map);
                return stringify(map);

            }


            return "null";
        }


    };
}