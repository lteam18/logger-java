package logger.loggers;

import java.util.ArrayList;
import java.util.Arrays;
import logger.serialize.Major;

public class LoggerBuilder {
    private String name;
    private ArrayList<Serializer.Type> majorlist;

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
        return new Logger(arrayList, Serializer.combine(majorlist));
    }
}
