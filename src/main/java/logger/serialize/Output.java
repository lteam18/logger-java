package logger.serialize;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class Output {

    private Type type = msg1 -> System.out.println(msg1);

    public Output() {
    }

    public Type CONSOLE() {
        return   (String msg) -> type.output(msg);
    }

    public Type file(String filePath) {
        return (String msg) -> appendFile(msg, filePath);
    }

    public Type combine(Type... outputs) {
        ArrayList<Type> output = new ArrayList<>();
        for (int i = 0; i < outputs.length; i++) {
            output.add(outputs[i]);
        }

        return (String message) -> output.forEach((e) -> e.output(message));
    }

    private void appendFile(String msg, String filePath) {
        FileWriter fw = null;
        try {
            File file = new File(filePath);
            fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(msg);

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
