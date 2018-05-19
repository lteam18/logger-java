package logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import logger.type.Persistant;
import logger.type.Types;

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

    public static String stringify(Persistant.LevelLog msg) {
        Persistant.LevelLog data = msg;
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("N", data.N);
        if (data.T != 0) {
            map.put("T", data.T);
        }
        map.put("L", getIntLogLevel(data.L));
        map.put("M", data.M);
        if (data.D != null) {
            map.put("D", data.D);
        }
        if (data.E != null && data.E.size() > 0) {
            map.put("E", data.E);
        }
        return stringify(map) + "\n";
    }

    ;

    private static int getIntLogLevel(Types.LevelType level) {
        switch (level) {
            case DEBUG:
                return 0;
            case INFO:
                return 1;
            case WARN:
                return 2;
            case ERROR:
                return 3;
            case FATAL:
                return 4;
            default:
                break;
        }
        // return mean error
        return 100;
    }

//    main

    public static void main() {
        Types.LevelType.DEBUG.ordinal();
    }

}