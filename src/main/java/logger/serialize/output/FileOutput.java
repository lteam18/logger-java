package logger.serialize.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class FileOutput implements Type {

    private String filePath;

    public FileOutput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void output(String msg) {
        FileWriter fw = null;
        try {
            File f = new File(filePath);
            fw = new FileWriter(f, true);
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
}
