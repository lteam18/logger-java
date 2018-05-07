package logger.serialize.serializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import logger.JSON;
import logger.serialize.output.Output;
import logger.types.Persistant;

/**
 * Created by Lynnsion on 2018/5/6.
 */

public class Major implements Type {

    private String levelLogStringify;
    private Method output;
    Class<?> classType = Output.class;

    public Major(Object msg) {
//        levelLogStringify = JSON.stringify((Map<String, ?>) msg);

        try {
            output = classType.getMethod("CONSOLE", String.class);
            output.invoke(classType.newInstance(), "new Major");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void log(Persistant.LevelLog data) {
        try {
            this.output.invoke(classType.newInstance(), "Major log");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void defineHeart(long hid, Map<String, Object> data) {

    }

    @Override
    public void beat(long hid) {

    }

    @Override
    public void defineStatus(long sid, Map<String, Object> data) {

    }

    @Override
    public void rec(long sid, Map<String, Object> status) {

    }
}
