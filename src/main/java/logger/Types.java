package logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logger.Utils;

public class Types {

    public static class LevelLoggerOption {
        public Error error;
        public String msg;
        public HashMap<String, Object> data;
    }

    public static class Persistant {

        public static class LevelLog {
            public ArrayList<String> N;
            public long T;          //timeStamp
            public LevelType L;     //LogLevel
            public String M = "";   //Message
                                    //Data
            public HashMap<String, Object> D = new HashMap<>();
                                    /*
                                    ErrorStack
                                       msg?: string,
                                       name?: string,
                                       stack?: string
                                    */
            public HashMap<String, Object> E = new HashMap<>();

            public LevelLog(ArrayList<String> N,
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

        }

        public static class HeatLog {
            public BeatLog beatLog;
            public HashMap<String, Object> data;
        }

        public static class BeatLog {
            public long hid;
        }

        public static class StatusDefineLog {
            public long sid;
            public Map<String, Object> data;
        }

        public static class StatusRecordLog {
            public long sid;
            public Map<String, Object> data;
        }
    }

    public enum LevelType {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }

}
