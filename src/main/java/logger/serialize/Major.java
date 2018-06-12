package logger.serialize;

import java.util.HashMap;
import logger.loggers.LevelLogger;
import logger.loggers.Logger;
import logger.loggers.PatternLogEntry;
import logger.output.Output;
import logger.type.Persistant;

public class Major implements Serialize.Type {
    private Output.Type outputType;
    private Serialize.Type stringifier;

    public Major(Serialize.Type stringifier, Output.Type... output) {
        this.stringifier = stringifier;
        this.outputType =
                output.length == 0
                        ? Output.CONSOLE()
                        : output.length == 1 ? output[0] : Output.combine(output);
    }

    @Override
    public String getVersionName() {
        String r = this.stringifier.getVersionName();
        this.outputType.output(r);
        return r;
    }

    @Override
    public String log(LevelLogger levelLogger, Persistant.LevelLog data) {
        String r = this.stringifier.log(levelLogger, data);
        if (r != null) this.outputType.output(r);
        return r != null ? r : null;
    }

    @Override
    public String defineLogger(Logger logger) {
        String r = this.stringifier.defineLogger(logger);
        if (r != null) {
            this.outputType.output(r);
            return r;
        } else {
            return null;
        }
    }

    @Override
    public String defineLevelLogger(LevelLogger levelLogger) {
        String r = this.stringifier.defineLevelLogger(levelLogger);
        if (r != null) this.outputType.output(r);
        return r == null ? null : r;
    }

    @Override
    public String definePatternLogEntry(PatternLogEntry data) {
        String r = this.stringifier.definePatternLogEntry(data);
        if (!r.isEmpty()) this.outputType.output(r);
        return r;
    }

    @Override
    public String logInPattern(PatternLogEntry log, HashMap<String, Object> data) {
        String r = this.stringifier.logInPattern(log, data);
        if (!r.isEmpty()) this.outputType.output(r);
        return r;
    }

    @Override
    public String logStatus(long id, HashMap<String, Object> data) {
        String r = this.stringifier.logStatus(id, data);
        if (r != null) this.outputType.output(r);
        return r;
    }
}
