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
        //timeStamp
        private long T;
        //LogLevel
        private int L;
        //Message
        private String M = "";
        //Data
        private Map<String, Object> D = new HashMap<>();
        /*
         Stack
            msg?: string,
            name?: string,
            stack?: string
         */
        private Map<String, Object> E = new HashMap<>();

        public LevelLog() {

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
