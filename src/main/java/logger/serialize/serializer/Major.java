package logger.serialize.serializer;

import java.util.Date;
import java.util.Map;

import logger.JSON;
import logger.serialize.Output;
import logger.serialize.Stringify;
import logger.types.Types;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class Major implements Type {

    private Output.Type output_Major;
    private Stringify.Chalk levelLogStringify;

    public Major() {
        levelLogStringify = msg -> JSON.stringify_Object(msg);
        this.output_Major = new Output().CONSOLE();
    }

    public Major(Stringify.Chalk chalk, Output.Type output) {
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
