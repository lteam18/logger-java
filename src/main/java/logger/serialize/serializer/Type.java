package logger.serialize.serializer;

import java.util.Map;

import logger.types.Types;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public interface Type {
    void log(Types.Persistant.LevelLog data);
    void defineHeart(long hid, Map<String, Object> data);
    void beat(long hid);
    void defineStatus(long sid, Map<String,Object> data);
    void rec(long sid, Map<String,Object> status);

}
