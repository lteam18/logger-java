package logger.serialize.serializer;

import java.util.ArrayList;
import java.util.Map;

import logger.types.Persistant;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class Combination implements Type {

    private ArrayList<Type> s;

    public Combination(ArrayList<Type> s) {
        this.s = s;
    }

    @Override
    public void log(Persistant.LevelLog data) {
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

    public Combination combine(Type... s) {
        ArrayList<Type> arrayList = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            arrayList.add(s[i]);
        }
        return new Combination(arrayList);
    }
}
