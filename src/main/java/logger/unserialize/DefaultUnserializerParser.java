package logger.unserialize;

import java.util.ArrayList;
import java.util.Map;

import logger.JSON;

/**
 * Created by Lynnsion on 2018/5/3.
 */

public class DefaultUnserializerParser {
    public ArrayList<Status> statusList = new ArrayList<>();

    public DefaultUnserializerParser() {

    }

    public DefaultUnserializerParser(ArrayList<Status> statusList) {
        this.statusList = statusList;
    }

    public void record_status(Status st) {
        this.statusList.set(new Long(st.sid).intValue(), st);
    }

    public Status parseStatusDefinition(String astr) {
        final int pos = astr.indexOf(" ");
        final int id = Integer.parseInt(astr.substring(1, pos));
        final Map<String, String> data = JSON.parse(astr.substring(pos + 1));

        return new Status((long) id, data);
    }
}
