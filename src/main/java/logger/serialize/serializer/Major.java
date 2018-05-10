package logger.serialize.serializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import logger.JSON;
import logger.serialize.Output;
import logger.types.Persistant;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class Major implements Type {

    private Output.Type output_Major = new Output().CONSOLE();
    private LevelLogStringify levelLogStrify = msg -> JSON.stringify_Object(msg);

    public Major() {

        this.output_Major = new Output().CONSOLE();
    }

    public Major(Output.Type output) {

        this.output_Major = output;
    }

    public Major(Object data, Output.Type output) {
        this.levelLogStrify.levelLogStringify(data);
        this.output_Major = output;
    }

    @Override
    public void log(Persistant.LevelLog data) {
        output_Major.output(this.levelLogStrify.levelLogStringify(data.getLevelLogDataMap()));
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


    public interface LevelLogStringify {
        String levelLogStringify(Object msg);
    }


}
