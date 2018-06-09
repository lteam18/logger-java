package logger.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {

    public interface Type {
        void output(String msg);
    }

    public static Type CONSOLE() {
        return msg -> System.out.println(msg);
    }

    public static Type file(String filePath) {
        return msg -> appendFile(msg + "\n", filePath);
    }

    public static Type combine(Type... outputs) {
        ArrayList<Type> output = new ArrayList<>();
        for (int i = 0; i < outputs.length; i++) {
            output.add(outputs[i]);
        }

        return (String message) ->
                output.forEach(
                        (e) -> {
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
