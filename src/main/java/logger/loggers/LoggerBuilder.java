package logger.loggers;

import java.util.ArrayList;
import java.util.Arrays;

import logger.serialize.Major;
import logger.serialize.Serialize;

public class LoggerBuilder {
    private String name;
    private ArrayList<Serialize.Type> majorlist;

    public LoggerBuilder() {
        majorlist = new ArrayList<>();
    }

    public final LoggerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public final LoggerBuilder addOutputType(Major major) {
        this.majorlist.add(major);
        return this;
    }

        public final Logger build() {
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(name));
            return new Logger(arrayList, Serialize.combine(majorlist));
        }
}
