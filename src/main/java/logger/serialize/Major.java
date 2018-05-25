package logger.serialize;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import logger.JSON;
import logger.type.Persistant;

public class Major implements Serializer.Type {
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

    public static Major toJSON(Output.Type... outputs) {
        return new Major(JSON::stringify, outputs);
    }

    public static Major toChalk(Output.Type... outputs) {
        return new Major(Stringify::chalkDataStr, outputs);
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
