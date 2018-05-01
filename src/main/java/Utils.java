import java.util.HashMap;

public class Utils {

    public static HashMap<String, Object> createMap(Object... values) {
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < values.length; i += 2) {
            map.put(values[i].toString(), values[i + 1]);
        }
        return map;
    }

    public static void main(){
        HashMap<String, Object> m = createMap(
                "a", 1,
                "b", 2,
                "c", 3
                );
    }
}
