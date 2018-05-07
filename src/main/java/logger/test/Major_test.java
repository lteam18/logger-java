package logger.test;

import logger.JSON;
import logger.serialize.output.Output;
import logger.serialize.output.Type;
import logger.serialize.serializer.Major;

/**
 * Created by Lynnsion on 2018/5/7.
 */

public class Major_test {

    public static void main(String[] args) {

        Type ty = new Output().CONSOLE();

        Major major = new Major(
                JSON.stringify("sss"),
                ty
        );

    }
}
