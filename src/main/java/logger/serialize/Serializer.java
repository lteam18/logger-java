package logger.serialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import logger.JSON;
import logger.type.Persistant;

public class Serializer {
    public interface Type {
        void log(Persistant.LevelLog data);

        void defineHeart(long hid, Map<String, Object> data);

        void beat(long hid);

        void defineStatus(long sid, Map<String, Object> data);

        void rec(long sid, Map<String, Object> status);

    }

    public static class Major implements Type {
        private Output.Type output_Major;
        private Function<Persistant.LevelLog, String> levelLogStringify;

        public Major() {
            this.levelLogStringify = JSON::stringify;
            this.output_Major = Output.CONSOLE();
        }

        public Major(Function<Persistant.LevelLog, String> stringify, Output.Type... outputs) {
            this.levelLogStringify = stringify;
            this.output_Major = Output.combine(outputs);
        }


        @Override
        public void log(Persistant.LevelLog data) {
            output_Major.output(levelLogStringify.apply(data));
        }

        @Override
        public void defineHeart(long hid, Map<String, Object> data) {
            output_Major.output(hid + " " + JSON.stringify(data));
        }

        @Override
        public void beat(long hid) {
            output_Major.output(hid + " " + new Date().getTime());
        }

        @Override
        public void defineStatus(long sid, Map<String, Object> data) {
            output_Major.output(sid + " " + JSON.stringify(data));
        }

        @Override
        public void rec(long sid, Map<String, Object> status) {
            output_Major.output(sid + " " + new Date().getTime() + " " + JSON.stringify(status));
        }
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

    public static Combination combine(Type... s) {
        ArrayList<Type> arrayList = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            arrayList.add(s[i]);
        }
        return new Combination(arrayList);
    }

}
