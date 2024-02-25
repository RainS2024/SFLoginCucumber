package com.Salesforce.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4jUtility {
	public static Log4jUtility LogObject;
    public static Logger logger;

private Log4jUtility() {
		
	}
    
    public static  Log4jUtility getInstance() {
		if(LogObject==null) {
			LogObject=new Log4jUtility();
		}

		return LogObject;
	}
   /* static {
        logger = LogManager.getLogger(Log4jUtility.class.getName());
       
    }*/
    
public Logger getLogger() {
		
	logger=LogManager.getLogger(Log4jUtility.class);
		return logger;
	}
    
    public static Logger getLogger(Object name) {
    	return logger = LogManager.getLogger(name.getClass());
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
