package logger.loggers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import logger.Utils;
import logger.serialize.Serializer;
import logger.types.Types;

/**
 * Created by Lynnsion on 2018/5/10.
 */

@SuppressWarnings("SpellCheckingInspection")
public class LevelLogger {
    private Types.LevelType logType;
    private Serializer.Type s;
    private ArrayList<String> namelist;

    private O o;
    private Types log = new Types();


    public LevelLogger(Types.LevelType logType, Serializer.Type s, ArrayList<String> namelist) {
        this.logType = logType;
        if (s == null) {
            this.s = new Serializer().major;
        } else {
            this.s = s;
        }
        this.namelist = namelist;
    }


    public void o(Types.LevelLoggerOption o) {

        log.persistant.levelLog.N = this.namelist;
        log.persistant.levelLog.T = new Date().getTime();
        log.persistant.levelLog.L = this.logType;
        log.persistant.levelLog.M = o.msg;
        log.persistant.levelLog.D = o.data;
        if (o.error != null)
            log.persistant.levelLog.E = new Utils().stringifyError(o.error);


        this.s.log(log.persistant.levelLog);
    }

    public void msg(String msg) {
        log.levelLoggerOption.msg = msg;
        this.o(log.levelLoggerOption);
    }

    public void msg_trace(String msg, Error error) {
        log.levelLoggerOption.msg = msg;
        log.levelLoggerOption.error = error;
        this.o(log.levelLoggerOption);
    }

    public void msg_data(String msg, HashMap<String, Object> data) {
        log.levelLoggerOption.msg = msg;
        log.levelLoggerOption.data = data;
        this.o(log.levelLoggerOption);
    }

    public void msg_data_trace(String msg, HashMap<String, Object> data, Error error) {
        log.levelLoggerOption.msg = msg;
        log.levelLoggerOption.data = data;
        log.levelLoggerOption.error = error;
        this.o(log.levelLoggerOption);
    }

    public void trace(Error error) {
        this.msg_trace("invoked", error);
    }

    public interface O {
        void log(Types.Persistant.LevelLog data);
    }
}
