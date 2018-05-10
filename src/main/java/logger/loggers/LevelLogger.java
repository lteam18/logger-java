package logger.loggers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import logger.serialize.serializer.Type;
import logger.types.LevelLoggerOption;
import logger.types.LevelType;
import logger.types.Persistant;

/**
 * Created by Lynnsion on 2018/5/10.
 */

public class LevelLogger {
    private LevelType logType;
    private Type s;
    private ArrayList<String> namelist;

    public LevelLogger(LevelType logType, Type s, ArrayList<String> namelist) {
        this.logType = logType;
        this.s = s;
        this.namelist = namelist;
    }


    public void o(LevelLoggerOption o) {
        this.s.log(new Persistant.LevelLog(
                this.namelist,
                new Date().getTime(),
                this.logType,
                o.msg,
                o.data,
                o.error));
    }

    public void msg(String msg) {
        this.o(new LevelLoggerOption(msg));
    }

    public void msg_trace(String msg, Error error) {
        this.o(new LevelLoggerOption(msg, error));
    }

    public void msg_data(String msg, HashMap<String, Object> data) {
        this.o(new LevelLoggerOption(msg, data));
    }

    public void msg_data_trace(String msg, HashMap<String, Object> data, Error error) {
        this.o(new LevelLoggerOption(msg, data, error));
    }

    public void trace(Error error){
        this.msg_trace("invoked", error);
    }

}
