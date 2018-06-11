package logger;

import logger.loggers.Logger;
import logger.output.Output;
import logger.serialize.Serialize;

public class Instances {

    public static Logger RootLogger ;
    public static void Start(){
        Instances.RootLogger = Logger.create("Logger", Serialize.toChalk(Output.CONSOLE()));;
    }
}
