package logger.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logger.Utils;

/**
 * Created by Lynnsion on 2018/5/10.
 */
public class Types {

    public class LevelLoggerOption {
        public Error error;
        public String msg;
        public HashMap<String, Object> data;

        public LevelLoggerOption() {
        }

        public LevelLoggerOption(String msg) {
            this.msg = msg;
        }

        public LevelLoggerOption(String msg, Error error) {
            this.error = error;
            this.msg = msg;
        }

        public LevelLoggerOption(String msg, HashMap<String, Object> data) {
            this.msg = msg;
            this.data = data;
        }

        public LevelLoggerOption(String msg, HashMap<String, Object> data, Error error) {
            this.msg = msg;
            this.data = data;
            this.error = error;
        }
    }

    public final String[] LOG_TYPE = {"debug", "info", "warn", "error", "fatal"};

    public class Persistant {

        public LevelLog levelLog;
        public HeatLog heatLog;
        public BeatLog beatLog;
        public StatusDefineLog statusDefineLog;
        public StatusRecordLog statusRecordLog;

        public Persistant() {
            levelLog = new LevelLog();
            heatLog = new HeatLog();
            beatLog = new BeatLog();
            statusDefineLog = new StatusDefineLog();
            statusRecordLog = new StatusRecordLog();
        }

        public class LevelLog {
            public ArrayList<String> N;
            public long T; //timeStamp
            public LevelType L; //LogLevel
            public String M = ""; //Message
            //Data
            public HashMap<String, Object> D = new HashMap<>();
            /*
            ErrorStack
               msg?: string,
               name?: string,
               stack?: string
            */
            public HashMap<String, Object> E = new HashMap<>();

            public LevelLog() {
            }

            public LevelLog(
                    ArrayList<String> N,
                    long T,
                    LevelType L,
                    String M,
                    HashMap<String, Object> D,
                    Object E) {
                this.N = N;
                this.T = T;
                this.L = L;
                this.M = M;
                this.D = D;
                if (E == null) {
                    return;
                } else {
                    if (E instanceof Error)
                        this.E = new Utils().stringifyError((Error) E);
                    else if (E instanceof HashMap) {
                        this.E = (HashMap<String, Object>) E;
                    }
                }
            }

            public HashMap<String, Object> getLevelLogDataMap() {
                HashMap<String, Object> result_map = new HashMap<>();
                result_map.put("N", N);
                result_map.put("T", T); //timeStamp
                result_map.put("L", L); //LogLevel
                if (!M.equals("")) {
                    result_map.put("M", M); //Message
                }
                if (D != null) {
                    result_map.put("D", D); //Data
                }
                if (E != null) {
                    result_map.put("E", E); //ErrorStack
                }

                return result_map;
            }
        }

        public class HeatLog {
            public BeatLog beatLog;
            public HashMap<String, Object> data;

            public HeatLog() {
            }
        }

        public class BeatLog {
            public long hid;

            public BeatLog() {
            }
        }

        public class StatusDefineLog {
            public long sid;
            public Map<String, Object> data;

            public StatusDefineLog() {
            }
        }

        public class StatusRecordLog {
            public long sid;
            public Map<String, Object> data;

            public StatusRecordLog() {
            }
        }
    }

    public enum LevelType {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }

    public static LevelLoggerOption levelLoggerOption;
    public static Persistant persistant;

    public Types() {
        this.levelLoggerOption = new LevelLoggerOption();
        this.persistant = new Persistant();
    }
}
