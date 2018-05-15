package logger.serialize;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/** Created by Lynnsion on 2018/5/6. */
@SuppressWarnings("SpellCheckingInspection")
public class Output {

    public Output() {}

    public static Type CONSOLE() {
        Type t = msg -> System.out.println(msg);
        return t;
    }

    public static Type file(String filePath) {
        Type t = msg -> appendFile(msg, filePath);
        return t;
    }

    public static Type combine(Type... outputs) {
        ArrayList<Type> output = new ArrayList<>();
        for (int i = 0; i < outputs.length; i++) {
            output.add(outputs[i]);
        }

        return (String message) -> output.forEach((e) -> e.output(message));
    }

    private static void appendFile(String msg, String filePath) {
        FileWriter fw;
        try {
            File file = new File(filePath);
            fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);

            pw.print(msg);

            pw.flush();
            fw.flush();
            pw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface Type {
        void output(String msg);
    }
}
