package logger.serialize.serializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import logger.JSON;
import logger.serialize.output.Output;
import logger.types.Persistant;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class Major implements Type {

    private logger.serialize.output.Type output_Major = new Output().CONSOLE();


    public Major(Object msg, logger.serialize.output.Type output) {
        this.levelLogStringify(msg);
        this.output_Major = output;

        System.out.println("Major.class");
    }

    public String levelLogStringify(Object msg) {
        if (msg instanceof Persistant.LevelLog) {
            return JSON.stringify(((Persistant.LevelLog) msg).getLevelLogDataMap());
        } else
            return JSON.stringify(msg);
    }


    @Override
    public void log(Persistant.LevelLog data) {
        output_Major.output(this.levelLogStringify(data));
    }

    @Override
    public void defineHeart(long hid, Map<String, Object> data) {
        output_Major.output(hid + " " + JSON.stringify(data));
    }

    @Override
    public void beat(long hid) {

        output_Major.output(hid + " " + getDataNow());
    }

    @Override
    public void defineStatus(long sid, Map<String, Object> data) {
        output_Major.output(sid + " " + getDataNow() + " " + JSON.stringify(data));
    }

    @Override
    public void rec(long sid, Map<String, Object> status) {
        output_Major.output(sid + " " + getDataNow() + " " + JSON.stringify(status));
    }

    private String getDataNow() {
        String dateNow = "";
        Date dt = new Date();
        //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
        dateNow = sdf.format(dt);
        return dateNow;
    }


}
