package logger.unserialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;

import logger.JSON;

/**
 * Created by Lynnsion on 2018/5/3.
 */

public class DefaultUnserializerParser {
    public Map<Long, Status> statusList = new HashMap<>();

    public DefaultUnserializerParser() {

    }

    public DefaultUnserializerParser(Map<Long, Status> statusList) {
        this.statusList = statusList;
    }

    //Status
    public void record_status(Status st) {
        this.statusList.put(st.sid, st);
    }

    public Status parseStatusDefinition(String astr) {
        final int pos = astr.indexOf(" ");
        final long id = Long.parseLong(astr.substring(1, pos));
        final Map<String, String> data = JSON.parse(astr.substring(pos + 1));

        return new Status(id, data);
    }

    public Map<String, Object> parseStatusRecord(String astr) {
        final int pos = astr.indexOf(" ");
        final long id = Long.parseLong(astr.substring(1, pos));
        final int pos2 = astr.indexOf(" ", pos);
        final long timeStamp = Long.parseLong(astr.substring(pos + 1, pos2));
        final Map<String, String> data = JSON.parse(astr.substring(pos2 + 1));

        Map<String, Object> re = new HashMap<>();
        re.put("definition", this.statusList.get(id));
        re.put("timeStamp", timeStamp);
        re.put("data", data);
        return re;
    }


    //Heartbeat
    public Map<Long, Heartbeat> heartbeats = new HashMap<>();

    public void record_heartbeat(Heartbeat hb) {
        this.heartbeats.put(hb.hid, hb);
    }

    public Heartbeat parseHeart(String astr) {
        final int pos = astr.indexOf(" ");
        final long id = Long.parseLong(astr.substring(1, pos));
        final Map<String, String> data = JSON.parse(astr.substring(pos + 1));

        return new Heartbeat(id, data);
    }

    public Map<String, Object> parseBeat(String astr) {
        final int pos = astr.indexOf(" ");
        final long id = Long.parseLong(astr.substring(1,pos));
        final long timeStamp  = Long.parseLong(astr.substring(pos+1));

        Map<String, Object> re = new HashMap<>();
        re.put("definition", this.heartbeats.get(id));
        re.put("timestamp", timeStamp);
        return re;
    }

}
