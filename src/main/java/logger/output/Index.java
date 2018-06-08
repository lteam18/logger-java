package logger.output;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logger.serialize.Output;

public class Index {

    public interface Type {
        void output(String msg);
    }

    public static Output.Type CONSOLE() {
        Output.Type t = msg -> System.out.println(msg);
        return t;
    }

    public static Output.Type file(String filePath) {
        Output.Type t = msg -> appendFile(msg + "\n", filePath);
        return t;
    }

    public static Output.Type combine(Output.Type... outputs) {
        ArrayList<Output.Type> output = new ArrayList<>();
        for (int i = 0; i < outputs.length; i++) {
            output.add(outputs[i]);
        }

        return (String message) -> output.forEach((e) -> {
            try {
                e.output(message);
            } catch (Exception err) {
                err.printStackTrace();
            }
        });

    }

    private static void appendFile(String msg, String filePath) {
        FileWriter fw;
        try {
            File file = new File(filePath);
            fw = new FileWriter(file, true);

            fw.append(msg);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
