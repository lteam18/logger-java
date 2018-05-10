package logger.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logger.Utils;

/**
 * Created by Lynnsion on 2018/5/4.
 */

@SuppressWarnings("SpellCheckingInspection")
public class Persistant {

//    public Persistant() {
//
//    }

    public static class LevelLog {
        public ArrayList<String> N;
        public long T; //timeStamp
        public LevelType L; //LogLevel
        public String M = "";  //Message
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
                Error E
        ) {
            this.N = N;
            this.T = T;
            this.L = L;
            this.M = M;
            this.D = D;
            if (E == null) {
                ;
            } else {
                this.E = new Utils().stringifyError(E);
            }

        }


        public Map<String, Object> getLevelLogDataMap() {
            Map<String, Object> result_map = new HashMap<>();
            result_map.put("N", N);
            result_map.put("T", T);    //timeStamp
            result_map.put("L", L);    //LogLevel
            if (!M.equals("")) {
                result_map.put("M", M);    //Message
            }
            if (D != null) {
                result_map.put("D", D);    //Data
            }
            if (E != null) {
                result_map.put("E", E);    //ErrorStack
            }

            return result_map;
        }
    }

    public class HeatLog {
        private Map<String, Object> data;
        private BeatLog beatLog;

        public HeatLog() {

        }
    }

    public class BeatLog {
        private long hid;

        public BeatLog() {

        }
    }

    public class StatusDefineLog {
        private long sid;
        private Map<String, Object> data;

        public StatusDefineLog() {

        }
    }

    public class StatusRecordLog {
        private long sid;
        private Map<String, Object> data;

        public StatusRecordLog() {

        }
    }

}
