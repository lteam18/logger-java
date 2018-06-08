package logger.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import logger.Utils;
import logger.serialize.Serializer;

public class Persistant {

    public static class LevelLog {
        public ArrayList<String> N;
        public long T; //timeStamp
        public Types.LevelType L; //LogLevel
        public String M = ""; //Message
        //Data
        public HashMap<String, Object> D = new HashMap<>();
        /*
        ErrorStack
           msg: string,
           name: string,
           stack: string
        */
        public HashMap<String, Object> E = new HashMap<>();

        public Serializer.Type s = Serializer.toJSON();

        public LevelLog(
                ArrayList<String> N,
                long T,
                Types.LevelType L,
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
            }

            this.E =
                    E instanceof HashMap
                            ? (HashMap<String, Object>) E
                            : Utils.stringifyError((Error) E);
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
