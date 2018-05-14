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
        HashMap<String, Object> errorHashmap;
        if (o.error != null) {
            errorHashmap = new Utils().stringifyError(o.error);
        } else {
            errorHashmap = null;
        }

        Types.Persistant.LevelLog levelLog = new Types().new Persistant().new LevelLog(
                this.namelist,
                new Date().getTime(),
                this.logType,
                o.msg,
                o.data,
                errorHashmap
        );
        this.s.log(levelLog);
    }

    public void msg(String msg) {
        Types.LevelLoggerOption levelLoggerOption = new Types().new LevelLoggerOption();
        levelLoggerOption.msg = msg;
        this.o(levelLoggerOption);
    }

    public void msg_trace(String msg, Error error) {
        Types.LevelLoggerOption levelLoggerOption = new Types().new LevelLoggerOption();
        levelLoggerOption.msg = msg;
        levelLoggerOption.error = error;
        this.o(levelLoggerOption);
    }

    public void msg_data(String msg, HashMap<String, Object> data) {
        Types.LevelLoggerOption levelLoggerOption = new Types().new LevelLoggerOption();
        levelLoggerOption.msg = msg;
        levelLoggerOption.data = data;
        this.o(levelLoggerOption);
    }

    public void msg_data_trace(String msg, HashMap<String, Object> data, Error error) {
        Types.LevelLoggerOption levelLoggerOption = new Types().new LevelLoggerOption();
        levelLoggerOption.msg = msg;
        levelLoggerOption.data = data;
        levelLoggerOption.error = error;
        this.o(levelLoggerOption);
    }

    public void trace(Error error) {
        this.msg_trace("invoked", error);
    }
}
