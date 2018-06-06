package logger.serialize;

import java.util.ArrayList;
import java.util.Map;
import logger.JSON;
import logger.stringify.Stringify;
import logger.type.Persistant;

public class Serializer {
    public interface Type {
        void log(Persistant.LevelLog data);

        void defineHeart(long hid, Map<String, Object> data);

        void beat(long hid);

        void defineStatus(long sid, Map<String, Object> data);

        void rec(long sid, Map<String, Object> status);
    }

    public static class Combination implements Type {

        private ArrayList<Type> s;

        public Combination(ArrayList<Type> s) {
            this.s = s;
        }

        @Override
        public void log(Persistant.LevelLog data) {
            this.s.forEach(type -> type.log(data));
        }

        @Override
        public void defineHeart(long hid, Map<String, Object> data) {
            this.s.forEach(type -> type.defineHeart(hid, data));
        }

        @Override
        public void beat(long hid) {
            this.s.forEach(type -> type.beat(hid));
        }

        @Override
        public void defineStatus(long sid, Map<String, Object> data) {
            this.s.forEach(type -> type.defineStatus(sid, data));
        }

        @Override
        public void rec(long sid, Map<String, Object> status) {
            this.s.forEach(type -> type.rec(sid, status));
        }
    }

    public static Combination combine(Type... types) {
        ArrayList<Type> arrayList = new ArrayList<>();
        for (int i = 0; i < types.length; i++) arrayList.add(types[i]);
        return new Combination(arrayList);
    }

    public static Combination combine(ArrayList<Type> types) {
        return new Combination(types);
    }

    public static Major toJSON(Output.Type... outputs) {
        return new Major(JSON::stringify, outputs);
    }

    public static Major toChalk(Output.Type... outputs) {
        return new Major(Stringify::chalkDataStr, outputs);
    }
}
