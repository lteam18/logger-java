package logger.loggers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import logger.Utils;
import logger.serialize.Major;
import logger.serialize.Serializer;
import logger.type.Persistant;
import logger.type.Types;

public class LevelLogger {
    private Types.LevelType logType;
    private Serializer.Type s;
    private ArrayList<String> namelist;

    public LevelLogger(Types.LevelType logType, Serializer.Type s, ArrayList<String> namelist) {
        this.logType = logType;
        this.s = s == null ? new Major() : s;
        this.namelist = namelist;
    }

    public void o(Types.LevelLoggerOption o) {
        HashMap<String, Object> errorHashmap;

        errorHashmap = o.error != null ? Utils.stringifyError(o.error) : null;

        Persistant.LevelLog levelLog =
                new Persistant.LevelLog(
                        this.namelist,
                        new Date().getTime(),
                        this.logType,
                        o.msg,
                        o.data,
                        errorHashmap);
        this.s.log(levelLog);
    }

    public void msg(String msg) {
        Types.LevelLoggerOption levelLoggerOption = new Types.LevelLoggerOption();
        levelLoggerOption.msg = msg;
        this.o(levelLoggerOption);
    }

    public void msg_trace(String msg, Error error) {
        Types.LevelLoggerOption levelLoggerOption = new Types.LevelLoggerOption();
        levelLoggerOption.msg = msg;
        levelLoggerOption.error = error;
        this.o(levelLoggerOption);
    }

    public void msg_data(String msg, HashMap<String, Object> data) {
        Types.LevelLoggerOption levelLoggerOption = new Types.LevelLoggerOption();
        levelLoggerOption.msg = msg;
        levelLoggerOption.data = data;
        this.o(levelLoggerOption);
    }

    public void msg_data_trace(String msg, HashMap<String, Object> data, Error error) {
        Types.LevelLoggerOption levelLoggerOption = new Types.LevelLoggerOption();
        levelLoggerOption.msg = msg;
        levelLoggerOption.data = data;
        levelLoggerOption.error = error;
        this.o(levelLoggerOption);
    }

    public void trace(Error error) {
        this.msg_trace("invoked", error);
    }
}
