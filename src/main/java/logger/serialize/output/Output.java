package logger.serialize.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class Output implements Type {

    private String msg;

    public Output() {
        this.msg = "";
    }

    public Output(String msg) {
        this.msg = msg;
    }

    public Type CONSOLE() {
        return (String msg) -> console(msg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Type file(String filePath) {
        return (String msg) -> appendFile(msg, filePath);
    }

//    public Type combine(ArrayList<Type>... outputs) {
//        ArrayList<ArrayList<Type>> output = new ArrayList<>();
//        for (int i = 0; i < outputs.length; i++) {
//            output.add(outputs[i]);
//        }
//        return (String message) -> output.forEach((e) -> e.forEach((a) -> a.output(message)));
//    }

    public Type combine(Type... outputs) {
        ArrayList<Type> output = new ArrayList<>();
        for (int i = 0; i < outputs.length; i++) {
            output.add(outputs[i]);
        }

        return (String message) -> output.forEach((e) -> e.output(message));
    }


    private void console(String msg) {
        System.out.println(msg);
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


    @Override
    public void output(String msg) {
        System.out.println(msg);
    }
}
