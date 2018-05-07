package logger.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lynnsion on 2018/5/4.
 */

public class Persistant {

    public LevelLog levelLog = new LevelLog();
    public HeatLog heatLog = new HeatLog();
    public BeatLog beatLog = new BeatLog();
    public StatusDefineLog statusDefineLog = new StatusDefineLog();
    public StatusRecordLog statusRecordLog = new StatusRecordLog();

    public Persistant() {

    }


    public class LevelLog {
        private ArrayList<String> N;
        private long T; //timeStamp
        private int L; //LogLevel

        private String M = "";  //Message
        //Data
        private Map<String, Object> D = new HashMap<>();
        /*
         ErrorStack
            msg?: string,
            name?: string,
            stack?: string
         */
        public Map<String, Object> E = new HashMap<>();

        public LevelLog() {

        }

        public Map<String, Object> getLevelLogDataMap() {
            Map<String, Object> result_map = new HashMap<>();
            result_map.put("N", N);
            result_map.put("T", T);    //timeStamp
            result_map.put("L", L);    //LogLevel
            if (!M.equals("")) {
                result_map.put("M", M);    //Message
            }
            if (D.size() != 0) {
                result_map.put("D", D);    //Data
            }
            if (E.size() != 0) {
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
