package logger.serialize.serializer;

import java.util.Map;

import logger.JSON;
import logger.types.Persistant;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class Major implements Type {

    private String levelLogStringify;


    public Major(String str, String output){


    }

    @Override
    public void log(Persistant.LevelLog data) {

    }

    @Override
    public void defineHeart(long hid, Map<String, Object> data) {

    }

    @Override
    public void beat(long hid) {

    }

    @Override
    public void defineStatus(long sid, Map<String, Object> data) {

    }

    @Override
    public void rec(long sid, Map<String, Object> status) {

    }
}
