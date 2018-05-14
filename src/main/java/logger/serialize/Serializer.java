package logger.serialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import logger.JSON;
import logger.types.Types;

/** Created by Lynnsion on 2018/5/12. */
public class Serializer {
    public interface Type {
        void log(Types.Persistant.LevelLog data);

        void defineHeart(long hid, Map<String, Object> data);

        void beat(long hid);

        void defineStatus(long sid, Map<String, Object> data);

        void rec(long sid, Map<String, Object> status);
    }

    public class Major implements Type {
        private Output.Type output_Major;
        private IChalk levelLogStringify;

        public Major() {
            levelLogStringify = msg -> JSON.stringify_Object(msg);
            this.output_Major = new Output().CONSOLE();
        }

        public Major(IChalk chalk, Output.Type output) {
            levelLogStringify = chalk;
            this.output_Major = output;
        }

        @Override
        public void log(Types.Persistant.LevelLog data) {
            output_Major.output(this.levelLogStringify.chalk(data));
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

    public class Combination implements Type {

        private ArrayList<Type> s;

        public Combination() {
            s = new ArrayList<>();
        }

        public Combination(ArrayList<Type> s) {
            this.s = s;
        }

        @Override
        public void log(Types.Persistant.LevelLog data) {
            this.s.forEach((e) -> e.log(data));
        }

        @Override
        public void defineHeart(long hid, Map<String, Object> data) {
            this.s.forEach((e) -> e.defineHeart(hid, data));
        }

        @Override
        public void beat(long hid) {
            this.s.forEach((e) -> e.beat(hid));
        }

        @Override
        public void defineStatus(long sid, Map<String, Object> data) {
            this.s.forEach((e) -> e.defineStatus(sid, data));
        }

        @Override
        public void rec(long sid, Map<String, Object> status) {
            this.s.forEach((e) -> e.rec(sid, status));
        }
    }

    public Combination combine(Type... s) {
        ArrayList<Type> arrayList = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            arrayList.add(s[i]);
        }
        return new Combination(arrayList);
    }

    public Major major;

    public Combination combination;

    public Serializer() {
        this.major = new Major();
        this.combination = new Combination();
    }

    public Serializer(Major major, Combination combination) {
        this.major = major;
        this.combination = combination;
    }
}
