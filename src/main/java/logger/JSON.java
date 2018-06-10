package logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import logger.loggers.PatternLogEntry;
import logger.type.Persistant;

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

    public static String stringify(Persistant.LevelLog msg) {
        Persistant.LevelLog data = msg;
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        if (data.T != 0) {
            hashMap.put("T", data.T);
        }
        hashMap.put("M", data.M);
        if (data.D != null) {
            hashMap.put("D", data.D);
        }
        if (data.E.size() > 0) {
            hashMap.put("E", data.E);
        }

        return stringify(hashMap);
    }

    public static String stringify(PatternLogEntry data) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pieid", data.pieid);
        return stringify(hashMap);
    }

}
