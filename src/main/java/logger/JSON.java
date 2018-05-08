package logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

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

    public static String stringify(Object obj) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("Object",obj);
        return gson.toJson(map);
    }


    public static String createMapAndStringify(String... values) {
        return stringify(Utils.map(values));
    }

    public static String createMapJson(Object... values) {
        return stringify(Utils.map(values));
    }
}
