package logger.serialize.output;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class CONSOLE implements Type {
    @Override
    public void output(String msg) {
        System.out.println(msg);
    }
}
