package logger.loggers;

import java.util.ArrayList;
import java.util.Arrays;
import logger.serialize.Major;
import logger.serialize.Serialize;

public class LoggerBuilder {
    private String name;
    private Serialize.Type[] types;
    private static int typeCount = 0;

    public LoggerBuilder() {}

    public final LoggerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public final LoggerBuilder addOutputType(Major major) {
        types[typeCount] = major;
        typeCount++;
        return this;
    }

    public final Logger build() {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(name));
        return new Logger(arrayList, Serialize.combine(types));
    }
}
